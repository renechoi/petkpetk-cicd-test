package com.petkpetk.service.domain.shopping.service.review;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.config.converter.ImageConverter;
import com.petkpetk.service.config.file.ImageLocalRepository;
import com.petkpetk.service.domain.shopping.dto.review.ReviewDto;
import com.petkpetk.service.domain.shopping.dto.review.ReviewImageDto;
import com.petkpetk.service.domain.shopping.dto.review.request.ReviewRegisterRequest;
import com.petkpetk.service.domain.shopping.dto.review.response.ReviewResponse;
import com.petkpetk.service.domain.shopping.entity.item.Item;
import com.petkpetk.service.domain.shopping.entity.item.ItemImage;
import com.petkpetk.service.domain.shopping.entity.review.Review;
import com.petkpetk.service.domain.shopping.entity.review.ReviewImage;
import com.petkpetk.service.domain.shopping.repository.item.ItemImageRepository;
import com.petkpetk.service.domain.shopping.repository.item.ItemRepository;
import com.petkpetk.service.domain.shopping.repository.review.ReviewImageRepository;
import com.petkpetk.service.domain.shopping.repository.review.ReviewRepository;
import com.petkpetk.service.domain.shopping.repository.review.likes.ReviewLikesRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewLikesRepository reviewLikesRepository;
	private final ReviewRepository reviewRepository;
	private final ReviewImageRepository reviewImageRepository;
	private final ItemRepository itemRepository;
	private final ItemImageRepository itemImageRepository;
	private final ImageLocalRepository<ReviewImage> imageLocalRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public void addReview(ReviewDto reviewDto) {
		List<ReviewImage> images = ImageConverter.of(ReviewImage::from).convertToImages(reviewDto.getRawImages());

		reviewRepository.save(reviewDto.toEntity(images));

		IntStream.range(0, images.size())
			.forEach(image -> imageLocalRepository.save(images.get(image), reviewDto.getRawImages().get(image)));

		setItemRating(reviewDto.getItem().getId());
	}

	public List<ReviewResponse> getReviewList(Long itemId) {
		List<ReviewResponse> reviewResponses = new ArrayList<>();
		List<Review> reviews = reviewRepository.findAllByItem_Id(itemId);

		for (Review review : reviews) {
			ReviewResponse reviewResponse = ReviewResponse.from(review);
			reviewResponses.add(reviewResponse);
		}

		return reviewResponses;
	}

	public boolean plusLike(Long reviewId, Long likeNum) {
		Review review =  entityManager.find(Review.class, reviewId);
		review.setLikes(likeNum + 1);
		entityManager.flush();

		return true;
	}

	public boolean minusLike(Long reviewId) {
		Review review =  entityManager.find(Review.class, reviewId);
		review.setLikes(review.getLikes() - 1);
		entityManager.flush();

		return true;
	}

	public void deleteReview(Long reviewId) {
		Review review = reviewRepository.findById(reviewId).get();
		Long itemId = review.getItem().getId();
		review.setDeletedYn("Y");

		reviewLikesRepository.deleteAllByReviewId(reviewId);

		List<ReviewImage> reviewImages = reviewImageRepository.findByReviewIdOrderByIdAsc(reviewId);
		reviewImages.forEach(reviewImage -> reviewImage.setDeletedYn("Y"));

		imageLocalRepository.deleteFiles(reviewImages);


		setItemRating(itemId);

	}

	public ReviewResponse modifyReview(ReviewRegisterRequest reviewRegisterRequest, Long reviewId) {
		Review review = reviewRepository.findById(reviewId).get();
		Long itemId = review.getItem().getId();

		List<ReviewImage> reviewImages = review.getImages();
		reviewRegisterRequest.getImages().removeIf(MultipartFile::isEmpty);

		List<ReviewImage> notModifiedImages = reviewRegisterRequest.getReviewImageDtos()
			.stream()
			.map(ReviewImageDto::getUniqueName)
			.map(reviewImageRepository::findByUniqueName)
			.collect(Collectors.toList());

		List<ReviewImage> newlyAddedImages = reviewRegisterRequest.getImages().stream()
			.map(image -> !image.isEmpty() ? ReviewImage.from(image) : null)
			.collect(Collectors.toList());

		List<ReviewImage> allRequestedImages = Stream.concat(notModifiedImages.stream(), newlyAddedImages.stream())
			.collect(Collectors.toList());

		List<ReviewImage> imagesToDelete = reviewImages.stream()
			.filter(reviewImage -> !allRequestedImages.contains(reviewImage))
			.collect(Collectors.toList());

		imageLocalRepository.deleteFiles(imagesToDelete);

		IntStream.range(0, newlyAddedImages.size())
			.forEach(image -> imageLocalRepository.save(newlyAddedImages.get(image),
				reviewRegisterRequest.getImages().get(image)));

		reviewImages.removeIf(reviewImage -> !notModifiedImages.contains(reviewImage));

		reviewImages.addAll(newlyAddedImages);
		review.mapImages(reviewImages);
		review.setContent(reviewRegisterRequest.getContent());
		review.setRating(reviewRegisterRequest.getRating());

		setItemRating(itemId);

		return ReviewResponse.from(review);
	}

	public List<ReviewResponse> getUserReviewList(String email) {
		List<Review> reviewList = reviewRepository.findAllByUserAccountEmail(email);
		List<ReviewResponse> reviewResponses = new ArrayList<>();

		IntStream.range(0, reviewList.size())
			.filter(i -> reviewList.get(i).getDeletedYn().equals("N"))
			.forEach(i-> reviewResponses.add(ReviewResponse.from(reviewList.get(i))));

		List<Long> itemIds = new ArrayList<>();
		for (ReviewResponse review : reviewResponses) {
			itemIds.add(review.getItem().getId());
		}

		List<ItemImage> itemImages = new ArrayList<>();
		IntStream.range(0, (itemIds.size()))
			.forEach( i ->
				itemImages.add(itemImageRepository.findAllByItem_Id(itemIds.get(i)))
			);

		IntStream.range(0, reviewResponses.size())
			.forEach(i -> {
				for (int j = 0; j < itemImages.size(); j++) {
					if ((reviewResponses.get(i).getItem().getId() == itemImages.get(j).getItem().getId())) {
						if (itemImages.get(j).getDeletedYn().equals("N")) {
							reviewResponses.get(i).setItemImageUrl(itemImages.get(j).getImageUrl());
						} else {
							reviewResponses.get(i).setItemImageUrl("itemDeleted");
						}
						break;
					} else {
						reviewResponses.get(i).setItemImageUrl("itemDeleted");
					}
				}
			});

		return reviewResponses;
	}

	private void setItemRating(Long itemId) {
		List<Review> reviewList = reviewRepository.findAllByItem_Id(itemId);
		double totalRating = 0.0;

		for (Review review : reviewList) {
			totalRating += review.getRating();
		}

		double averageRating = totalRating / reviewList.size();
		double rating = Math.round(averageRating * 2) / 2.0;

		if (rating % 1 != 0) {
			rating = Math.floor(rating) + 0.5;
		}
		rating = Math.min(rating, 5.0);

		Item item = itemRepository.findById(itemId).orElse(null);

		if (item != null) {
			item.setTotalRating(rating);
			itemRepository.flush();
		}
	}
}

package com.petkpetk.service.domain.community.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.config.converter.ImageConverter;
import com.petkpetk.service.config.file.ImageLocalRepository;
import com.petkpetk.service.domain.community.dto.ArticleDto;
import com.petkpetk.service.domain.community.entity.ArticleImage;
import com.petkpetk.service.domain.community.repository.ArticleImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class articleImageService {
	private final ArticleImageRepository articleImageRepository;
	private final ImageLocalRepository<ArticleImage> articleImageLocalRepository;

	public List<ArticleImage> convertToImages(List<MultipartFile> rawImages) {
		return ImageConverter.of(ArticleImage::from).convertToImages(rawImages);
	}

	public void uploadImages(ArticleDto articleDto, List<ArticleImage> images) {
		IntStream.range(0, images.size())
			.forEach(
				image -> articleImageLocalRepository.save(images.get(image), articleDto.getRawImages().get(image)));
	}

	public void deleteImagesByArticle(Long articleId) {
		List<ArticleImage> articleImages = articleImageRepository.findByArticleIdOrderByIdAsc(articleId);
		articleImages.forEach(articleImage -> articleImage.setDeletedYn("Y"));

		articleImageLocalRepository.deleteFiles(articleImages);
	}

	public List<MultipartFile> convertToRawImages(List<ArticleImage> articleImages) {
		return articleImages.stream()
			.map(articleImageLocalRepository::findByPetkpetkImage)
			.collect(Collectors.toList());

	}
}

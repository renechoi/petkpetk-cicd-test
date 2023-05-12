package com.petkpetk.service.domain.shopping.repository.querydsl.item;

import static com.petkpetk.service.domain.shopping.entity.item.QItemImage.*;
import static java.time.LocalDateTime.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.petkpetk.service.domain.shopping.constant.ItemStatus;
import com.petkpetk.service.domain.shopping.dto.item.ItemSearchDto;
import com.petkpetk.service.domain.shopping.dto.item.MainItemDto;
import com.petkpetk.service.domain.shopping.dto.item.ManageItemDto;
import com.petkpetk.service.domain.shopping.dto.item.QManageItemDto;
import com.petkpetk.service.domain.shopping.entity.item.Item;
import com.petkpetk.service.domain.shopping.entity.item.QItem;
import com.petkpetk.service.domain.shopping.entity.item.QItemImage;
import com.petkpetk.service.domain.shopping.entity.review.QReview;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

	private JPAQueryFactory queryFactory;

	public ItemRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	private BooleanExpression searchSellStatusEq(ItemStatus searchSellStatus) {

		return searchSellStatus == null ? null : QItem.item.itemStatus.eq(searchSellStatus);
	}

	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = now();

		if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
			return null;
		} else if (StringUtils.equals("1d", searchDateType)) {
			dateTime = dateTime.minusDays(1);
		} else if (StringUtils.equals("1w", searchDateType)) {
			dateTime = dateTime.minusWeeks(1);
		} else if (StringUtils.equals("1m", searchDateType)) {
			dateTime = dateTime.minusMonths(1);
		} else if (StringUtils.equals("6m", searchDateType)) {
			dateTime = dateTime.minusMonths(6);
		}

		return QItem.item.createdAt.after(dateTime);
	}

	private BooleanExpression searchByLike(String searchBy, String searchQuery) {

		if (StringUtils.equals("itemName", searchBy)) {

			if (searchQuery == null || searchQuery.equals("")) {

			} else {
				return QItem.item.itemName.like("%" + searchQuery + "%");
			}
		} else if (StringUtils.equals("createdBy", searchBy)) {
			if (searchQuery == null || searchQuery.equals("")) {

			} else {
				return QItem.item.createdBy.like("%" + searchQuery + "%");
			}
		}

		return null;
	}

	@Override
	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

		List<Item> content = queryFactory
			.selectFrom(QItem.item)
			.where(regDtsAfter(itemSearchDto.getSearchDateType()),
				searchSellStatusEq(itemSearchDto.getSearchItemStatus()),
				searchByLike(itemSearchDto.getSearchBy(),
					itemSearchDto.getSearchQuery()))
			.orderBy(QItem.item.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = queryFactory
			.select(Wildcard.count)
			.from(QItem.item)
			.where(regDtsAfter(itemSearchDto.getSearchDateType()),
				searchSellStatusEq(itemSearchDto.getSearchItemStatus()),
				searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
			.fetchOne();

		return new PageImpl<>(content, pageable, total);
	}

	private BooleanExpression itemNmLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ? null :
			QItem.item.itemName.like("%" + searchQuery + "%");
	}

	@Override
	public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
		QItem item = QItem.item;
		QReview review = QReview.review;
		QItemImage itemImage = QItemImage.itemImage;

		List<MainItemDto> content = queryFactory
			.select(
				Projections.constructor(
					MainItemDto.class,
					item.id,
					item.itemName,
					item.itemDetail,
					item.itemStatus,
					itemImage.imageUrl,
					item.originalPrice,
					item.discountRate,
					item.price,
					review.count().as("reviewCount"),
					item.totalRating
				)
			)
			.from(item)
			.leftJoin(review).on(review.item.eq(item)).on(review.deletedYn.eq("N"))
			.leftJoin(itemImage).on(itemImage.item.eq(item))
			.where(itemImage.representativeImageYn.eq("Y"))
			.where(itemNmLike(itemSearchDto.getSearchQuery()))
			.where(item.deletedYn.eq("N"))
			.groupBy(item.id, itemImage.imageUrl) // 수정된 부분
			.orderBy(item.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = queryFactory
			.select(Wildcard.count)
			.from(itemImage)
			.join(itemImage.item, item)
			.where(itemImage.representativeImageYn.eq("Y"))
			.where(itemNmLike(itemSearchDto.getSearchQuery()))
			.fetchOne();

		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public Page<ManageItemDto> getManageList(ItemSearchDto itemSearchDto, Pageable pageable, String email) {
		QItem item = QItem.item;
		QItemImage itemImg = itemImage;

		List<ManageItemDto> content = queryFactory
			.select(
				new QManageItemDto(
					item.id,
					item.itemName,
					item.itemDetail,
					item.itemStatus,
					item.userAccount.email,
					itemImage.imageUrl,
					item.price,
					item.createdBy,
					item.createdAt
				)
			)
			.from(itemImg)
			.join(itemImg.item, item)
			.where(itemImg.representativeImageYn.eq("Y"))
			.where(item.userAccount.email.eq(email))
			.where(item.deletedYn.eq("N"))
			.where(
				regDtsAfter(itemSearchDto.getSearchDateType())
				, searchSellStatusEq(itemSearchDto.getSearchItemStatus())
				, searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
			.orderBy(item.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = queryFactory
			.select(Wildcard.count)
			.from(itemImg)
			.join(itemImg.item, item)
			.where(itemImg.representativeImageYn.eq("Y"))
			.where(
				regDtsAfter(itemSearchDto.getSearchDateType())
				, searchSellStatusEq(itemSearchDto.getSearchItemStatus())
				, searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
			.fetchOne();

		return new PageImpl<>(content, pageable, total);
	}

}
package com.petkpetk.service.domain.shopping.repository.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.petkpetk.service.domain.shopping.entity.item.ItemImage;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long>,
	QuerydslPredicateExecutor<ItemImage> {
	List<ItemImage> findByItemIdOrderByIdAsc(Long itemId);
	ItemImage findByUniqueName(String originalName);

	@Query("select iti from ItemImage iti where iti.item.id = ?1 and iti.representativeImageYn = 'Y'")
	ItemImage findAllByItem_Id(Long itemId);

	ItemImage findByItemIdAndRepresentativeImageYn(Long id, String y);
}
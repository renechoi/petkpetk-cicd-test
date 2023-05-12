package com.petkpetk.service.domain.shopping.repository.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.petkpetk.service.domain.shopping.entity.item.Item;
import com.petkpetk.service.domain.shopping.repository.querydsl.item.ItemRepositoryCustom;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>,
	ItemRepositoryCustom {

	void deleteById(Long itemId);

	@Query("select count(t) from Item t where t.deletedYn='N'")
	Long getItemCount();

	@Query("SELECT i FROM Item i JOIN FETCH i.images img WHERE img.representativeImageYn = 'Y' AND i.id = :itemId")
	Optional<Item> findItemWithRepresentativeImageById(@Param("itemId") Long itemId);
}

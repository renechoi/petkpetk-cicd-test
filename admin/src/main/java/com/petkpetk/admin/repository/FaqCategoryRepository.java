package com.petkpetk.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petkpetk.admin.entity.FaqCategory;

public interface FaqCategoryRepository extends JpaRepository<FaqCategory, Long> {
}

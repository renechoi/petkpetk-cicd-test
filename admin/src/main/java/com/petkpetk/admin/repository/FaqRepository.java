package com.petkpetk.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petkpetk.admin.entity.Faq;
import com.petkpetk.admin.entity.ShoppingNotice;

public interface FaqRepository extends JpaRepository<Faq, Long> {
}

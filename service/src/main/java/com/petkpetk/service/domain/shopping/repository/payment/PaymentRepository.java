package com.petkpetk.service.domain.shopping.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petkpetk.service.domain.shopping.entity.payment.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

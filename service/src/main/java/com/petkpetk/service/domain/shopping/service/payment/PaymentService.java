package com.petkpetk.service.domain.shopping.service.payment;

import org.springframework.stereotype.Service;

import com.petkpetk.service.domain.shopping.entity.item.Item;
import com.petkpetk.service.domain.shopping.entity.payment.Payment;
import com.petkpetk.service.domain.shopping.repository.payment.PaymentRepository;
import com.petkpetk.service.domain.user.entity.UserAccount;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;

	public Payment addPayment(Long orderId, UserAccount userAccount, Item item, Long totalCost, String payToken, String payApp) {
		Payment payment = Payment.of(orderId, userAccount, item, totalCost, payToken, payApp);
		return paymentRepository.save(payment);
	}
}

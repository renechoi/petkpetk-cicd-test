package com.petkpetk.service.domain.shopping.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.petkpetk.service.domain.shopping.entity.delivery.Delivery;
import com.petkpetk.service.domain.shopping.service.delivery.DeliveryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/delivery")
public class DeliveryController {

	@Autowired DeliveryService deliveryService;

	@GetMapping("/{id}/status")
	public String getDeliveryStatusById(@PathVariable Long id, Model model) {
		Optional<Delivery> optionalDelivery = deliveryService.getDeliveryById(id);

		if (optionalDelivery.isPresent()) {
			model.addAttribute("deliveryStatus", optionalDelivery.get().getDeliveryStatus());
			return "deliveryStatusView";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}

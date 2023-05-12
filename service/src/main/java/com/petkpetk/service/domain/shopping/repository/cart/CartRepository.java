package com.petkpetk.service.domain.shopping.repository.cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petkpetk.service.domain.shopping.entity.cart.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {

	Cart findCartByUserAccountId(Long userAccountId);
	Optional<Cart> findByUserAccountId(Long userAccountId);
}

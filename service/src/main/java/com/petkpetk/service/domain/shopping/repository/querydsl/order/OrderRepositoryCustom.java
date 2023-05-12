package com.petkpetk.service.domain.shopping.repository.querydsl.order;

import java.time.LocalDateTime;
import java.util.List;

import com.petkpetk.service.domain.shopping.constant.OrderStatus;
import com.petkpetk.service.domain.shopping.dto.order.OrderDto;
import com.petkpetk.service.domain.shopping.entity.order.Order;
import com.petkpetk.service.domain.user.entity.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;


public interface OrderRepositoryCustom {
	List<Order> searchOrders(String userAccountName, LocalDateTime startDate, LocalDateTime endDate, OrderStatus status);

	List<Order> findAllOrdersByUserAccountAndOrderStatus(UserAccount userAccount, OrderStatus orderStatus);

}

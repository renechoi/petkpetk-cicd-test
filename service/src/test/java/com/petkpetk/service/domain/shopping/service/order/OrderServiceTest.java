package com.petkpetk.service.domain.shopping.service.order;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.domain.shopping.constant.DeliveryStatus;
import com.petkpetk.service.domain.shopping.constant.ItemStatus;
import com.petkpetk.service.domain.shopping.constant.OrderStatus;
import com.petkpetk.service.domain.shopping.dto.order.request.OrderRequest;
import com.petkpetk.service.domain.shopping.entity.delivery.Delivery;
import com.petkpetk.service.domain.shopping.entity.item.Item;
import com.petkpetk.service.domain.shopping.entity.order.Order;
import com.petkpetk.service.domain.shopping.entity.order.OrderItem;
import com.petkpetk.service.domain.shopping.exception.OrderAlreadyInProcessException;
import com.petkpetk.service.domain.shopping.repository.delivery.DeliveryRepository;
import com.petkpetk.service.domain.shopping.repository.item.ItemRepository;
import com.petkpetk.service.domain.shopping.repository.order.OrderRepository;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.repository.UserAccountRepository;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserAccountRepository userAccountRepository;

	@Autowired
	DeliveryRepository deliveryRepository;

	private Item createItem() {
		Item item = new Item();
		item.setItemName("테스트 상품");
		item.setPrice(10000L);
		item.setItemDetail("테스트 상품 상세 설명");
		item.setItemStatus(ItemStatus.SELL);
		item.setItemAmount(100L);
		item.setOriginalPrice(10000L);
		item.setDiscountRate(30.0);
		return itemRepository.save(item);
	}

	private UserAccount createUserAccount() {
		UserAccount userAccount = new UserAccount();
		userAccount.setEmail("test@test.com");
		userAccount.setRoles(Set.of(RoleType.ADMIN));
		return userAccountRepository.save(userAccount);
	}

	@Test
	@DisplayName("주문 테스트")
	public void order() {
		// given
		Item item = createItem();
		UserAccount userAccount = createUserAccount();

		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOrderCount(100L);
		orderRequest.setItemId(item.getId());

		// when
		Long orderId = orderService.createOrder(orderRequest, userAccount.getEmail());
		Order savedOrder = orderRepository.findById(orderId)
			.orElseThrow(EntityNotFoundException::new);

		// then
		assertEquals(1, savedOrder.getOrderItems().size());
		assertEquals(OrderStatus.ORDER, savedOrder.getOrderStatus());
		assertEquals(userAccount.getId(), savedOrder.getUserAccount().getId());

		OrderItem savedOrderItem = savedOrder.getOrderItems().get(0);
		assertEquals(item.getId(), savedOrderItem.getItem().getId());
		assertEquals(orderRequest.getOrderCount(), savedOrderItem.getOrderCount());
		assertEquals(item.getPrice() * orderRequest.getOrderCount(), savedOrder.getTotalPrice());

	}

	@Test
	@DisplayName("주문 생성 - 성공")
	void createOrder_Success() {
		// given
		Item item = createItem();
		UserAccount userAccount = createUserAccount();
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOrderCount(100L);
		orderRequest.setItemId(item.getId());

		// when
		Long orderId = orderService.createOrder(orderRequest, userAccount.getEmail());
		Order savedOrder = orderRepository.findById(orderId)
			.orElseThrow(EntityNotFoundException::new);

		// then
		assertEquals(OrderStatus.ORDER, savedOrder.getOrderStatus());
		assertEquals(userAccount.getId(), savedOrder.getUserAccount().getId());
		assertEquals(1, savedOrder.getOrderItems().size());

		OrderItem savedOrderItem = savedOrder.getOrderItems().get(0);
		assertEquals(item.getId(), savedOrderItem.getItem().getId());
		assertEquals(orderRequest.getOrderCount(), savedOrderItem.getOrderCount());
		assertEquals(item.getPrice() * orderRequest.getOrderCount(), savedOrder.getTotalPrice());
	}

	@Test
	@DisplayName("주문 생성 - 실패: 상품이 존재하지 않음")
	void createOrder_Fail_ItemNotExist() {
		UserAccount userAccount = createUserAccount();

		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOrderCount(200L);
		orderRequest.setItemId(101L);

		assertThrows(EntityNotFoundException.class,
			() -> orderService.createOrder(orderRequest, userAccount.getEmail()));
	}

	@Test
	@DisplayName("주문 생성 - 실패: 사용자가 존재하지 않음")
	void createOrder_Fail_UserNotExist() {
		Item item = createItem();

		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOrderCount(10L);
		orderRequest.setItemId(item.getId());

		assertThrows(EntityNotFoundException.class, () -> orderService.createOrder(orderRequest, "not_exist@test.com"));
	}

	@Test
	void testCancelOrder() {
		// given
		Long orderId = 1L;
		String email = "test@test.com";
		UserAccount userAccount = new UserAccount();
		userAccount.setEmail(email);
		Order order = new Order();
		order.setId(orderId);
		order.setUserAccount(userAccount);
		order.setOrderStatus(OrderStatus.ORDER);

		Delivery delivery = new Delivery();
		delivery.setDeliveryStatus(DeliveryStatus.DELIVERING);

		orderRepository.save(order);
		deliveryRepository.save(delivery);

		// when & then
		assertThrows(OrderAlreadyInProcessException.class, () -> {
			orderService.cancelOrder(order, delivery);
		});

		// 주문 상태가 배송중이 아니라면 취소 가능
		order.setOrderStatus(OrderStatus.CANCEL);
		orderService.cancelOrder(order, delivery);
		assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
	}
}
//
//
// 	@Test
// 	@DisplayName("주문 취소 테스트")
// 	public void cancelOrder(){
// 		Item item = createItem();
// 		UserAccount userAccount = createUserAccount();
//
// 		OrderRequest orderRequest = new OrderRequest();
// 		orderRequest.setAmount(10L);
// 		orderRequest.setProductId(item.getId());
//
// 		Long orderId = orderService.createOrder(orderRequest, userAccount.getEmail());
//
// 		Order order = orderRepository.findById(orderId)
// 			.orElseThrow(EntityNotFoundException::new);
//
// 		Delivery delivery = deliveryRepository. findById(orderId).orElseThrow(EntityNotFoundException::new);
//
// 		orderService.cancelOrder(order, delivery);
//
// 		assertEquals(OrderStatus.CANCEL, order.getOrderStatus());
// 		assertEquals(0, item.getItemAmount());
// 	}
//
//
//
//
// }
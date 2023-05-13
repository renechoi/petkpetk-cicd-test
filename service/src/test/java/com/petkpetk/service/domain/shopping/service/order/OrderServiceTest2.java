package com.petkpetk.service.domain.shopping.service.order;// package com.petkpetk.service.domain.shopping.service.order;
//
//
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.assertj.core.api.Assertions.assertThatThrownBy;
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.BDDMockito.*;
// import static org.mockito.Mockito.when;
//
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;
//
// import javax.persistence.EntityNotFoundException;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInstance;
// import org.junit.jupiter.api.TestInstance.Lifecycle;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
//
// import com.petkpetk.service.domain.shopping.constant.DeliveryStatus;
// import com.petkpetk.service.domain.shopping.constant.OrderStatus;
// import com.petkpetk.service.domain.shopping.dto.order.OrderDto;
// import com.petkpetk.service.domain.shopping.dto.order.OrderHistoryDto;
// import com.petkpetk.service.domain.shopping.dto.order.request.OrderRequest;
// import com.petkpetk.service.domain.shopping.entity.delivery.Delivery;
// import com.petkpetk.service.domain.shopping.entity.item.Item;
// import com.petkpetk.service.domain.shopping.entity.order.Order;
// import com.petkpetk.service.domain.shopping.entity.order.OrderItem;
// import com.petkpetk.service.domain.shopping.exception.OrderAlreadyInProcessException;
// import com.petkpetk.service.domain.shopping.repository.item.ItemImageRepository;
// import com.petkpetk.service.domain.shopping.repository.item.ItemRepository;
// import com.petkpetk.service.domain.shopping.repository.order.OrderRepository;
// import com.petkpetk.service.domain.user.entity.UserAccount;
// import com.petkpetk.service.domain.user.repository.UserAccountRepository;
//
// @ExtendWith(MockitoExtension.class)
// @TestInstance(Lifecycle.PER_CLASS)
// public class OrderServiceTest2 {
//
// 	@Mock
// 	private OrderRepository orderRepository;
//
// 	@Mock
// 	private ItemRepository itemRepository;
//
// 	@Mock
// 	private ItemImageRepository itemImageRepository;
//
// 	@Mock
// 	private UserAccountRepository userAccountRepository;
//
//
// 	@InjectMocks
// 	private OrderService orderService;
//
// 	@BeforeEach
// 	void setUp() {
// 		// Mockito 초기화
// 		MockitoAnnotations.initMocks(this);
// 		// OrderService 생성
// 		orderService = new OrderService(orderRepository, itemRepository, itemImageRepository, userAccountRepository);
//
//
//
// 	}
//
// 	@Test
// 	void testCreateOrder() {
// 		// given
// 		Long itemId = 1L;
// 		Item item = new Item();
// 		item.setId(itemId);
// 		item.setItemAmount(10L);
// 		item.setPrice(10000L);
//
// 		String email = "test@test.com";
//
// 		OrderRequest orderRequest = new OrderRequest();
// 		orderRequest.setProductId(itemId);
// 		orderRequest.setAmount(3L);
//
// 		UserAccount userAccount = new UserAccount();
// 		userAccount.setEmail(email);
// 		userAccount.getAddress().setAddress1("서울시 강남구");
//
// 		given(itemRepository.findById(itemId)).willReturn(Optional.of(item));
// 		given(userAccountRepository.findByEmail(email)).willReturn(Optional.of(userAccount));
// 		given(orderRepository.save(any())).willReturn(new Order());
//
// 		// when
// 		Long orderId = orderService.createOrder(orderRequest, email);
//
// 		// then
// 		assertThat(orderId).isNotNull();
// 	}
//
// 	@Test
// 	void testCreateOrders() {
// 		// given
// 		Long itemId1 = 1L;
// 		Long itemId2 = 2L;
//
// 		Item item1 = new Item();
// 		item1.setId(itemId1);
// 		item1.setItemAmount(10L);
// 		item1.setPrice(10000L);
//
// 		Item item2 = new Item();
// 		item2.setId(itemId2);
// 		item2.setItemAmount(10L);
// 		item2.setPrice(20000L);
//
// 		String email = "test@test.com";
//
// 		List<OrderDto> orderDtoList = new ArrayList<>();
// 		orderDtoList.add(new OrderDto(1L,1L,1L,2L,0.0));
// 		orderDtoList.add(new OrderDto(2L,2L,2L,2L,0.0));
//
// 		UserAccount userAccount = new UserAccount();
// 		userAccount.setEmail(email);
// 		userAccount.getAddress().setAddress1("서울시 강남구");
//
// 		given(itemRepository.findById(itemId1)).willReturn(Optional.of(item1));
// 		given(itemRepository.findById(itemId2)).willReturn(Optional.of(item2));
// 		given(userAccountRepository.findByEmail(email)).willReturn(Optional.of(userAccount));
// 		given(orderRepository.save(any())).willReturn(new Order());
//
// 		// when
// 		Long orderId = orderService.createOrders(orderDtoList, email);
//
// 		// then
// 		assertThat(orderId).isNotNull();
//
// 	}
//
// 	@Test
// 	void testCancelOrder() {
// 		// given
// 		Long orderId = 1L;
// 		String email = "test@test.com";
// 		UserAccount userAccount = new UserAccount();
// 		userAccount.setEmail(email);
// 		Order order = new Order();
// 		order.setId(orderId);
// 		order.setUserAccount(userAccount);
// 		order.setOrderStatus(OrderStatus.ORDER);
//
// 		Delivery delivery = new Delivery();
// 		delivery.setDeliveryStatus(DeliveryStatus.DELIVERING);
//
// 		given(orderRepository.findById(orderId)).willReturn(Optional.of(order));
//
// 		// when & then
// 		assertThrows(OrderAlreadyInProcessException.class, () -> {
// 			orderService.cancelOrder(order, delivery);
// 		});
//
// 		// 주문 상태가 배송중이 아니라면 취소 가능
// 		order.setOrderStatus(OrderStatus.CANCEL);
// 		orderService.cancelOrder(order, delivery);
// 		assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
//
// 	}
//
// 	// @Test
// 	// void testSearchOrders() {
// 	// 	// given
// 	// 	String userAccountName = "testUser";
// 	// 	LocalDateTime startDate = LocalDateTime.of(2022, 1, 1, 0, 0, 0);
// 	// 	LocalDateTime endDate = LocalDateTime.of(2022, 12, 31, 23, 59, 59);
// 	// 	OrderStatus status = OrderStatus.ORDER;
// 	// 	OrderDto orderDto = OrderDto.fromEntity(order);
// 	// 	List<Order> orders = Arrays.asList(order);
// 	// 	Pageable pageable = PageRequest.of(0, 10);
// 	// 	when(orderRepository.searchOrders(any(String.class), any(LocalDateTime.class), any(LocalDateTime.class), any(OrderStatus.class)))
// 	// 		.thenReturn(orders);
// 	// 	when(orderRepository.findOrders(any(String.class), any(Pageable.class)))
// 	// 		.thenReturn((List<Order>)new PageImpl<>(orders));
// 	// 	when(orderRepository.countOrder(any(String.class)))
// 	// 		.thenReturn(1L);
// 	//
// 	// 	// when
// 	// 	List<OrderDto> found = orderService.searchOrders(userAccountName, startDate, endDate, status);
// 	// 	PageImpl<Order> page = (PageImpl<Order>) orderService.getOrders(userAccountName, pageable);
// 	//
// 	// 	// then
// 	// 	assertThat(found).hasSize(1);
// 	// 	assertThat(found.get(0)).isEqualTo(orderDto);
// 	// 	assertThat(page.getContent()).isEqualTo(orders);
// 	// 	assertThat(page.getTotalElements()).isEqualTo(1L);
// 	// }
// }
//
//
//

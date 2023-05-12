// package com.petkpetk.service.domain.shopping.service.cart;
//
// import java.util.Set;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.petkpetk.service.domain.user.constant.RoleType;
// import com.petkpetk.service.domain.shopping.constant.ItemStatus;
// import com.petkpetk.service.domain.shopping.entity.item.Item;
// import com.petkpetk.service.domain.shopping.repository.cart.CartRepository;
// import com.petkpetk.service.domain.shopping.repository.item.ItemRepository;
// import com.petkpetk.service.domain.shopping.service.order.OrderService;
// import com.petkpetk.service.domain.user.entity.UserAccount;
// import com.petkpetk.service.domain.user.entity.embedded.Address;
// import com.petkpetk.service.domain.user.repository.UserAccountRepository;
//
// @SpringBootTest
// @ActiveProfiles("test")
// @Transactional
// class CartServiceTest {
//
// 	@Autowired ItemRepository itemRepository;
//
// 	@Autowired CartRepository cartRepository;
//
// 	@Autowired CartItemRepository cartItemRepository;
//
// 	@Autowired UserAccountRepository memberRepository;
//
// 	@Autowired CartService cartService;
//
// 	@Autowired OrderService orderService;
//
//
//
// 	public Item saveItem(){
// 		Item item = new Item();
// 		item.setItemName("테스트 상품");
// 		item.setOriginalPrice(10000L);
// 		item.setPrice(10000L);
// 		item.setDiscountRate(0.0);
// 		item.setItemDetail("테스트 상품 상세 설명");
// 		item.setItemStatus(ItemStatus.SELL);
// 		item.setItemAmount(100L);
//
// 		return itemRepository.save(item);
// 	}
//
// 	public UserAccount saveUserAccount(){
// 		UserAccount member = new UserAccount();
// 		member.setEmail("test@test.com");
// 		member.setName("홍길동");
// 		member.setNickname("홍길");
// 		member.setPhoneNumber("01012341234");
// 		member.setAddress(new Address());
// 		member.setRoles(Set.of(RoleType.USER));
// 		return memberRepository.save(member);
// 	}
//
//
// 	@Test
// 	@DisplayName("장바구니 담기 테스트")
// 	public void addCart() {
// 		// given
// 		Item item = saveItem();
//
// 		UserAccount userAccount = saveUserAccount();
//
// 		CartItemDto cartItemDto = new CartItemDto();
// 		cartItemDto.setItemId(1L);
// 		cartItemDto.setItemCount(5L);
//
// 		System.out.println(" ********** **********아이템 갯수 **********"+cartItemDto.getItemCount());
// 		System.out.println(" ********** **********아이템 총액 **********" + cartItemDto.getCartId().getTotalPrice());
//
//
// 		// when
// 		// Long savedCartItemId = cartService.addCartItems(cartItemDto, userAccount.getEmail());
// 		//
// 		// // then
// 		// CartItem savedCartItem = cartItemRepository.findById(savedCartItemId).orElseThrow(EntityNotFoundException::new);
// 		// assertThat(savedCartItem.getItem().getId()).isEqualTo(item.getId());
// 		// assertThat(savedCartItem.getCart().getUserAccount().getId()).isEqualTo(userAccount.getId());
// 		// assertThat(savedCartItem.getItemCount()).isEqualTo(cartItemDto.getItemCount());
// 	}
//
// }
//
// // @Test
// // @DisplayName("장바구니 담기 테스트")
// // public void addCart(){
// // 	Item item = saveItem();
// // 	UserAccount member = saveUserAccount();
// //
// // 	CartItemDto cartItemDto = new CartItemDto();
// // 	cartItemDto.setItemCount(5L);
// // 	cartItemDto.setItemId(item.getId());
// //
// // 	Long addCartItems = cartService.addCartItems(cartItemDto, member.getEmail());
// // 	CartItem cartItem = cartItemRepository.findById(addCartItems)
// // 		.orElseThrow(EntityNotFoundException::new);
// //
// // 	assertEquals(item.getId(), cartItem.getItem().getId());
// // 	assertEquals(cartItemDto.getItemCount(), cartItem.getItemCount());
// // }
//

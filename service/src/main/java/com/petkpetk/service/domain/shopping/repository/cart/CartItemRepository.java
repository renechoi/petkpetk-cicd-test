package com.petkpetk.service.domain.shopping.repository.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petkpetk.service.domain.shopping.entity.cart.Cart;
import com.petkpetk.service.domain.shopping.entity.cart.CartItem;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	Optional<CartItem> findByCartIdAndItemId(Long cartId, Long itemId);

	/**
	 * CartItem 조회시 ItemImage가 representativeYn='Y'만 조회하도록 하는 Jpql 쿼리를 작성하는 부분에 있어서 처음 시도한 방식인
	 * // @Query("SELECT ci FROM CartItem ci JOIN ci.item i JOIN i.images ii WHERE ci.cart.id =
	 * :cartId AND ii.representativeImageYn = 'Y'")
	 * 방식은 CartItem 엔티티와 Item 엔티티 사이의 연관관계에서 images 필드에 대한 FetchType 설정이 LAZY로 되어 있어서
	 * images 필드가 실제로 사용되는 시점까지 DB에서 데이터를 가져오지 못한다.
	 * 즉, JPQL 쿼리에서 ii.representativeImageYn = 'Y' 조건은 images 필드가 로딩되지 않았을 때에는
	 * 적용되지 않으므로 모든 itemImage을 가져오게 된다.
	 * <p>
	 * 따라서, CartItem 엔티티에서 images 필드에 대한 FetchType을 EAGER로 변경하면 문제를 해결할 수 있다.
	 * 최종 코드 변경 사항은 아래와 같다.
	 */
	@Query("SELECT ci FROM CartItem ci JOIN FETCH ci.item i JOIN FETCH i.images ii WHERE ci.cart.id = :cartId AND ii.representativeImageYn = 'Y'")
	List<CartItem> findCartItemsByCartIdWithRepresentativeItemImage(@Param("cartId") Long cartId);
}

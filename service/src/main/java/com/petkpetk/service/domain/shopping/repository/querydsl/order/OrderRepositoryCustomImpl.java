package com.petkpetk.service.domain.shopping.repository.querydsl.order;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.petkpetk.service.domain.user.entity.UserAccount;
import org.springframework.util.StringUtils;

import com.petkpetk.service.domain.shopping.constant.OrderStatus;
import com.petkpetk.service.domain.shopping.entity.order.Order;
import com.petkpetk.service.domain.shopping.entity.order.QOrder;
import com.petkpetk.service.domain.user.entity.QUserAccount;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public OrderRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<Order> searchOrders(String userAccountName, LocalDateTime startDate, LocalDateTime endDate,
		OrderStatus status) {
		QOrder order = QOrder.order;
		QUserAccount userAccount = QUserAccount.userAccount;
		JPAQuery<Order> query = queryFactory.selectFrom(order)
			.leftJoin(order.userAccount, userAccount)
			.fetchJoin()
			.where((Predicate)statusEq(status),
				userAccountName(userAccountName),
				orderDateBetween(startDate, endDate))
			.orderBy(order.createdAt.desc(), order.id.desc());
		return query.fetch();
	}




	@Override
	public List<Order> findAllOrdersByUserAccountAndOrderStatus(UserAccount userAccount, OrderStatus orderStatus) {
		QOrder order = QOrder.order;
		JPAQuery<Order> query = queryFactory.selectFrom(order)
				.where(order.userAccount.eq(userAccount)
						.and((Predicate) order.orderStatus.eq(orderStatus)))
				.orderBy(order.orderStatus.desc(), order.id.desc());
		return query.fetch();
	}

//	@Override
//	public List<OrderDto> findAllOrdersByUserAccountAndOrderStatus(UserAccount userAccount, OrderStatus orderStatus, Pageable pageable) {
//		List<Order> orders = new ArrayList<>();
//		QOrder order = QOrder.order;
//		QUserAccount qUserAccount = QUserAccount.userAccount;
//		JPAQuery<Order> query = queryFactory.selectFrom(order)
//				.leftJoin(order.userAccount, qUserAccount).fetchJoin()
//				.where(order.userAccount.eq(qUserAccount)
//						.and((Predicate) order.orderStatus.eq(orderStatus)))
//				.orderBy(order.createdAt.desc(), order.id.desc())
//
//
//		orders = query.fetch();
//		return new PageImpl<>(orders,);
//	}

	private BooleanExpression statusEq(OrderStatus orderStatus) {
		if (orderStatus == null) {
			return null;
		}
		return QOrder.order.orderStatus.eq(orderStatus);
	}

	private BooleanExpression userAccountName(String userAccountName) {
		if (StringUtils.isEmpty(userAccountName)) {
			return null;
		}
		return QUserAccount.userAccount.name.like("%" + userAccountName + "%");
	}

	private BooleanExpression orderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
		if (startDate == null && endDate == null) {
			return null;
		}
		if (startDate == null) {
			return QOrder.order.createdAt.loe(endDate);
		}
		if (endDate == null) {
			return QOrder.order.createdAt.goe(startDate);
		}
		return QOrder.order.createdAt.between(startDate, endDate);
	}


}
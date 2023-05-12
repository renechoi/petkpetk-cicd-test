package com.petkpetk.service.domain.shopping.entity.delivery;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

import com.petkpetk.service.common.AuditingFields;
import com.petkpetk.service.domain.shopping.constant.DeliveryStatus;
import com.petkpetk.service.domain.shopping.entity.order.Order;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.entity.embedded.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Where(clause = "deleted_yn='N'")
public class Delivery extends AuditingFields {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "delivery_id")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	private Long payId;

	@OneToOne(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Order order;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;

	/** 배송지 */
	@Embedded
	private Address address;

	public Delivery(UserAccount userAccount,Long payId, DeliveryStatus deliveryStatus, Address address) {
		this.userAccount = userAccount;
		this.payId = payId;
		this.deliveryStatus = deliveryStatus;
		this.address = address;
	}

	public Delivery(Address address) {
		this.address = address;
	}

	public static Delivery of(UserAccount userAccount){
		return new Delivery(userAccount.getAddress());
	}

	public static Delivery of(UserAccount userAccount, Long payId, DeliveryStatus deliveryStatus, Address address) {
		return new Delivery(userAccount, payId, deliveryStatus,address);
	}

	public void cancelDelivery(){
		this.deliveryStatus = DeliveryStatus.CANCEL;
	}

}


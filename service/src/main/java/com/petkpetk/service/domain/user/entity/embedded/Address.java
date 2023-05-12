package com.petkpetk.service.domain.user.entity.embedded;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private String zipCode;

	private String address1;

	private String address2;

	private String addressEtc;

	public static Address of(String zipCode, String address1, String address2, String addressEtc){
		return new Address(zipCode, address1, address2, addressEtc);
	}

}


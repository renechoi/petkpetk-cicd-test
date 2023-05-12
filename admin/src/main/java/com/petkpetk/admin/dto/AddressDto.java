package com.petkpetk.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
	private String zipCode;
	private String address1;
	private String address2;
	private String addressEtc;
}
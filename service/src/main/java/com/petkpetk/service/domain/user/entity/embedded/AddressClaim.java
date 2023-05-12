package com.petkpetk.service.domain.user.entity.embedded;

import org.springframework.security.oauth2.core.oidc.AddressStandardClaim;

import lombok.Setter;

@Setter
public class AddressClaim implements AddressStandardClaim {
	private final Address address;

	public AddressClaim(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public String getFormatted() {
		return null;
	}

	@Override
	public String getStreetAddress() {
		return null;
	}

	@Override
	public String getLocality() {
		return null;
	}

	@Override
	public String getRegion() {
		return null;
	}

	@Override
	public String getPostalCode() {
		return null;
	}

	@Override
	public String getCountry() {
		return null;
	}
}
package com.petkpetk.admin.dto.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.petkpetk.admin.config.constant.RoleType;
import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.AdminAccountDto;
import com.petkpetk.admin.entity.AuditingFields;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminAccountPrincipal extends AuditingFields implements UserDetails, Serializable {

	private Long id;

	private String email;

	private String password;

	private String name;

	private Collection<? extends GrantedAuthority> roles;

	public static AdminAccountPrincipal from(AdminAccountDto dto) {
		AdminAccountPrincipal principal = EntityAndDtoConverter.convertToDto(dto, AdminAccountPrincipal.class);
		principal.setRoles(
			Set.of(RoleType.ADMIN).stream()
				.map(RoleType::getRoleName)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toUnmodifiableSet())
		);
		return principal;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

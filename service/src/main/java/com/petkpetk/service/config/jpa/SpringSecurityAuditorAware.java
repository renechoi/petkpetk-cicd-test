package com.petkpetk.service.config.jpa;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;

@EnableJpaAuditing
@Component
@Configuration
public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
			return Optional.empty();
		}

		return Optional.of(securityContext)
			.map(SecurityContext::getAuthentication)
			.filter(Authentication::isAuthenticated)
			.map(Authentication::getPrincipal)
			.map(UserAccountPrincipal.class::cast)
			.map(userAccountPrincipal ->
				userAccountPrincipal.getNickname() != null ? userAccountPrincipal.getNickname() :
				(userAccountPrincipal.getName() != null ? userAccountPrincipal.getName() :
					userAccountPrincipal.getEmail()));
	}
}

// package com.petkpetk.admin.config.security;
//
// import java.util.Optional;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.domain.AuditorAware;
// import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContext;
// import org.springframework.security.core.context.SecurityContextHolder;
//
// import com.petkpetk.admin.dto.security.AdminAccountPrincipal;
//
// @EnableJpaAuditing
// @Configuration
// public class JpaConfig {
//
// 	@Bean
// 	public AuditorAware<String> auditorAware() {
//
// 		SecurityContext securityContext = SecurityContextHolder.getContext();
//
// 		if (securityContext.getAuthentication() != null) {
// 			return () -> Optional.of(securityContext)
// 				.map(SecurityContext::getAuthentication)
// 				.filter(Authentication::isAuthenticated)
// 				.map(Authentication::getPrincipal)
// 				.map(AdminAccountPrincipal.class::cast)
// 				.map(AdminAccountPrincipal::getUsername);
// 		}
//
// 		return Optional::empty;
// 	}
// }

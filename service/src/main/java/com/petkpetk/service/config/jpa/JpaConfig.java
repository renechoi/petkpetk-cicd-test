package com.petkpetk.service.config.jpa;// package com.petkpetk.service.config.jpa;
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
// import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;
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
// 				.map(UserAccountPrincipal.class::cast)
// 				.map(UserAccountPrincipal::getName);
// 		}
//
// 		return Optional::empty;
// 	}
// }

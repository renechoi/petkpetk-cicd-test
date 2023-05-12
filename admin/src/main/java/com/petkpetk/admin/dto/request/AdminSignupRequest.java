package com.petkpetk.admin.dto.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.petkpetk.admin.config.constant.RoleType;
import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.entity.AdminAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminSignupRequest {

	@NotBlank(message = "이메일 형식으로 입력해주세요")
	@Email
	private String email;

	@Length(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요")
	private String password;

	@NotBlank(message = "이름을 입력해주세요")
	private String name;

	public AdminAccount toEntity() {
		AdminAccount adminAccount = EntityAndDtoConverter.convertToEntity(this, AdminAccount.class);
		adminAccount.setRoles(Set.of(RoleType.ADMIN));
		return adminAccount;
	}
}


package com.petkpetk.service.config.converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.petkpetk.service.domain.user.constant.RoleType;

@Converter
public class RoleTypeConverter implements AttributeConverter<Set<RoleType>, String> {

	private static final String DELIMETER = ",";

	@Override
	public String convertToDatabaseColumn(Set<RoleType> attribute) {
		return attribute.stream().map(RoleType::name).sorted().collect(Collectors.joining(DELIMETER));
	}

	@Override
	public Set<RoleType> convertToEntityAttribute(String dbData) {
		return Arrays.stream(dbData.split(DELIMETER)).map(RoleType::valueOf).collect(Collectors.toSet());
	}
}


package com.petkpetk.admin.config.converter;

import org.modelmapper.ModelMapper;

public class EntityAndDtoConverter {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static <T, U> U convertToDto(T entity, Class<U> dtoClass) {
		return modelMapper.map(entity, dtoClass);
	}

	public static <T, U> T convertToEntity(U dto, Class<T> entityClass) {
		return modelMapper.map(dto, entityClass);
	}
}

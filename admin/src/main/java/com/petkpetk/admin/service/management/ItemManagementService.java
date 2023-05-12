package com.petkpetk.admin.service.management;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.petkpetk.admin.config.properties.ProjectProperties;
import com.petkpetk.admin.dto.ItemDto;
import com.petkpetk.admin.dto.UserAccountDto;
import com.petkpetk.admin.dto.response.ItemApiResponse;
import com.petkpetk.admin.dto.response.ItemResponse;
import com.petkpetk.admin.dto.response.UserAccountApiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemManagementService {
	private final RestTemplate restTemplate;
	private final ProjectProperties projectProperties;

	public List<ItemDto> getItems() {
		URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.getPetkPetk().getUrl() + "/api/items")
			.queryParam("size", 10000)
			.build()
			.toUri();

		return Optional.ofNullable(restTemplate.getForObject(uri, ItemApiResponse.class))
			.orElseGet(ItemApiResponse::empty).getResult();
	}

	public ItemDto getItem(String id) {
		URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.getPetkPetk().getUrl() + "/api/items/" + id)
			.build()
			.toUri();

		return Optional.ofNullable(
				restTemplate.getForObject(uri, ItemDto.class))
			.orElseThrow(() -> new NoSuchElementException("상품이 없습니다 - userId: " + id));
	}

	public void deleteItem(String userId) {
		URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.getPetkPetk().getUrl() + "/api/items/" + userId)
			.build()
			.toUri();
		restTemplate.delete(uri);
	}

}


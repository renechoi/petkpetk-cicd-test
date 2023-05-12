package com.petkpetk.admin.service.management;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.petkpetk.admin.config.properties.LocalProperty;
import com.petkpetk.admin.dto.response.ShoppingNoticeImageUploadResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImgurImageService {
	private final RestTemplate restTemplate;

	public ShoppingNoticeImageUploadResponse uploadImage(File imageFile) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Client-ID " + LocalProperty.getInstance().getImgurClientId());

		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("image", new FileSystemResource(imageFile));

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<ShoppingNoticeImageUploadResponse> responseEntity = restTemplate.exchange(
			"https://api.imgur.com/3/image",
			HttpMethod.POST,
			requestEntity,
			ShoppingNoticeImageUploadResponse.class
		);

		return responseEntity.getBody();
	}
}

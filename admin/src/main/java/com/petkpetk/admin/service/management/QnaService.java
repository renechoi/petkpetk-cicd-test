package com.petkpetk.admin.service.management;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.petkpetk.admin.config.constant.AnswerStatus;
import com.petkpetk.admin.config.properties.ProjectProperties;
import com.petkpetk.admin.dto.QnaAnswerDto;
import com.petkpetk.admin.dto.QnaDto;
import com.petkpetk.admin.dto.UserAccountDto;
import com.petkpetk.admin.dto.response.QnaApiResponse;
import com.petkpetk.admin.dto.response.QnaResponse;
import com.petkpetk.admin.dto.response.UserAccountApiResponse;
import com.petkpetk.admin.entity.QnaAnswer;
import com.petkpetk.admin.exception.QnaAnswerStatusNotChangedException;
import com.petkpetk.admin.repository.QnaAnswerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {
	private final RestTemplate restTemplate;
	private final ProjectProperties projectProperties;
	private final QnaAnswerRepository qnaAnswerRepository;

	public List<QnaDto> qnas() {
		URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.getPetkPetk().getUrl() + "/api/userAsks")
			.queryParam("size", 10000)
			.build()
			.toUri();

		return Optional.ofNullable(restTemplate.getForObject(uri, QnaApiResponse.class))
			.orElseGet(QnaApiResponse::empty).get_embedded().getUserAsks();
	}

	public QnaDto qna(String id) {
		URI uri = UriComponentsBuilder.fromHttpUrl(projectProperties.getPetkPetk().getUrl() + "/api/userAsks/" + id)
			.build()
			.toUri();

		return Optional.ofNullable(
				restTemplate.getForObject(uri, QnaDto.class))
			.orElseThrow(() -> new NoSuchElementException("QNA가 없습니다 - id: " + id));
	}

	public void registerQnaAnswer(QnaAnswerDto qnaAnswerDto) {
		qnaAnswerRepository.save(QnaAnswer.from(qnaAnswerDto));
	}

}



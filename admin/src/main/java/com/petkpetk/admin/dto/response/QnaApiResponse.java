package com.petkpetk.admin.dto.response;

import java.util.List;

import com.petkpetk.admin.dto.QnaDto;
import com.petkpetk.admin.dto.UserAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaApiResponse {

	private Embedded _embedded;
	private QnaDto qna;

	public static QnaApiResponse empty() {
		return new QnaApiResponse(new Embedded(List.of()), new QnaDto());
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Embedded {
		private List<QnaDto> userAsks;
	}

}





package com.petkpetk.admin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaAnswerRequest {
	private String answer;
	private String userAskId;
}

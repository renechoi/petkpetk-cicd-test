package com.petkpetk.admin.controller.management;

import static com.petkpetk.admin.dto.api.ResponseDTO.*;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petkpetk.admin.dto.FaqDto;
import com.petkpetk.admin.dto.QnaAnswerDto;
import com.petkpetk.admin.dto.api.ResponseDTO;
import com.petkpetk.admin.dto.request.FaqRequest;
import com.petkpetk.admin.dto.request.QnaAnswerRequest;
import com.petkpetk.admin.dto.response.QnaResponse;
import com.petkpetk.admin.dto.response.UserAccountResponse;
import com.petkpetk.admin.service.management.QnaService;
import com.petkpetk.admin.service.management.UserAccountManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("management/qna")
public class QnaController {

	private final QnaService qnaService;

	@GetMapping()
	public String qnas(Model model) {
		model.addAttribute("qnas", qnaService.qnas()
			.stream()
			.map(QnaResponse::from)
			.collect(Collectors.toList()));

		return "main/management/qna";
	}

	@ResponseBody
	@GetMapping("/{id}")
	public QnaResponse qna(@PathVariable String id) {
		return QnaResponse.from(qnaService.qna(id));
	}

	@ResponseBody
	@PostMapping("/register")
	public ResponseDTO<Void> registerQnaAnswer(QnaAnswerRequest qnaAnswerRequest){
		qnaService.registerQnaAnswer(QnaAnswerDto.from(qnaAnswerRequest));
		return ok();
	}


}
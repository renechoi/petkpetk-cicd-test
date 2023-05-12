package com.petkpetk.service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {

	SUCCESS_CODE("성공적으로 작업을 수행 했습니다."),
	INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
	INVALID_REQUEST("잘못된 요청입니다."),

	USER_DUPLICATE("중복 회원이 존재합니다."),

	USER_NOT_FOUND("요청한 회원을 찾을 수 없습니다."),

	OAUTH2_PROVIDER_ERROR("소셜 로그인 진행중 오류가 발생했습니다. 다시 시도해주세요."),

	IMAGE_UPLOAD_FAILURE("이미지 업로드 중 오류 발생"),

	LOCAL_IMAGE_CONVERTING_FAILURE("로컬 이미지 변환 작업 중 오류 발생"),


	// 상품

	// 주문 및 장바구니
	NOT_FOUND_ORDER("주문 정보를 찾을 수 없습니다."),
	ALREADY_IN_CART("이미 장바구니에 존재합니다."),

	OUT_OF_STOCK("재고가 없습니다."),
	ITEM_NOT_FOUND("상품을 찾을 수 없습니다"),

	// 결제

	PAY_CANCEL("결제 진행 중 취소되었습니다."),
	PAY_FAILED("결제가 실패되었습니다."),

	//QnA 게시글 & 댓글

	//상품 리뷰
	ALREADY_REGISTERED_REVIEW("이미 등록된 리뷰가 있습니다."),
	NOT_MY_REVIEW("내가 작성한 리뷰가 아닙니다"),
	NOT_HAD_REVIEW("등록된 리뷰가 없습니다."),
	REVIEW_NOT_FOUND("리뷰를 찾을 수 없습니다."),

	// 쿠폰
	CAN_ONLY_ONE_COUPON("하나의 쿠폰만 사용할 수 있습니다."),
	NOT_FOUND_COUPON("존재하지 않는 쿠폰입니다."),
	ALREADY_USED_COUPON("이미 사용된 쿠폰입니다."),
	EXPIRED_COUPON("만료된 쿠폰입니다."),

	ORDER_ALREADY_IN_PROCESS("이미 배송 처리중입니다."),

	// 1:1 질문

	NOT_FOUND_ASK("존재하지 않는 질문입니다."),


	// 커뮤니티

	ARTICLE_NOT_FOUND("게시글을 조회할 수 없습니다"),
	THYMELEAF_RENDERING_FAILED("뷰 렌더링에 실패하였습니다.");

	private final String message;
}

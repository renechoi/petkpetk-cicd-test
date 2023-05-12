package com.petkpetk.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.petkpetk.admin.config.constant.ImgurImageError;
import com.petkpetk.admin.entity.ShoppingNoticeImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingNoticeImageUploadResponse {
	@JsonProperty("data")
	private ShoppingNoticeImage data;

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("status")
	private int status;

	@JsonProperty("error")
	private ImgurImageError error;

	public boolean isSuccess() {
		return success;
	}
}

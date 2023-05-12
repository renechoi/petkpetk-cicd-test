package com.petkpetk.service.domain.community.exception;

import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.PetkpetkServerException;

public class ArticleNotFoundException extends PetkpetkServerException {
	public ArticleNotFoundException() {
		super(StatusCode.ARTICLE_NOT_FOUND);
	}
}

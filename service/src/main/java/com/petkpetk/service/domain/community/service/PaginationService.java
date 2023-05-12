package com.petkpetk.service.domain.community.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class PaginationService {

	private static final int BAR_LENGTH = 5;

	public List<Integer> getPageBars(int currentPage, int totalPages) {

		int startNumber = getStartPage(currentPage);
		int endNumber = getEndPage(totalPages, startNumber);
		return IntStream.range(startNumber, endNumber).boxed().collect(Collectors.toList());
	}

	private static int getEndPage(int totalPages, int startNumber) {
		return Math.min(startNumber + BAR_LENGTH, totalPages);
	}

	private static int getStartPage(int currentPage) {
		return Math.max(currentPage - (BAR_LENGTH / 2), 0);
	}

}

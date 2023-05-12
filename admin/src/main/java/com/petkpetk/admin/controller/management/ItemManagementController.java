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

import com.petkpetk.admin.dto.api.ResponseDTO;
import com.petkpetk.admin.dto.response.ItemResponse;
import com.petkpetk.admin.service.management.ItemManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("management/shopping-items")
public class ItemManagementController {

	private final ItemManagementService itemManagementService;

	@GetMapping()
	public String Items(Model model) {
		model.addAttribute("items", itemManagementService.getItems()
			.stream()
			.map(ItemResponse::from)
			.collect(Collectors.toList()));

		return "main/management/item-management";
	}

	@ResponseBody
	@GetMapping("/{id}")
	public ItemResponse Item(@PathVariable String id) {
		return ItemResponse.from(itemManagementService.getItem(id));
	}

	@PostMapping("/{id}")
	public ResponseDTO<String> delete(@PathVariable String id) {
		itemManagementService.deleteItem(id);
		return ok("삭제 성공");

	}
}
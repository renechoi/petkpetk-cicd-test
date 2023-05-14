package com.petkpetk.service.common.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.petkpetk.service.domain.shopping.dto.item.ItemSearchDto;
import com.petkpetk.service.domain.shopping.dto.item.MainItemDto;
import com.petkpetk.service.domain.shopping.service.item.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @GetMapping("layout")
    public String test() {
        return "/layout/myPageLayout";
    }

    @GetMapping
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {

        PageRequest pageRequest = PageRequest.of(page.orElse(0),12);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageRequest);

        Long itemCount = itemService.getItemCount();

        model.addAttribute("itemCount", itemCount);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        return "main";

    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("login")
    public String loginPageForwarded() {
        return "login";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }
}

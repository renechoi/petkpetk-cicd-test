package com.petkpetk.admin.dto.response;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.petkpetk.admin.dto.ItemDto;
import com.petkpetk.admin.dto.UserAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemApiResponse {
    private List<ItemDto> result;

    private Long totalItemCount;

    public static ItemApiResponse empty() {
        return new ItemApiResponse(Collections.emptyList());
    }

    public ItemApiResponse(List<ItemDto> result) {
        this.result = result;
    }
}


package com.zinminthet.plantguardai.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private int page;
    private int limit;
    private int totalPages;
    private long totalItems;
}

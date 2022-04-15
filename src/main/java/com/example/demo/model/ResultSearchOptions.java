package com.example.demo.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ResultSearchOptions
{
    private String title_filter;
    private LocalDate start_date_filter;

    private Integer page;
    private Integer page_size;
}

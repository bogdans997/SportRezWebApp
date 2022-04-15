package com.example.demo.model.dto;

import lombok.*;

@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SportDTO
{
    private int sportId;
    String sportName;
}

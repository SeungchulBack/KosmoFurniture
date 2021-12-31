package com.kosmo.kosmofurniture.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchDto {
    private String section;
    private String search;
}

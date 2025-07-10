package com.spring.spring.dto;

import com.spring.spring.enums.CollectionCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionSearchDTO {
    private String title;
    private String curatorName;
    private CollectionCategory category;
    private String artPieceTitle;
    private  String artistName;
}

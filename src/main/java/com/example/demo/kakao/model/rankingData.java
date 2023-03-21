package com.example.demo.kakao.model;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "metaData")
public class rankingData {

    @ApiModelProperty(position = 1, value = "검색어")
    private String searchString;

    @ApiModelProperty(position = 2, value = "검색 횟수")
    private int searchCounting;

    @ApiModelProperty(position = 3, value = "검색 랭킹")
    private int searchRanking;

    @QueryProjection
    public rankingData(String searchString, int searchCounting) {
        this.searchString = searchString;
        this.searchCounting = searchCounting;
    }

}


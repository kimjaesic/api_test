package com.example.demo.kakao.model;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "metaData")
public class pageData {

    @ApiModelProperty(position = 1, value = "총 검색수")
    private int totalSearch;

    @ApiModelProperty(position = 2, value = "페이지번호")
    private int pageNum;


    public pageData(int totalSearch, int pageNum) {
        this.totalSearch = totalSearch;
        this.pageNum = pageNum;
    }
}


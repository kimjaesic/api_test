package com.test.domain.kakao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "kakaoRequestDTO")
public class kakaoRequestDTO {

    @ApiModelProperty(position = 1, value = "query", example = "기기괴괴", required = true)
    private String query;

    @ApiModelProperty(position = 2, value = "결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy", example = "accuracy")
    private String sort;

    @ApiModelProperty(position = 3, value = "결과 페이지 번호, 1~50 사이의 값, 기본 값 1", example = "1")
    private int page;

    @ApiModelProperty(position = 4, value = "한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10", example = "10")
    private int size;

}
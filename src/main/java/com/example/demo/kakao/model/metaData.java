package com.example.demo.kakao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
@Getter
@ApiModel(value = "metaData")
public class metaData {

    @ApiModelProperty(position = 1, value = "검색된 문서 수")
    private int totalCount;
    @ApiModelProperty(position = 2, value = "total_count 중 노출 가능 문서 수")
    private int pageableCount;
    @ApiModelProperty(position = 3, value = "현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음")
    private boolean isEnd;


}


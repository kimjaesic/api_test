package com.test.domain.kakao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@ApiModel(value = "documentListData")
public class documentListData {

    @ApiModelProperty(position = 1, value = "블로그 글 제목")
    private String title;
    @ApiModelProperty(position = 2, value = "블로그 글 요약")
    private String contents;
    @ApiModelProperty(position = 3, value = "블로그 글 URL")
    private String url;
    @ApiModelProperty(position = 4, value = "블로그의 이름")
    private String blogname;
    @ApiModelProperty(position = 5, value = "검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음")
    private String thumbnail;
    @ApiModelProperty(position = 6, value = "블로그 글 작성시간")
    private LocalDateTime datetime;


}


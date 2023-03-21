package com.example.demo.kakao.dto;

import com.example.demo.kakao.model.documentListData;
import com.example.demo.kakao.model.pageData;
import com.example.demo.kakao.model.rankingData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
@ApiModel(value = "kakaoResponseDTO")
public class kakaoResponseDTO {

    @ApiModelProperty(position = 1, value = "rankingdata")
    private List<rankingData> rankingData;

    @ApiModelProperty(position = 2, value = "document")
    private List<documentListData> documents;

    @ApiModelProperty(position = 3, value = "page")
    private pageData pageData;

    public kakaoResponseDTO(List<rankingData> rankingdata, List<documentListData> documents, pageData pageData) {
        this.rankingData = rankingdata;
        this.documents = documents;
        this.pageData = pageData;
    }
}


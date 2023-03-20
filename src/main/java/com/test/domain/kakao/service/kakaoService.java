package com.test.domain.kakao.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.domain.kakao.dto.kakaoRequestDTO;
import com.test.domain.kakao.dto.kakaoResponseDTO;
import com.test.domain.kakao.model.documentListData;
import com.test.domain.kakao.model.pageData;
import com.test.domain.kakao.model.rankingData;
import com.test.domain.kakao.repository.kakaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class kakaoService {

    private final String url = "https://dapi.kakao.com/v2/search/blog";
    private final String key = "c03562479898a4687853cc9e12ca41ba";
    private final kakaoRepository kakaoRepository;

    public  kakaoResponseDTO view(kakaoRequestDTO query, boolean isPass){

        if(query.getQuery().equals(""))
            return null;

        URI targetUrl = UriComponentsBuilder
                .fromUriString(url) //기본 url
                .queryParam("query", query.getQuery()) //인자
                .queryParam("page", query.getPage()) //인자
                .queryParam("size", query.getSize()) //인자
                .queryParam("sort", query.getSort()) //인자

                .build()
                .encode(StandardCharsets.UTF_8) //인코딩
                .toUri();

        RestTemplate restTpl = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key); //Authorization 설정
        HttpEntity entity = new HttpEntity<>(httpHeaders); // http entity에 header 담아줌

        ResponseEntity<Map> responseEntity = restTpl.exchange(targetUrl, HttpMethod.GET, entity, Map.class);
        List<documentListData> document = (List<documentListData>) responseEntity.getBody().get("documents");
        Object metas = responseEntity.getBody().get("meta");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(metas, Map.class);
        int total_counts = (int) map.get("total_count");

        //리스트
        List<rankingData> rankingData = kakaoRepository.view(query, isPass);

        pageData pagedata = new pageData(total_counts, query.getPage());

        kakaoResponseDTO returnData = new kakaoResponseDTO(rankingData, document, pagedata);
        return returnData;
    }
}

package com.example.demo.kakao.service;

import com.example.demo.kakao.model.naverDocumentData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.kakao.dto.kakaoRequestDTO;
import com.example.demo.kakao.dto.kakaoResponseDTO;
import com.example.demo.kakao.model.documentListData;
import com.example.demo.kakao.model.pageData;
import com.example.demo.kakao.model.rankingData;
import com.example.demo.kakao.repository.kakaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class kakaoService {

    private final String kakaoUrl = "https://dapi.kakao.com/v2/search/blog";
    private final String kakaoKey = "c03562479898a4687853cc9e12ca41ba";
    private final String naverUrl = "https://openapi.naver.com/v1/search/blog";
    private final String naverId = "IjoJp3Gx1QEfH27G3DXj";
    private final String naverSecret = "ZLMzMr9OLE";
    private final kakaoRepository kakaoRepository;

    public  kakaoResponseDTO view(kakaoRequestDTO query, boolean isPass){

        if(query.getQuery().equals(""))
            return null;

        URI targetUrl = UriComponentsBuilder
                .fromUriString(kakaoUrl) //기본 url
                .queryParam("query", query.getQuery()) //인자
                .queryParam("page", query.getPage()) //인자
                .queryParam("size", query.getSize()) //인자
                .queryParam("sort", query.getSort()) //인자
                .build()
                .encode(StandardCharsets.UTF_8) //인코딩
                .toUri();

        RestTemplate restTpl = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + kakaoKey); //Authorization 설정
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity<>(httpHeaders); // http entity에 header 담아줌

        List<documentListData> document = new ArrayList<>();
        int total_counts = 0;
        try
        {
            ResponseEntity<Map> responseEntity = restTpl.exchange(targetUrl, HttpMethod.GET, entity, Map.class);
            document = (List<documentListData>) responseEntity.getBody().get("documents");
            Object metas = responseEntity.getBody().get("meta");
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.convertValue(metas, Map.class);
            total_counts = (int) map.get("total_count");
        }
        catch (Exception e)
        {
            System.out.println(e);
            String sort = (query.getSort().equals("accuracy"))? "sim" : "date";
            URI targetUrlnaver = UriComponentsBuilder
                    .fromUriString(naverUrl) //기본 url
                    .queryParam("query", query.getQuery()) //인자
                    .queryParam("start", query.getPage()) //인자
                    .queryParam("display", query.getSize()) //인자
                    .queryParam("sort", sort) //인자
                    .build()
                    .encode(StandardCharsets.UTF_8) //인코딩
                    .toUri();

            RestTemplate restnaver = new RestTemplate();
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.set("X-Naver-Client-ID",naverId); //Authorization 설정
            httpHeader.set("X-Naver-Client-Secret",naverSecret); //Authorization 설정
            httpHeader.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity naver_entity = new HttpEntity<>(httpHeader); // http entity에 header 담아줌
            ResponseEntity<naverDocumentData> responseEntits = null;
            try
            {
                responseEntits = restnaver.exchange(targetUrlnaver, HttpMethod.GET, naver_entity, naverDocumentData.class);
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }

            List<naverDocumentData.SearchLocalItem> documents = responseEntits.getBody().getItems();

            for(naverDocumentData.SearchLocalItem result : documents)
            {
                documentListData documentlistdata = new documentListData(result.getTitle(), result.getDescription(), result.getLink(), result.getCategory(), null, null);
                document.add(documentlistdata);
            }
            total_counts = responseEntits.getBody().getTotal();
        }

        //리스트
        List<rankingData> rankingData = kakaoRepository.view(query, isPass);

        pageData pagedata = new pageData(total_counts, query.getPage());

        kakaoResponseDTO returnData = new kakaoResponseDTO(rankingData, document, pagedata);
        return returnData;
    }
}

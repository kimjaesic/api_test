package com.example.demo.kakao.controller;

import com.example.demo.kakao.dto.kakaoRequestDTO;
import com.example.demo.kakao.dto.kakaoResponseDTO;
import com.example.demo.kakao.service.kakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/kakao")
public class kakaoController {

    private final kakaoService kakaoService;

    @PostMapping("/search")
    public kakaoResponseDTO kakaoControlSearch(@RequestBody kakaoRequestDTO query) {
        return kakaoService.view(query, true);
    }
    @PostMapping("/paging")
    public kakaoResponseDTO kakaoControlpaging(@RequestBody kakaoRequestDTO query) {
        return kakaoService.view(query, false);
    }

}

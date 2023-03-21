package com.example.demo.kakao.repository;

import com.example.demo.kakao.model.QrankingData;
import com.querydsl.core.Tuple;
import com.example.demo.kakao.dto.kakaoRequestDTO;
import com.example.demo.kakao.model.rankingData;
import com.example.demo.global.entity.QSearch;
import com.example.demo.global.entity.Search;
import com.example.demo.global.repository.kakaoDataRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.global.entity.QSearch.search;


@Transactional
@RequiredArgsConstructor
@Repository
public class kakaoRepository {

    private final JPAQueryFactory queryFactory;

    private final kakaoDataRepository repository;

    public List<rankingData> view(kakaoRequestDTO kakao, boolean isPass) {
//        QSearch qSearch = new QSearch("p");
        if(isPass)
        {
            Search search = new Search();
            search.setSearchString(kakao.getQuery());
            search.setCreatedDt(LocalDateTime.now());
            repository.save(search);
        }

        List<rankingData> rankingdata =
                queryFactory.select(
                                new QrankingData(
                                        search.searchString,
                                        search.searchString.count().intValue()
                                )
                        )
                        .from(search.search)
                        .groupBy(search.searchString)
                        .orderBy(search.count().intValue().desc())
                        .fetch();

        for(int i = 0 ; i < rankingdata.size(); i++)
        {
            rankingdata.get(i).setSearchRanking(i + 1);
        }

        return rankingdata;
    }

}

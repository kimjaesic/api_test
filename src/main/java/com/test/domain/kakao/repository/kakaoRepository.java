package com.test.domain.kakao.repository;

import com.test.domain.kakao.dto.kakaoRequestDTO;
import com.test.domain.kakao.model.QrankingData;
import com.test.domain.kakao.model.rankingData;
import com.test.global.entity.QSearch;
import com.test.global.entity.Search;
import com.test.global.repository.kakaoDataRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@Transactional
@RequiredArgsConstructor
@Repository
public class kakaoRepository {

    private final JPAQueryFactory queryFactory;

    private final kakaoDataRepository repository;

    private static String _queryData = "";

    public List<rankingData> view(kakaoRequestDTO kakao, boolean isPass) {

        if(isPass)
        {
            Search search = new Search();
            search.setSearchString(kakao.getQuery());
            search.setCreatedDt(LocalDateTime.now());
            repository.save(search);
            _queryData = kakao.getQuery();
        }

        List<rankingData> rankingdata =
                queryFactory.select(
                                new QrankingData(
                                        QSearch.search.searchString,
                                        QSearch.search.searchString.count().intValue()
                                )
                        )
                        .from(QSearch.search)
                        .groupBy(QSearch.search.searchString)
                        .orderBy(QSearch.search.count().intValue().desc())
                        .fetch();
        for(int i = 0 ; i< rankingdata.size(); i++)
        {
            rankingdata.get(i).setSearchRanking(i + 1);
        }

        return rankingdata;
    }

}

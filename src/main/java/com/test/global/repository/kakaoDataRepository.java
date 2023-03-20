package com.test.global.repository;

import com.test.global.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;

public interface kakaoDataRepository extends JpaRepository<Search, Long> {

}

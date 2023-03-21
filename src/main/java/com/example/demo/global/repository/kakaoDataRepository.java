package com.example.demo.global.repository;

import com.example.demo.global.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;

public interface kakaoDataRepository extends JpaRepository<Search, Long> {

}

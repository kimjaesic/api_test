package com.example.demo.global.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "common_search")
@DynamicUpdate
@Entity
public class Search{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "search_sno")
    private Long sno;

    private String searchString;

    @ColumnDefault("0")
    @Comment("사용 여부 0:사용, 9:삭제")
    @Column(name = "use_yn")
    private int useYn; //0: 사용, 9: 미사용

    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @Builder
    public Search(String searchString, LocalDateTime now) {
        this.searchString = searchString;
        this.createdDt = now;
    }
}

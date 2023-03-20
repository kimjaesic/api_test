package com.test;

import com.test.global.entity.Search;
import com.test.global.repository.kakaoDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TESTApplicationTests {
    //<editor-fold defaultstate="collapsed" desc="delombok">
    @Autowired
    private kakaoDataRepository repositorys;
    //</editor-fold>

    @Test
    void contextLoads() {
    }

    @Test
    public void startTest() {
        System.out.println("Test");
        insertTest();
        selectTest();
    }

    @Test
    public void insertTest() {
        ArrayList<Search> orderModelList = new ArrayList<Search>();
        for (int i = 0; i < 10; i++) {
            Search search = Search.builder().searchString("test" + i).now(LocalDateTime.now()).build();
            System.out.println(">>> Create OrderModel: " + search.toString());
            orderModelList.add(search);
        }
        repositorys.saveAll(orderModelList);
        //when
        List<Search> orderModels = repositorys.findAll();
        System.out.println("####### " + orderModels);
        //then
        assertThat(orderModels.size()).isGreaterThan(0);
        assertThat(orderModels).isNotNull();
        System.out.println("insertTest");
    }

    @Test
    public void selectTest() {
        List<Search> orderModelList = new ArrayList<Search>();
        orderModelList = repositorys.findAll();
        for (int i = 0; i < orderModelList.size(); i++) {
            System.out.println(">>> " + orderModelList.get(i).getSearchString());
        }
        System.out.println("selectTest");
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public TESTApplicationTests() {
    }
    //</editor-fold>
}

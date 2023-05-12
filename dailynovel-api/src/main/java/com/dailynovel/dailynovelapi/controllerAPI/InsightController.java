package com.dailynovel.dailynovelapi.controllerAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.diary;
import com.dailynovel.dailynovelapi.service.InsightService;


@RestController("apiInstightController")
@RequestMapping("/insights1")
public class InsightControllerAPI {
    


    // 차트 부분
    @Autowired
     private InsightService service;


    @GetMapping("/chart")
        public List<diary> getList(
			
        @RequestParam(name = "feeling") String feeling,
        @RequestParam(name = "count", defaultValue = "0") Integer count
        )
        {		
        List<diary> List = service.getByFeeling();
        return List;

        }



 }

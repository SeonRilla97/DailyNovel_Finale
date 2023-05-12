package com.dailynovel.dailynovelapi.controllerAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.service.DiaryService;


@RestController("apiInstightController")
@RequestMapping("/insights1")
public class InsightController {
    


    // 차트 부분
    @Autowired
     private DiaryService service;


    @GetMapping("/chart")
        public List<Diary> getList(
			
        @RequestParam(name = "feeling") String feeling,
        @RequestParam(name = "count", defaultValue = "0") Integer count
        )
        {		
        List<Diary> List = service.getByFeeling(1);
        return List;

        }



 }

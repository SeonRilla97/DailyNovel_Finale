package com.dailynovel.dailynovelapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.service.DiaryService;


@RestController
@RequestMapping("/insights")
public class InsightController {
    


    // 차트 부분
    @Autowired
     private DiaryService service;

         


     @GetMapping("/chart")
     public Map<String, Object> feelingChart(Integer memberId){
    
        List<Diary> feeling = service.getByFeeling(memberId);
        Map<String,Object> map = new HashMap<>();
        map.put("feeling", feeling);
        
        
        return map;


     }

 }

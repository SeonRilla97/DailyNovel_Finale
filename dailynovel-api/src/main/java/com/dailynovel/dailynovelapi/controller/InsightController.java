package com.dailynovel.dailynovelapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.diary;
import com.dailynovel.dailynovelapi.service.InsightService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/insights")
public class InsightController {
    


    // 차트 부분
    @Autowired
     private InsightService service;


    @GetMapping("/chart")
         public Map<String, Object> feelingChart(Integer memberId){
        
            List<diary> feeling = service.getByFeeling(memberId);
            Map<String,Object> map = new HashMap<>();
            map.put("feeling", feeling);
            
            
            return map;
         }

     








 }

package com.dailynovel.dailynovelapi.controller;

import java.util.HashMap;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.service.InsightService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("insights")
public class InsightController {
    


    // 차트 부분
    @Autowired
    private InsightService service;


    @GetMapping("/chart/feeling")
    public Map<String, Object> feelingChart(Model model, HttpSession session){
        Map<String, object> dto = new HashMap<>();
        dto.put 
    }


}

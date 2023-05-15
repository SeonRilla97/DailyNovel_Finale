package com.dailynovel.dailynovelapi.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.service.DiaryService;

@RestController
@RequestMapping("diary")
public class DiaryController {


    @Autowired
    private DiaryService service;


    // 리스트 필터링 (월별로 Diary 불러옴)
    @GetMapping("filter")
    public String getListGroupingMonthly(
        @RequestParam(required = false) String feeling,
        @RequestParam(required = false) String weather,
        @RequestParam(required = false) Date date,
        @RequestParam(required = false) String order,
        @RequestParam(required = false) String collection,
        @RequestParam(required = false) String query
    ){
        Map<String,List<Diary>> list = service.getListGroupingMonthly(
            feeling,weather,date,order,collection,query
        );
        
        return "hi";
    }
}

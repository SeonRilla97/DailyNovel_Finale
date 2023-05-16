package com.dailynovel.dailynovelapi.controller;

import com.dailynovel.dailynovelapi.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


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
        @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") String date,
        @RequestParam(required = false) String order,
        @RequestParam(required = false) String collection,
        @RequestParam(required = false) String query
    ) throws UnsupportedEncodingException {
//        Map<String,List<Diary>> list = service.getListGroupingMonthly(
//            feeling,weather,date,order,collection,query
//        );

        // URL 디코딩
        Date realdate = new Date(date);


        System.out.println("====================");

        System.out.println(feeling);
        System.out.println(weather);
        System.out.println(date);
        System.out.println(query);
        System.out.println(order);

        
        return "hi";
    }
}

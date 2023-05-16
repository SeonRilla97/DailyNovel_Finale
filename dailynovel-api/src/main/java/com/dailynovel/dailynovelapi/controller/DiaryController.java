package com.dailynovel.dailynovelapi.controller;

import com.dailynovel.dailynovelapi.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        @RequestParam(required = false) String date,
        @RequestParam(required = false) String order,
        @RequestParam(required = false) String collection,
        @RequestParam(required = false) String query
    ) throws UnsupportedEncodingException {
//        Map<String,List<Diary>> list = service.getListGroupingMonthly(
//            feeling,weather,date,order,collection,query
//        );

//       ISO 8601 날짜형식으로 인코딩 된거 Decoder를 통해 LocalDate형식으로 변환
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE_TIME);

        System.out.println("====================");
        System.out.println(localDate);
        System.out.println(collection);
        System.out.println(feeling);
        System.out.println(weather);
        System.out.println(date);
        System.out.println(query);
        System.out.println(order);

        
        return "hi";
    }
}

package com.dailynovel.dailynovelapi.controller;



import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.mbentity.MbDiaryCollectionView;
import com.dailynovel.dailynovelapi.service.DiaryService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("diary")
public class DiaryController {


    @Autowired
    private DiaryService service;


    // 리스트 필터링 (월별로 Diary 불러옴)
    @GetMapping("filter")
    public  Map<String,List<MbDiaryCollectionView>> getListGroupingMonthly(
        @RequestParam(required = true ,defaultValue = "1") int memberId,
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


//        LocalDateTime
        //ISO 8601 날짜형식으로 인코딩 된거 Decoder를 통해 LocalDate형식으로 변환
        LocalDate localDate = null;
        if(date !=null)
             localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE_TIME);
             System.out.println(localDate);
        Map<String,List<MbDiaryCollectionView>> list = service.getListGroupingMonthly(
            memberId,feeling,weather,localDate,order,collection,query
        );
        System.out.println("어서오세요 컨트롤러 입니다 " +list);

        // URL 디코딩
        Date realdate = new Date(date);


        System.out.println("====================");

        System.out.println(feeling);
        System.out.println(weather);
        System.out.println(date);
        System.out.println(query);
        System.out.println(order);

        return list;
    }

    @GetMapping("get")
    public boolean get(){

        boolean qqq = service.isValid("1");

        return qqq;
    }

    @PostMapping ("write")
    public  void write(){

        int id = 25;

        int memberId = 1;
        String title = "하와이";
         String content = "가자";
         String weather = null;
         String feeling = null;
         String honesty = null;
         String tag = null;
//        Timestamp date = Timestamp.valueOf("2023-05-17 17:02:25");
        Timestamp date = null;

//        LocalDateTime
        //ISO 8601 날짜형식으로 인코딩 된거 Decoder를 통해 LocalDate형식으로 변환
//        LocalDateTime localDateTime = null;
//        if(date  != null)
//            localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
//        System.out.println(localDateTime);
//
//        Timestamp time = Timestamp.valueOf(localDateTime);

//        Timestamp time = Timestamp.valueOf(date);
//        Timestamp time = new Timestamp(date);


        Diary diary = new Diary(id, memberId,title,content,weather,feeling,honesty,tag,date);
        service.writeDiary(diary);

        System.out.println(diary.toString());

    }

}

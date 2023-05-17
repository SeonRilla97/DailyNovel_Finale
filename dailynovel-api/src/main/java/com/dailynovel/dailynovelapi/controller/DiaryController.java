package com.dailynovel.dailynovelapi.controller;

import com.dailynovel.dailynovelapi.mbentity.MbDiaryCollectionView;
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
        
        //ISO 8601 날짜형식으로 인코딩 된거 Decoder를 통해 LocalDate형식으로 변환
        LocalDate localDate = null;
        if(date !=null)
             localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE_TIME);
             System.out.println(localDate);
        Map<String,List<MbDiaryCollectionView>> list = service.getListGroupingMonthly(
            memberId,feeling,weather,localDate,order,collection,query
        );
        System.out.println("어서오세요 컨트롤러 입니다 " +list);

        // System.out.println("====================");
        // System.out.println(localDate);
        // System.out.println(collection);
        // System.out.println(feeling);
        // System.out.println(weather);
        // System.out.println(date);
        // System.out.println(query);
        // System.out.println(order);

        
        return list;
    }
}

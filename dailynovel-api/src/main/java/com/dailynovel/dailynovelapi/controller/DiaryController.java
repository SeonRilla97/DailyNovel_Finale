package com.dailynovel.dailynovelapi.controller;



import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.mbentity.MbDiaryCollectionView;
import com.dailynovel.dailynovelapi.service.DiaryService;


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

        return list;
    }

    @GetMapping("get")
    public boolean get(){

        boolean qqq = service.isValid("1");

        return qqq;
    }

    @GetMapping("write")
    public  void write(){

        Integer id = null;

        int memberId = 1;
        String title = "하와이";
         String content = "가자";
         String weather = "맑음";
         String feeling = "사랑";
         String honesty = "100";
         String tag = "영화";
         String date = "2023-05-17 17:02:25";

//        LocalDateTime
        //ISO 8601 날짜형식으로 인코딩 된거 Decoder를 통해 LocalDate형식으로 변환
        LocalDateTime localDateTime = null;
        if(date  != null)
            localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(localDateTime);

        Timestamp time = Timestamp.valueOf(localDateTime);



        // Diary diary = new Diary(null, memberId,title,content,weather,feeling,honesty,tag,time);

        // int tmp = service.writeDiary(diary);

    }


    @GetMapping("coor")
    public List<Object[]> coor(
        @RequestParam(name = "latitude", required = false) Double lat,
        @RequestParam(name = "longitude", required =  false) Double lng
    ){
        List<Object[]> list = service.getById();
        System.out.println("ok");
        return list;
    }

    // @PostMapping("latlng")
    // public List<Diary> latlng(@RequestBody Diary diary){
        
    //     return diary;
    // }



    }



    // @DeleteMapping("{id}")
    // public void remove(
    //         @PathVariable("id") int diaryId
    // ){
    //     service.removeDiary(diaryId);
    // }

package com.dailynovel.dailynovelapi.controller;



import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.mbentity.MbDiary;
import com.dailynovel.dailynovelapi.mbentity.MbDiaryCollectionView;
import com.dailynovel.dailynovelapi.mbentity.MbDiaryDisplayed;
import com.dailynovel.dailynovelapi.service.DiaryService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

import java.lang.reflect.Type;
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
        @RequestParam(required = true) int memberId,
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
        // list 담아오기
        Map<String,List<MbDiaryCollectionView>> list = service.getListGroupingMonthly(
            memberId,feeling,weather,localDate,order,collection,query
        );

        return list;
    }
    //공유가 한번이라도 된 적 있는 일기를 불러온다(join 4번)
    @GetMapping("displayed")
    public List<MbDiaryDisplayed> getDisplayedDiary(
        @RequestParam(required = true) int memberId,
        @RequestParam(required = false) Integer collectionId
    ){
        List<MbDiaryDisplayed> list=service.getSharedDiary(memberId,collectionId);
        return list;
    }
    // 멤버아이디 , 컬렉션 아이디

//    @GetMapping("get")
//    public boolean get(){
//
//        boolean qqq = service.isValid("1");
//
//        return qqq;
//    }


    @GetMapping("{id}")
    public MbDiary getDiary(
            @PathVariable("id") int diaryId
    ){
        return service.readDiary(diaryId);
    }

    @GetMapping("list")
    public List<MbDiary> getDiaryList(
    ){
        return service.readListDiary();
    }

    @PostMapping
    public  void write(
            @RequestBody MbDiary diary
    ){
//        Integer id = null;
//
//        int memberId = 1;
//        String title = "하와이2";
//        String content = "가자2";
//        String weather = null;
//        String feeling = null;
//        String honesty = null;
//        String tag = null;
////        Timestamp date = Timestamp.valueOf("2023-05-17 17:02:25");
//        LocalDateTime date = null;
//        Double lat = 38.0;
//        Double lon = 128.0;
//
//        MbDiary diray = new MbDiary
//                (id,
//                        memberId,
//                        title,
//                        content,
//                        weather,
//                        feeling,
//                        honesty,
//                        tag,
//                        date
//                        , lat
//                        , lon);

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

        System.out.println(diary.toString());
        System.out.println(diary.getClass().getName());
        service.writeDiary(diary);
    }

    @PutMapping
    public void edit(
            @RequestBody MbDiary diary
    ){

        System.out.println(diary.toString());
        int c = service.editDiary(diary);
    }

    @DeleteMapping("{id}")
    public void remove(
            @PathVariable("id") int diaryId
    ){
        service.removeDiary(diaryId);
    }
}
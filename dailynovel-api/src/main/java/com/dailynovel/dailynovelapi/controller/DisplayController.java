package com.dailynovel.dailynovelapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.DiaryLike;
import com.dailynovel.dailynovelapi.entity.DisplayView;
import com.dailynovel.dailynovelapi.service.DisplayService;

@RestController
@RequestMapping("display")
public class DisplayController {
    
    @Autowired
    private DisplayService service;

    

    @GetMapping("listall")
    public List<DisplayView> listAll(){
        return service.getByList();
    }
  
    // @GetMapping("listall")
    // public List<Diary> listAll(){
    //     return service.getByList();
    // }

    // @GetMapping("list")
    // public List<Diary> list(){
    //     return service.getBySharedDiaryList();
    // }
    
    @PostMapping("save")
    public String save(/* 회원id와 일기id를 받아오게 */){
        int memberId = 2;
        int diaryId = 17;
        service.insertLike(memberId, diaryId);
        return "세이브 완료";
    }

    @GetMapping("likeScan")
    public List<DiaryLike> IndivisualLikeList(){
        List<DiaryLike> likeList = service.getByLikeList(1); // 일단은 1번으로 고정해 놨지만 이후 값을 받아와야 함
        return  likeList;
    }
}

package com.dailynovel.dailynovelapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  
    /* 실험실 삭제할 가능성 농후함 31~ */
    // @GetMapping("listall2")
    // public List<DisplayView> listAll(
    //     @RequestParam(name = "page", defaultValue = "0") int page,
    //     @RequestParam(name = "limit", defaultValue = "10") int limit
    // ){
    //     // int offset = page * limit;
    //     List<DisplayView> result = service.getByListWithPagination(limit);
    //     return result;
    // }
    /* 실험실 삭제할 가능성 농후함 31~ */

    @PostMapping("addlike")
    public String addLike(
                    @RequestBody DiaryLike dl
                    // @RequestBody Map<String,Object> dl                      
                    ){
        int memberId = dl.getMemberId();
        int diaryId = dl.getDiaryId();
        service.insertLike(memberId, diaryId);
        System.out.println(dl);
        return "세이브 완료";
    }

    @PostMapping("deletelike")
    public String deleteLike(
                    @RequestBody DiaryLike dl
                    // @RequestBody Map<String,Object> dl                      
                    ){
        int memberId = dl.getMemberId();
        int diaryId = dl.getDiaryId();
        service.deleteLike(memberId, diaryId);
        System.out.println(dl);
        return "세이브 완료";
    }


    @GetMapping("likeScan")
    public List<DiaryLike> IndivisualLikeList(
                                @RequestParam(name = "mId") int memberId
                            ){
        List<DiaryLike> likeList = service.getByLikeList(memberId); // 일단은 1번으로 고정해 놨지만 이후 값을 받아와야 함
        return  likeList;
    }
}

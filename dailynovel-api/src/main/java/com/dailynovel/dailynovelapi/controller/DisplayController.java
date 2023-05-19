package com.dailynovel.dailynovelapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.DiaryLike;
import com.dailynovel.dailynovelapi.entity.DisplayView;
import com.dailynovel.dailynovelapi.entity.MemberFollow;
import com.dailynovel.dailynovelapi.service.DisplayService;

@RestController
@RequestMapping("display")
public class DisplayController {

// 컨트롤러는 입력-출력 역할을 한다
// 서비스에서 해당 기능에 관한 것을 수행한다.

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
        return "좋아요 추가 완료";
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
        return "좋아요 삭제 완료";
    }


    @GetMapping("likeScan")
    public List<DiaryLike> IndivisualLikeList(
                                @RequestParam(name = "mId") int memberId
                            ){
        List<DiaryLike> likeList = service.getByLikeList(memberId); // 일단은 1번으로 고정해 놨지만 이후 값을 받아와야 함
        return  likeList;
    }

    @PostMapping("subscribeRequest")  // 구독 요청
    public String subscribe(
                    @RequestBody MemberFollow mf
                    // @RequestBody Map<String,Object> dl                      
                    ){
        int followedId = mf.getFollowedId();
        int followId = mf.getFollowId();
        int result = service.updateFollow(followedId, followId);

        if(result==1)
            return "구독하기 완료";
        else  
            return "구독취소 완료";
    }


    @GetMapping("subscribeScan")  // 테스트용 내용 삭제해도 된다.
    // public List<MemberFollow> subscribeScan(
    public boolean subscribeScan(
                                @RequestParam(name = "mId") int memberId,
                                @RequestParam(name = "fId") int followedId
                            ){
        List<MemberFollow> subscribeList = service.getFollowedList(memberId); // 일단은 1번으로 고정해 놨지만 이후 값을 받아와야 함
        System.out.println(subscribeList);
        
        boolean isDuplicate = subscribeList.stream().anyMatch(item -> item.getFollowedId() == followedId);
        System.out.println(isDuplicate);

        // return  subscribeList;
        return  isDuplicate;
    }



}




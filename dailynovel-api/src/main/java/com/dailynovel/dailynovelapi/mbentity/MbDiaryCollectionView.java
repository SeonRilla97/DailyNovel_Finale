package com.dailynovel.dailynovelapi.mbentity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MbDiaryCollectionView {
    private int diaryId;
    private int memberId;
    private String collectionName;

    private String title;
    private String content;
    private String weather;
    private String feeling;
    private String honesty;
    private String tag;
    private LocalDateTime regDate;

    //공유 시작 날짜
    private LocalDateTime sharedEndDate;
    // 공유여부 null : 공유 안함 || 0 : 공유 완료 || 1 공유중
    private Boolean isShared;

    public void setIsShared(){
        //=====공유 날짜가 없다면 아직 공유된게 아님 => 리턴====(null)
        if(sharedEndDate == null){
            System.out.println("공유 안함!");
            return;
        }
        
        // ==============공유 됐다면=============
        // 1. 현재날짜 구하기
        LocalDateTime cur = LocalDateTime.now();
        
        // 2. 공유 만료일을 구하기 위해서 시작일에서 72시간 더하기
        LocalDateTime EndSharedDate = this.sharedEndDate.plusHours(72);
        
        // System.out.println("공유 만료일" + EndSharedDate + "공유완료됨? " + );
        
        if(cur.isAfter(EndSharedDate)){ 
            // 현재 날짜가 공유 만료일보다 미래라면 ==> 공유끝 (false)
            this.isShared = false;
            System.out.println("공유 끗");
        }else{
            // 현재 날짜가 공유 만료일보다 과거라면 ==> 공유 중 (true)
            this.isShared = true;
            System.out.println("공유중!");
        }
        
    }
}

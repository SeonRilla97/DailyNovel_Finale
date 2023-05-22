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
// 공유한적 있는 일기들 담아오는 Modal
public class MbDiaryDisplayed {
    private Integer diaryId;
    private int memberId;
    private String title;
    private String content;
    private LocalDateTime diaryRegDate;
    private int likes;
    
}

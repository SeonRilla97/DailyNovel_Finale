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
}

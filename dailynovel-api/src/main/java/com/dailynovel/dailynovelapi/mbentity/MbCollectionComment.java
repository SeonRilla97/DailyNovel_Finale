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
public class MbCollectionComment {
    private int id;
    private int collectionId;
    private int memberId;
    private String content;
    private LocalDateTime regDate;
    private Integer refId;
    private Integer depth;
}

package com.dailynovel.dailynovelapi.mbentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MbCollectionItems {
    private int diaryId;
    private int collectionId;
}

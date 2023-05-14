package com.dailynovel.dailynovelapi.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DiaryLikeId implements Serializable {

    private static final long serialVersionUID = 1L; // 이건 뭐지?

    private int memberId;
    private int diaryId;
}

package com.dailynovel.dailynovelapi.service;

import java.util.List;

import com.dailynovel.dailynovelapi.entity.DiaryLike;
import com.dailynovel.dailynovelapi.entity.DisplayView;

public interface DisplayService {
    List<DisplayView> getByList(); // 최신리스트 용

    /* 실험실 삭제할 가능성 농후함 11~*/
    // List<DisplayView> getByListWithPagination(int limit);
    /* 실험실 삭제할 가능성 농후함 11~*/

    void insertLike(int mId, int dId);
    void deleteLike(int mId, int dId);

    List<DiaryLike> getByLikeList(int memberId);
}

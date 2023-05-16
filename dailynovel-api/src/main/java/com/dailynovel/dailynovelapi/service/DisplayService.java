package com.dailynovel.dailynovelapi.service;

import java.util.List;

import com.dailynovel.dailynovelapi.entity.DiaryLike;
import com.dailynovel.dailynovelapi.entity.DisplayView;

public interface DisplayService {
    List<DisplayView> getByList(); // 최신리스트 용

    void insertLike(int mId, int dId);

    List<DiaryLike> getByLikeList(int memberId);
}

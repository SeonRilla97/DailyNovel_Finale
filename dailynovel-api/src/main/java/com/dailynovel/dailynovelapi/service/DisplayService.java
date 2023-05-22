package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dailynovel.dailynovelapi.entity.DiaryLike;
import com.dailynovel.dailynovelapi.entity.DisplayView;
import com.dailynovel.dailynovelapi.entity.MemberFollow;

public interface DisplayService {
    List<DisplayView> getByList(); // 최신리스트 용

    Page<DisplayView> getByList(int limit, int page);

    void insertLike(int mId, int dId);
    void deleteLike(int mId, int dId);

    List<DiaryLike> getByLikeList(int memberId);

    int updateFollow(int followedId, int followId);

    List<MemberFollow> getFollowedList(int followId); // 테스트만 하고 바로 삭제
}

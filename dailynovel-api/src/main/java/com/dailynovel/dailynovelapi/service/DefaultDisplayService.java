package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.DiaryLike;
import com.dailynovel.dailynovelapi.entity.DisplayView;
import com.dailynovel.dailynovelapi.repository.DiaryLikeRepository;
import com.dailynovel.dailynovelapi.repository.DisplayRepository;

@Service
public class DefaultDisplayService implements DisplayService {

    @Autowired
    private DisplayRepository repository;

    @Autowired
    private DiaryLikeRepository diaryRepository;

    @Override
    public List<DisplayView> getByList() {
        return repository.findAll();
    }

    @Override
    public void insertLike(int mId, int dId) {
        DiaryLike diarylike = new DiaryLike();
        diarylike.setMemberId(mId);
        diarylike.setDiaryId(dId);
        // diarylike.setMemberId(2);
        // diarylike.setDiaryId(17);

        diaryRepository.save(diarylike);
    }
}

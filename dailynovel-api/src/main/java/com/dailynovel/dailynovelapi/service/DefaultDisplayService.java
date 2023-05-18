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
    private DiaryLikeRepository diaryLikeRepository;

    @Override
    public List<DisplayView> getByList() {
        return repository.findAll();
    }

    /* 실험실 삭제할 가능성 농후함 27~*/
    // @Override
    // public List<DisplayView> getByListWithPagination(int limit) {
    //     return repository.findByPage(limit);
    // }
    /* 실험실 삭제할 가능성 농후함 27~*/


    @Override
    public void insertLike(int mId, int dId) {  // 좋아요 눌러서 태이블에 레코드(?) 삽입하기 위해 있는 거
        DiaryLike diarylike = new DiaryLike();
        diarylike.setMemberId(mId);
        diarylike.setDiaryId(dId);
        // diarylike.setMemberId(2);
        // diarylike.setDiaryId(17);

        diaryLikeRepository.save(diarylike);
    }

    @Override
    public void deleteLike(int mId, int dId) {
        DiaryLike diarylike = new DiaryLike();
        diarylike.setMemberId(mId);
        diarylike.setDiaryId(dId);
        diaryLikeRepository.delete(diarylike);
    }

    @Override
    public List<DiaryLike> getByLikeList(int memberId) { // 로그인 한 회원이 자신이 좋아요 누른 글을 표시하기 위해 있는 것
        List<DiaryLike> aa = diaryLikeRepository.findByMemberId(memberId);
        return aa;
    }





    

}

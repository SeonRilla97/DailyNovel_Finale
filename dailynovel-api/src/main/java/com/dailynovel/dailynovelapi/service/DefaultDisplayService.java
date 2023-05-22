package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.DiaryLike;
import com.dailynovel.dailynovelapi.entity.DisplayView;
import com.dailynovel.dailynovelapi.entity.MemberFollow;
import com.dailynovel.dailynovelapi.repository.DiaryLikeRepository;
import com.dailynovel.dailynovelapi.repository.DisplayRepository;
import com.dailynovel.dailynovelapi.repository.MemberFollowRepository;

@Service
public class DefaultDisplayService implements DisplayService {

    @Autowired
    private DisplayRepository repository;

    @Autowired
    private DiaryLikeRepository diaryLikeRepository;

    @Autowired
    private MemberFollowRepository followRepository;

    @Override
    public List<DisplayView> getByList() {
        return repository.findAll();
    }

    @Override
    public Page<DisplayView> getByList(int limit, int page) {
        Pageable pageable = PageRequest.of(page, limit);

        Page<DisplayView> list = repository.findAll(pageable);
        return list;
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
        List<DiaryLike> myFavorites = diaryLikeRepository.findByMemberId(memberId);
        return myFavorites;
    }

    @Override
    public int updateFollow(int followedId, int followId) {
        MemberFollow mf = new MemberFollow();
        mf.setFollowedId(followedId);
        mf.setFollowId(followId);

        List<MemberFollow> subscribeList = followRepository.findByFollowId(followId);
        
        boolean isDuplicate = subscribeList.stream().anyMatch(item -> item.getFollowedId() == followedId);

        if(!isDuplicate){ // 구독이 안 돼 있으면 추가하기
            followRepository.save(mf);

            return 1;  // 구독insert
        }
        else // 이미 구독 돼 있으면 구독 delete
            followRepository.delete(mf);
            return 0;  // 구독delete
    }



    @Override // 확인 후 바로 삭제
    public List<MemberFollow> getFollowedList(int followId) {
        List<MemberFollow> followedList = followRepository.findByFollowId(followId);
        return followedList;
    }





    



    

}

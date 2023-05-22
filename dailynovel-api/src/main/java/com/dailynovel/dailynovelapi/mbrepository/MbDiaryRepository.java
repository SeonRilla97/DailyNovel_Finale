package com.dailynovel.dailynovelapi.mbrepository;

import java.time.LocalDate;
import java.util.List;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.mbentity.MbDiary;
import org.apache.ibatis.annotations.Mapper;

import com.dailynovel.dailynovelapi.mbentity.MbDiaryCollectionView;
import com.dailynovel.dailynovelapi.mbentity.MbDiaryDisplayed;

@Mapper
public interface MbDiaryRepository {
  // 파일 존재를 위한 ㅋㄷㅋㄷ


  //모재영  CRUD 기본세팅
  //select
  List<MbDiary> findAll();
  MbDiary findById(Integer id);


  //create
  int insertNew(MbDiary diary);

  //update
  int update(MbDiary diary);

  //delete
  int delete(Integer id);



  List<MbDiaryCollectionView> getListByFiltering(
    int memberId,
    String feeling, 
    String weather,
    LocalDate localDate, 
    String order, 
    String collection, 
    String query
    );
List<MbDiaryDisplayed> findDisplayedById(int memberId, Integer collectionId,String sortStandard);
List<MbDiaryDisplayed> getListByColId(int memberId, int collectionId);
}

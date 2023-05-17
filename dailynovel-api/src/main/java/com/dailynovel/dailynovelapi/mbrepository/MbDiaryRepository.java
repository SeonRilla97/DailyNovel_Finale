package com.dailynovel.dailynovelapi.mbrepository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dailynovel.dailynovelapi.mbentity.MbDiaryCollectionView;

@Mapper
public interface MbDiaryRepository {
  // 파일 존재를 위한 ㅋㄷㅋㄷ

  List<MbDiaryCollectionView> getListByFiltering(
    int memberId,
    String feeling, 
    String weather,
    LocalDate localDate, 
    String order, 
    String collection, 
    String query
    );
}

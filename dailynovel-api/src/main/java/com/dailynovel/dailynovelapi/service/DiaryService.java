package com.dailynovel.dailynovelapi.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.mbentity.MbDiary;
import com.dailynovel.dailynovelapi.mbentity.MbDiaryCollectionView;

public interface DiaryService {
    public TreeMap<String, Integer> getByFeeling();
    public TreeMap<String, Integer>  getByTag();
    public TreeMap<String, Integer>  getByHonesty();
    public Map<String, List<MbDiaryCollectionView>> getListGroupingMonthly(int memberId, String feeling, String weather,
            LocalDate localDate, String order, String collection, String query);

    //모재영 : 테스트 용
    public boolean isValid(String id);

    public MbDiary readDiary(int id);
    public List<MbDiary> readListDiary();
    public int writeDiary(Diary diary);
    public int writeDiary(MbDiary diary);

    public int editDiary(MbDiary diary);

    public int removeDiary(int id);
}
 

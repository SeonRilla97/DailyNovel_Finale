package com.dailynovel.dailynovelapi.service;

import java.util.List;

import com.dailynovel.dailynovelapi.entity.Diary;

public interface DiaryService {
    List<Object[]> getByFeeling();
    List<Object[]> getByTag(Integer memberId);
    List<Object[]>  getByHonesty(Integer memberId);
}
 

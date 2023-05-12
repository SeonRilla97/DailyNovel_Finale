package com.dailynovel.dailynovelapi.service;

import java.util.List;

import com.dailynovel.dailynovelapi.entity.Diary;

public interface DiaryService {
    List<Diary> getByFeeling(Integer memberId);
    List<Diary> getByTag(Integer memberId);
    List<Diary>  getByHonesty(Integer memberId);
}
 
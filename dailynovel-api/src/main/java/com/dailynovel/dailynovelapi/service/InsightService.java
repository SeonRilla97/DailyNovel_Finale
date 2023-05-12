package com.dailynovel.dailynovelapi.service;

import java.util.List;

import com.dailynovel.dailynovelapi.entity.diary;

public interface InsightService {

        List<diary> getByFeeling(Integer memberId);
        List<diary> getByTag(Integer memberId);
        List<diary> getByHonesty(Integer memberId);
}

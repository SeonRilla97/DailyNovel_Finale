package com.dailynovel.dailynovelapi.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dailynovel.dailynovelapi.entity.Diary;

public interface DiaryService {
    public TreeMap<String, Integer> getByFeeling();
    public TreeMap<String, Integer>  getByTag();
    public TreeMap<String, Integer>  getByHonesty();
    Map<String, List<Diary>> getListGroupingMonthly(String feeling, String weather, Date date, String order,
            String collection, String query);
}
 

package com.dailynovel.dailynovelapi.service;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.repository.DiaryRepository;

@Service
public class DiaryDefaultService implements DiaryService{



    @Autowired
    private DiaryRepository repository;

    @Override
    public HashMap<String, Integer> getByFeeling(){
        List<Object[]> FeelingCount = repository.findByFeeling(null);
        Map<String, Integer> map = new HashMap<String, Integer>();

    // object라 형변환
        for(Object[] o : FeelingCount){
        String feeling = String.valueOf(o[0]);
        Integer count = Integer.parseInt(o[1].toString());
        map.put(feeling,count);
    }
    System.out.println(map);
    return (HashMap<String, Integer>) map;
    }



   

    @Override
    public TreeMap<String, Integer> getByHonesty() {
        List<Object[]> HonestyCount = repository.findByHonesty(null);
        Map<String, Integer> map = new TreeMap<String, Integer>(
            new Comparator<String>() {
                @Override
                public int compare(String a, String b) {
                    if (a.equals("0-20") && !b.equals("0-20")) {
                        return -1;
                    } else if (!a.equals("0-20") && b.equals("0-20")) {
                        return 1;
                    } else {
                        return a.compareTo(b);
                    }
                }
            }
        );
    
        for (Object[] o : HonestyCount) {
            String honestyRange = String.valueOf(o[0]);
            Integer count = Integer.parseInt(o[1].toString());
            map.put(honestyRange, count);
        }
    
        System.out.println(map);
        return (TreeMap<String, Integer>) map;
    }



    @Override
    public HashMap<String, Integer>  getByTag() {
        List<Object[]> TagCount = repository.findByTag(null);
        Map<String, Integer> map = new HashMap<String, Integer>();

        for(Object[] o : TagCount){
            String tag = String.valueOf(o[0]);
            Integer count = Integer.parseInt(o[1].toString());
            map.put(tag, count);
        }

        System.out.println(map);
        return (HashMap<String, Integer>) map;
    }

    @Override
    public Map<String, List<Diary>> getListGroupingMonthly(String feeling, String weather, Date date, String order,
            String collection, String query) {
                List<Diary> list = repository.getListWithFiltering(feeling,weather,date,order,collection,query);
                System.out.println(list);
        return null;
    }


    
}

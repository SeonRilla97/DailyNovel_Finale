package com.dailynovel.dailynovelapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dailynovel.dailynovelapi.mbrepository.MbDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.mbentity.MbDiaryCollectionView;
import com.dailynovel.dailynovelapi.repository.DiaryRepository;

@Service
public class DiaryDefaultService implements DiaryService{



    @Autowired
    private DiaryRepository repository;

    @Autowired
    private MbDiaryRepository mbRepository;

    @Override
    public TreeMap<String, Integer> getByFeeling(){
        List<Object[]> FeelingCount = repository.findByFeeling(null);
        Map<String, Integer> map = new TreeMap<String, Integer>();

    // object라 형변환
        for(Object[] o : FeelingCount){
        String feeling = String.valueOf(o[0]);
        Integer count = Integer.parseInt(o[1].toString());
        map.put(feeling,count);
    }
    System.out.println(map);
    return (TreeMap<String, Integer>) map;
    }



   

    @Override
    public TreeMap<String, Integer> getByHonesty() {
        List<Object[]> HonestyCount = repository.findByHonesty(null);
        Map<String, Integer> map = new TreeMap<String, Integer>();
    
        for (Object[] o : HonestyCount) {
            String honestyRange = String.valueOf(o[0]);
            Integer count = Integer.parseInt(o[1].toString());
            map.put(honestyRange, count);
        }
    
        System.out.println(map);
        return (TreeMap<String, Integer>) map;
    }



    @Override
    public TreeMap<String, Integer>  getByTag() {
        List<Object[]> TagCount = repository.findByTag(null);
        Map<String, Integer> map = new TreeMap<String, Integer>();

        for(Object[] o : TagCount){
            String tag = String.valueOf(o[0]);
            Integer count = Integer.parseInt(o[1].toString());
            map.put(tag, count);
        }

        System.out.println(map);
        return (TreeMap<String, Integer>) map;
    }





    // 필터링 시켜 일기 목록 들고오기
    @Override
    public Map<String, List<MbDiaryCollectionView>> getListGroupingMonthly(int memberId, String feeling, String weather,
            LocalDate localDate, String order, String collection, String query) {
            System.out.println("서비스단 실행!");
            System.out.println(order);

            //**년 ***월 으로 그룹화 시키는 Map 객체 */
            Map<String, List<MbDiaryCollectionView>> groupingList = new HashMap<>();
            //DB에서 불러온 일기 목록
            List<MbDiaryCollectionView> list = mbRepository.getListByFiltering(
                    memberId,feeling,weather, localDate, order, collection , query
                    );

            for(MbDiaryCollectionView diary : list){
                //년 추출
                String year = String.valueOf(diary.getRegDate().getYear());
                //월 추출
                String month = String.valueOf(diary.getRegDate().getMonthValue());
                // {}년 {}월 으로 그룹화 시키기 위해 String 생성 (Map의 Key가 될거임)
                String group = year + "년 "+ month+"월";
                System.out.println(group);
                if(groupingList.containsKey(group)){
                    //Map에 키가 있으면

                    //1. 해당 키의 값에 일기 집어넣기
                    groupingList.get(group).add(diary);
                }else{
                    //키가 없다면
                    // 1. ArrayList 생성
                    List<MbDiaryCollectionView> tmpList = new ArrayList<>();
                    // 2. ArrayList 에 일기 넣기
                    tmpList.add(diary);
                    // 3. Map에 Key와 Value로 생성
                    groupingList.put(group, tmpList);
                }
            }
        return groupingList;
    }



    
}


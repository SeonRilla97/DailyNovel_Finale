package com.dailynovel.dailynovelapi.service;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.*;


import com.dailynovel.dailynovelapi.mbentity.MbDiary;
import com.dailynovel.dailynovelapi.mbrepository.MbDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.mbentity.MbDiaryCollectionView;
import com.dailynovel.dailynovelapi.mbentity.MbDiaryDisplayed;
import com.dailynovel.dailynovelapi.repository.DiaryRepository;
import org.springframework.transaction.annotation.Transactional;

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


            //**년 ***월 으로 그룹화 시키는 Map 객체 생성*/
            Map<String, List<MbDiaryCollectionView>> groupingList = new LinkedHashMap<>();
            //========DB에서 불러온 일기 목록========
            List<MbDiaryCollectionView> list = mbRepository.getListByFiltering(
                    memberId,feeling,weather, localDate, order, collection , query
                    );
            //======불러온 일기를 Map객체에 넣기======
            for(MbDiaryCollectionView diary : list){
                // ================ 공유 상태 설정======null : 공유안함 true : 공유중 false: 공유완료
                diary.setIsShared();

                // System.out.println(diary);

                //년 추출
                String year = String.valueOf(diary.getRegDate().getYear());
                //월 추출
                String month = String.valueOf(diary.getRegDate().getMonthValue());
                // {}년 {}월 으로 그룹화 시키기 위해 String 생성 (Map의 Key가 될거임)
                String group = year + "년 "+ month+"월";

                // System.out.println(group);

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

    @Override
    public boolean isValid(String id) {
        Optional<Diary> diary = repository.findById(1);

        if(diary.isPresent())
            System.out.println(diary.toString());

        return false;
    }

    @Override
    public MbDiary readDiary(int id) {
        return mbRepository.findById(id);
    }

    @Override
    public List<MbDiary> readListDiary() {
        return mbRepository.findAll();
    }

    @Override
    public int writeDiary(Diary diary) {
        return 0;
    }


    @Override
    public int writeDiary(MbDiary diary) {
//        System.out.println(diary.get);

        int a = mbRepository.insertNew(diary);
        System.out.println(a);

//        repository.save(diary);
//        return 0;
//        Diary diary1 = repository.save(diary);
//        if (diary1 != null) {
//            System.out.println(diary1.toString());
//            return 1;
//        } else {
//            // 저장 실패 처리를 수행하거나 예외를 던질 수 있습니다.
//            return 0;
//        }
        return a;
    }

    @Override
    public int editDiary(MbDiary diary) {

        mbRepository.update(diary);

        return 0;
    }

    @Override
    public int removeDiary(int id) {

        int result = mbRepository.delete(id);

        return result;
    }





    @Override
    public List<MbDiaryDisplayed> getSharedDiary(int memberId, Integer collectionId) {
        // TODO Auto-generated method stub
        List<MbDiaryDisplayed> list = mbRepository.findDisplayedById(memberId, collectionId);
        return list;
    }
    @Override
    public List<Diary> getAllByMId() {
        List<Diary> list = repository.findAllByMId(null);
        return list;
    }





	@Override
	public List<Object[]> getById() {
		List<Object[]> list = repository.findByCoorList(null);
        return list;
	}
}


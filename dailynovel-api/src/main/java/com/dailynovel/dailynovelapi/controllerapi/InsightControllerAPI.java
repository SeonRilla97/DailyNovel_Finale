package com.dailynovel.dailynovelapi.controllerapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.service.DiaryService;

@RestController
@RequestMapping("insights1")
public class InsightControllerAPI {
    

    @Autowired
    private DiaryService service;


    @GetMapping("chart")
    public List<Object[]> getList(
        @RequestParam(name = "feeling", required = false) String feeling,
        @RequestParam(name = "count", required = false) Integer count
    ){
        List<Object[]> list = service.getByFeeling();
        System.out.println(list);
        System.out.println(list);
        return list;
    }
}
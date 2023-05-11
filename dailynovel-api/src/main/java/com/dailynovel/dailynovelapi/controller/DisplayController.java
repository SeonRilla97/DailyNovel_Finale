package com.dailynovel.dailynovelapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.service.DisplayService;

@RestController
@RequestMapping("display")
public class DisplayController {
    
    @Autowired
    private DisplayService service;

    @GetMapping("listall")
    public List<Diary> listAll(){
        return service.getByList();
    }

    @GetMapping("list")
    public List<Diary> list(){
        return service.getBySharedDiaryList();
    }
    
}

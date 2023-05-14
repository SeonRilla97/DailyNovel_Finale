package com.dailynovel.dailynovelapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.entity.DiaryLikeId;
import com.dailynovel.dailynovelapi.entity.DisplayView;
import com.dailynovel.dailynovelapi.repository.DiaryLikeRepository;
import com.dailynovel.dailynovelapi.service.DisplayService;

@RestController
@RequestMapping("display")
public class DisplayController {
    
    @Autowired
    private DisplayService service;

    @Autowired
    private DiaryLikeRepository repository;

    @GetMapping("listall")
    public List<DisplayView> listAll(){
        return service.getByList();
    }

    // @GetMapping("listall")
    // public List<Diary> listAll(){
    //     return service.getByList();
    // }

    // @GetMapping("list")
    // public List<Diary> list(){
    //     return service.getBySharedDiaryList();
    // }
    
    @PostMapping("like")
    public void like(){
        repository.save(2,9);
    }
}

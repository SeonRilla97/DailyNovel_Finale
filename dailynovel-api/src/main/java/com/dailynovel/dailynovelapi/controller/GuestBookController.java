package com.dailynovel.dailynovelapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.dailynovelapi.mbentity.MbGuestBookAll;
import com.dailynovel.dailynovelapi.service.GuestBookService;

@RestController
@RequestMapping("members/guestbooks")
public class GuestBookController {

  @Autowired
  private GuestBookService service;

  @GetMapping
  public ResponseEntity<List<MbGuestBookAll>> viewGuestBookAll(@RequestParam("id") int id) {
    List<MbGuestBookAll> list = service.viewGuestBookAll(id);
    // System.out.println(list);
    return new ResponseEntity<List<MbGuestBookAll>>(list, HttpStatus.OK);
  }

  @PostMapping("save")
  public int writeGuestBook(@RequestBody Map<String, Object> guestbook) {
    
    service.writeGuestBook(guestbook);
    return 1;
  }

  @PostMapping("comment")
  public int writeGuestBookCommnet(@RequestBody Map<String, Object> comment){

    service.writeGuestBookComment(comment);
    System.out.println("sucess");
    return 1;
  }

}

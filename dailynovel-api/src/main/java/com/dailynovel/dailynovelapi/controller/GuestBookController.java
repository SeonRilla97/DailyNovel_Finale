package com.dailynovel.dailynovelapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  // @PostMapping("list")
  // public ResponseEntity<List<MbGuestBookAll>> viewGuestBookAll() {
  // List<MbGuestBookAll> list = service.viewGuestBookAll();
  // // System.out.println(list);
  // return new ResponseEntity<List<MbGuestBookAll>>(list, HttpStatus.OK);
  // }

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

  // @PostMapping("all")
  // public ResponseEntity<List<GuestBook>> viewGuestbooks() {
  // List<GuestBook> list = service.viewGuestBooks();

  // return new ResponseEntity<List<GuestBook>>(list, HttpStatus.OK);
  // }

  // @PostMapping("comment")
  // public ResponseEntity<GuestBookComment> viewGuestbookComment() {
  // GuestBookComment comment = commentService.viewGuestBookComment();

  // return new ResponseEntity<GuestBookComment>(comment, HttpStatus.OK);
  // }
}

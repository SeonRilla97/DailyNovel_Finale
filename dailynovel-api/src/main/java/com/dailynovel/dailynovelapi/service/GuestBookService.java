package com.dailynovel.dailynovelapi.service;

import java.util.List;
import java.util.Map;

import com.dailynovel.dailynovelapi.entity.GuestBook;
import com.dailynovel.dailynovelapi.entity.GuestBookComment;
import com.dailynovel.dailynovelapi.mbentity.MbGuestBookAll;

public interface GuestBookService {

  // 방명록 보기 (주인, 방문자)
  public List<GuestBook> viewGuestBooks();

  // 방명록 답글 쓰기 (주인)
  public GuestBookComment writeGuestBookComment();

  // 방명록 쓰기 (방문자)
  public void writeGuestBook(Map<String, Object> guestbook);

  public List<MbGuestBookAll> viewGuestBookAll();

}

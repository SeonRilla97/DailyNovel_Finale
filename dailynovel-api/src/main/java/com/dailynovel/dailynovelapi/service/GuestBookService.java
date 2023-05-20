package com.dailynovel.dailynovelapi.service;

import java.util.List;
import java.util.Map;

import com.dailynovel.dailynovelapi.entity.GuestBook;
import com.dailynovel.dailynovelapi.entity.GuestBookComment;
import com.dailynovel.dailynovelapi.mbentity.MbGuestBookAll;

public interface GuestBookService {

  // 방명록 보기 (주인, 방문자)
  public List<MbGuestBookAll> viewGuestBookAll();

  public List<MbGuestBookAll> viewGuestBookAll(int id);

  // 방명록 쓰기 (방문자)
  public void writeGuestBook(Map<String, Object> guestbook);

  // 방명록 수정 (방문자)

  // 방명록 지우기 (주인, 방문자)

  // 방명록 답글 쓰기 (주인)
  public void writeGuestBookComment(Map<String, Object> comment);

  // 방명록 답글 수정 (주인)

  // 방명록 답글 삭제 (주인)

}

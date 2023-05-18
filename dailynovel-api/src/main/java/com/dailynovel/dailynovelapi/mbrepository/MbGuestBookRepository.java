package com.dailynovel.dailynovelapi.mbrepository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dailynovel.dailynovelapi.mbentity.MbGuestBook;
import com.dailynovel.dailynovelapi.mbentity.MbGuestBookAll;

@Mapper
public interface MbGuestBookRepository {

  public List<MbGuestBookAll> findAllbyId(Integer id);

  public void insertGuestbook(MbGuestBook guestbook);
}

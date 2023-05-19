package com.dailynovel.dailynovelapi.mbentity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MbGuestBookComment {

  private Integer id;
  private Integer guestbookId;
  private Integer memberId;
  private String content;
  private Timestamp regDate;

}

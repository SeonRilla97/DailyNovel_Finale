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
public class MbGuestBook {

  private Integer id;
  private Integer memberId;
  private Integer writerId;
  private String content;
  private Timestamp regDate;

}

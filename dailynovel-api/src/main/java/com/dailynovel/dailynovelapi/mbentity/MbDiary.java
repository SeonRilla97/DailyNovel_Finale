package com.dailynovel.dailynovelapi.mbentity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MbDiary {
  private Integer id;
  private int member_id;

  private String title;
  private String content;
  private String weather;
  private String feeling;
  private String honesty;
  private String tag;
  private LocalDateTime regDate;


  //모재영 위도경도 추가
  private double lat;
  private double lng;

}

package com.dailynovel.dailynovelapi.mbentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MbMember {
  private int id;
  private String email;
  private String nickname;
  private String image;
  private String message;
  private String phoneNumber;

}

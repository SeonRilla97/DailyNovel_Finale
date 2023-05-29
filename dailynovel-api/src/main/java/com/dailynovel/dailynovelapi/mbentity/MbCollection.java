package com.dailynovel.dailynovelapi.mbentity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MbCollection {
    private int id;
    private int memberId;
    private String name;
    private LocalDateTime createDate;
}

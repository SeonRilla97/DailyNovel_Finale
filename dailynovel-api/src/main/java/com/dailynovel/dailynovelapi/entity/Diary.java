package com.dailynovel.dailynovelapi.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Diary {

    //변겅자 모재영 null 받기위해서 바꿈
    @Id
    private Integer id;
    @Column(name="member_id")
    private int memberId;

    private String title;
    private String content;
    private String weather;
    private String feeling;
    private String honesty;
    private String tag;
    @Column(name="reg_date")
    private Timestamp date;

//    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY)
//    private List<DiaryCollection> collections;
}

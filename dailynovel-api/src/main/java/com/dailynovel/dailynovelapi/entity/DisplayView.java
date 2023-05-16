package com.dailynovel.dailynovelapi.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "display_3days")
public class DisplayView {
    @Id
    private int id;
    @Column(name="member_id")
    private int memberId;
    private String title;
    private String content;
    private String weather;
    private String feeling;
    private String honesty;
    private String tag;
    @Column(name="reg_date")
    private Date date;
    @Column(name="like_count")
    private int like;
    private String image;
    private String nickname;

}

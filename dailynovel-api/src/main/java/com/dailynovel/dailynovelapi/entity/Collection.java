package com.dailynovel.dailynovelapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "collection")
public class Collection {
    @Id
    private int id;
    @Column(name="member_id")
    private int memberId;
    private String name;
    @Column(name="create_date")
    private LocalDate createDate;

//    @OneToMany(mappedBy = "collection")
//    private List<DiaryCollection> diarys;

}

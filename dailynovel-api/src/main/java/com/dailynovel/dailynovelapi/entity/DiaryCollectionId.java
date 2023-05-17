package com.dailynovel.dailynovelapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DiaryCollectionId implements Serializable {
        @Column(name = "diary_id")
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "diary_id")
        private Diary diary;

        @Column(name = "collection_id")
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "collection_id")
        private Collection collection;
}

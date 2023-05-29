package com.dailynovel.dailynovelapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "collection_items")
//@IdClass(DiaryCollectionId.class)
public class DiaryCollection {
//    DiaryCollectionId id;


    private Diary diary;
    private Collection collection;
}

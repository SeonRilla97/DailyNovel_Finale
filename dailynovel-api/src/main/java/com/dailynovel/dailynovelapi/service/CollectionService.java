package com.dailynovel.dailynovelapi.service;

import com.dailynovel.dailynovelapi.entity.Collection;
import com.dailynovel.dailynovelapi.mbentity.MbCollection;

import java.util.List;

public interface CollectionService {
    public List<Collection>  listAll(int MemberId);
    public Boolean add(MbCollection collection);
}

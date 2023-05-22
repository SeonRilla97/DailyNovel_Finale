package com.dailynovel.dailynovelapi.service;

import com.dailynovel.dailynovelapi.mbentity.MbCollection;

import java.util.List;

public interface CollectionService {
    public List<MbCollection>  listAll(int memberId);
    public Boolean add(MbCollection collection);
    public Boolean update(MbCollection collection);
}

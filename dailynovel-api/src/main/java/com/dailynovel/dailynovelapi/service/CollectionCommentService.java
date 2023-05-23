package com.dailynovel.dailynovelapi.service;

import java.util.List;

import com.dailynovel.dailynovelapi.mbentity.MbCCommentMemberView;
import com.dailynovel.dailynovelapi.mbentity.MbCollectionComment;

public interface CollectionCommentService {

    List<MbCCommentMemberView> getList(MbCCommentMemberView comment);

    Boolean update(MbCollectionComment comment);

    Boolean delete(int commentId);

    Boolean register(MbCollectionComment comment);
    
}

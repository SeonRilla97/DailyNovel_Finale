package com.dailynovel.dailynovelapi.mbrepository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dailynovel.dailynovelapi.mbentity.MbCCommentMemberView;
import com.dailynovel.dailynovelapi.mbentity.MbCollectionComment;

@Mapper
public interface MbCollectionCommentRepository {

    List<MbCCommentMemberView> findAllByObj(MbCCommentMemberView comment);

    Boolean update(MbCollectionComment comment);

    Boolean delete(int commentId);

    Boolean register(MbCollectionComment comment);
    
}

package com.dailynovel.dailynovelapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.dailynovelapi.mbentity.MbCCommentMemberView;
import com.dailynovel.dailynovelapi.mbentity.MbCollectionComment;
import com.dailynovel.dailynovelapi.mbrepository.MbCollectionCommentRepository;
@Service
public class DefaultCollectionCommentService implements CollectionCommentService{
    
    @Autowired
    private MbCollectionCommentRepository repository ;
    
    @Override
    public List<MbCCommentMemberView> getList(MbCCommentMemberView comment) {
        List<MbCCommentMemberView> list = repository.findAllByObj(comment);
        return list;
    }

    @Override
    public Boolean update(MbCollectionComment comment) {
        Boolean isSuccess = repository.update(comment);
        return isSuccess;
    }

    @Override
    public Boolean delete(int commentId) {
        Boolean isSuccess = repository.delete(commentId);
        Boolean deletereply = repository.deleteAllByRefId(commentId);
        return (isSuccess &&deletereply);
    }

    @Override
    public Boolean register(MbCollectionComment comment) {
        Boolean isSuccess = repository.register(comment);
        return isSuccess;
    }
    
}

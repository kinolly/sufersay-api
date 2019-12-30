package com.sufersay.demo.service;

import com.sufersay.demo.bean.Comment;
import com.sufersay.demo.dao.CommentDao;

import com.sufersay.demo.returnItem.commentItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public boolean createComment(Comment comment){
        boolean flag = false;
        try{
            commentDao.createComment(comment);
            flag = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<commentItem> getCommentByUserId(int userId){
       return commentDao.getCommentByUserId(userId);
    }
    @Override
    public List<commentItem> getCommentByPostingId(int postingId){
        return commentDao.getCommentByPostingId(postingId);
    }
}

package com.sufersay.demo.service;

import com.sufersay.demo.bean.Comment;
import com.sufersay.demo.returnItem.commentItem;

import java.util.List;


public interface CommentService {

    //创建评论
    boolean createComment(Comment comment);

    //获取评论列表
    List<commentItem> getCommentByUserId(int userId);
    List<commentItem> getCommentByPostingId(int postingId);
}

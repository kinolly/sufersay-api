package com.sufersay.demo.service;

import com.sufersay.demo.bean.Posting;
import com.sufersay.demo.returnItem.postItem;


import java.util.List;


public interface PostService {

    //创建帖子
    boolean createPost(Posting post);

    //获取帖子
    List<postItem> getPostByUserId(int userId);
    List<postItem> getPostByTime();
    List<postItem> getPostByHot();
    List<postItem> getPostByKeyword(String keyword);
    List<postItem> getPostById(int id);
    List<postItem> getPostByCollection(int userId);

}

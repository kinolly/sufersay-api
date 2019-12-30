package com.sufersay.demo.service;

import com.sufersay.demo.bean.Posting;
import com.sufersay.demo.dao.PostDao;
import com.sufersay.demo.returnItem.postItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;

    @Override
    public boolean createPost(Posting post){
        boolean flag = false;
        try{
            postDao.createPost(post);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @Override
    public List<postItem> getPostByUserId(int userId){

        return postDao.getPostByUserId(userId);
    }
    @Override
    public List<postItem> getPostByTime(){
        return postDao.getPostByTime();
    }
    @Override
    public List<postItem> getPostByHot(){
        return postDao.getPostByHot();
    }
    @Override
    public List<postItem> getPostByKeyword(String keyword){
        return postDao.getPostByKeyword(keyword);
    }
    @Override
    public List<postItem> getPostById(int id){
        return postDao.getPostById(id);
    }
    @Override
    public List<postItem> getPostByCollection(int userId){
        return postDao.getPostByCollection(userId);
    }
}

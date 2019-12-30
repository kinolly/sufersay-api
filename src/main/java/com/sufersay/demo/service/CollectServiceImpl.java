package com.sufersay.demo.service;

import com.sufersay.demo.bean.Coll;
import com.sufersay.demo.dao.CollectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl implements CollectService{
    @Autowired
    private CollectDao collectDao;

    @Override
    public boolean createColl(Coll coll){
        boolean flag = false;
        try{
            collectDao.createColl(coll);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @Override
    public boolean deleColl(int id){
        boolean flag = false;
        try {
            collectDao.deleColl(id);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}

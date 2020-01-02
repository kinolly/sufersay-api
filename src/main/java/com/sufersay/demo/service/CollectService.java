package com.sufersay.demo.service;

import com.sufersay.demo.bean.Coll;

public interface CollectService {
    boolean createColl(Coll coll);
    boolean isExist(int userId,int postingId);
    boolean deleColl(int id);
    boolean update(int postingId);
}

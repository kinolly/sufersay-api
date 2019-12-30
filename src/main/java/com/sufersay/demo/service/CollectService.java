package com.sufersay.demo.service;

import com.sufersay.demo.bean.Coll;

public interface CollectService {
    boolean createColl(Coll coll);

    boolean deleColl(int id);
}

package com.sufersay.demo.controller;

import com.sufersay.demo.bean.Coll;
import com.sufersay.demo.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/api/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 收藏发布/取消
     *
     * @param userId        用户ID 创建时传入
     * @param postingId     帖子ID 创建时传入
     * @param collectionId  收藏id 取消时传入
     * @param action        发布/取消收藏  2/1
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public boolean collectAction(@RequestParam(name = "userId", required = false, defaultValue = "") Integer userId,
                                 @RequestParam(name = "postingId", required = false, defaultValue = "")Integer postingId,
                                 @RequestParam(name = "collectionId", required = false, defaultValue = "")Integer collectionId,
                                 @RequestParam(name = "action",required = true, defaultValue = "")Integer action){
        boolean flag=false;

        if(action == 2){
            Coll coll = new Coll();
            Date date = new Date();
            coll.setUserId(userId);
            coll.setPostingId(postingId);
            coll.setCreateTime(new Timestamp(date.getTime()));
            flag=collectService.createColl(coll);
        }else if(action == 1){
            flag=collectService.deleColl(collectionId);
        }
        return flag;
    }

}

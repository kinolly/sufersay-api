package com.sufersay.demo.dao;

import com.sufersay.demo.bean.Coll;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Title:CollectDao
 * Description:收藏数据处理接口
 * */
@Repository
@Mapper
public interface CollectDao {
    @Insert("insert into collection(posting_id,user_id,create_time)" +
            " values (#{postingId},#{userId},#{createTime})")
    void createColl(Coll coll);

    @Delete("delete from collection where _id=#{id}")
    void deleColl(int id);
}

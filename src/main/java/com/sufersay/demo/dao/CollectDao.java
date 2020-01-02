package com.sufersay.demo.dao;

import com.sufersay.demo.bean.Coll;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("select _id from collection where posting_id=#{postingId} and user_id=#{userId}")
    List<Integer> isExist(int postingId,int userId);

    @Update("update posting set collect_num=collect_num+1 where _id=#{postingId}")
    void update(int postingId);

    @Delete("delete from collection where _id=#{id}")
    void deleColl(int id);
}

package com.sufersay.demo.dao;
import com.sufersay.demo.returnItem.postItem;
import org.apache.ibatis.annotations.*;
import com.sufersay.demo.bean.Posting;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Title:PostDao
 * Description:帖子数据处理接口
 * */
@Repository
@Mapper
public interface PostDao {
    @Insert("insert into posting(user_id,post_time,comment_num,collect_num,content,post_state)" +
            " values (#{userId},#{postTime},#{commentNum},#{collectNum},#{content},#{postState})")
    void createPost(Posting post);

    @Select("select posting._id,posting.post_time,user.username,posting.content,posting.comment_num,posting.collect_num,GROUP_CONCAT(collection._id) c_id" +
            " from posting LEFT JOIN collection on posting._id = collection.posting_id" +
            " LEFT JOIN user on posting.user_id = user._id WHERE posting.user_id=#{id} GROUP BY posting._id")
        @Results({
                @Result(column="_id",property="id"),
                @Result(column="post_time",property="postTime"),
                @Result(column="username",property="userName"),
                @Result(column="comment_num",property="commentNumber"),
                @Result(column="collect_num",property="collectNumber"),
                @Result(column="c_id",property="collectionId")
                        })
    List<postItem> getPostByUserId(int id);

    @Select(value = "select posting._id,posting.post_time,user.username,posting.content,posting.comment_num,posting.collect_num,collection._id c_id" +
            " from posting LEFT JOIN collection on posting._id = collection.posting_id" +
            " LEFT JOIN user on posting.user_id = user._id ORDER BY posting.post_time DESC")
    @Results({
            @Result(column="_id",property="id"),
            @Result(column="post_time",property="postTime"),
            @Result(column="username",property="userName"),
            @Result(column="comment_num",property="commentNumber"),
            @Result(column="collect_num",property="collectNumber"),
            @Result(column="c_id",property="collectionId")
    })
    List<postItem> getPostByTime();

    //按评论多少定义热度
    @Select(value = "select posting._id,posting.post_time,user.username,posting.content,posting.comment_num,posting.collect_num,collection._id c_id" +
            " from posting LEFT JOIN collection on posting._id = collection.posting_id" +
            " LEFT JOIN user on posting.user_id = user._id ORDER BY comment_num DESC")
    @Results({
            @Result(column="_id",property="id"),
            @Result(column="post_time",property="postTime"),
            @Result(column="username",property="userName"),
            @Result(column="comment_num",property="commentNumber"),
            @Result(column="collect_num",property="collectNumber"),
            @Result(column="c_id",property="collectionId")
    })
    List<postItem> getPostByHot();

    //按收藏查询
    @Select(value = "select posting._id,posting.post_time,user.username,posting.content,posting.comment_num,posting.collect_num,collection._id c_id" +
            " from posting LEFT JOIN collection on posting._id = collection.posting_id" +
            " LEFT JOIN user on posting.user_id = user._id WHERE posting._id = collection.posting_id and collection.user_id=#{userId}")
    @Results({
            @Result(column="_id",property="id"),
            @Result(column="post_time",property="postTime"),
            @Result(column="username",property="userName"),
            @Result(column="comment_num",property="commentNumber"),
            @Result(column="collect_num",property="collectNumber"),
            @Result(column="c_id",property="collectionId")
    })
    List<postItem> getPostByCollection(int userId);

    //按关键字查询
    @Select(value = "select posting._id,posting.post_time,user.username,posting.content,posting.comment_num,posting.collect_num,collection._id c_id" +
            " from posting LEFT JOIN collection on posting._id = collection.posting_id" +
            " LEFT JOIN user on posting.user_id = user._id WHERE content LIKE '%${keyword}%'")
    @Results({
            @Result(column="_id",property="id"),
            @Result(column="post_time",property="postTime"),
            @Result(column="username",property="userName"),
            @Result(column="comment_num",property="commentNumber"),
            @Result(column="collect_num",property="collectNumber"),
            @Result(column="c_id",property="collectionId")
    })
    List<postItem> getPostByKeyword(@Param("keyword")String keyword);

    //按postId查询
    @Select(value = "select posting._id,posting.post_time,user.username,posting.content,posting.comment_num,posting.collect_num,collection._id c_id" +
            " from posting LEFT JOIN collection on posting._id = collection.posting_id" +
            " LEFT JOIN user on posting.user_id = user._id WHERE posting._id =#{id}")
    @Results({
            @Result(column="_id",property="id"),
            @Result(column="post_time",property="postTime"),
            @Result(column="username",property="userName"),
            @Result(column="comment_num",property="commentNumber"),
            @Result(column="collect_num",property="collectNumber"),
            @Result(column="c_id",property="collectionId")
    })
    List<postItem> getPostById(int id);
}

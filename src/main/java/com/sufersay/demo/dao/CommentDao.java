package com.sufersay.demo.dao;

import com.sufersay.demo.returnItem.commentItem;
import org.apache.ibatis.annotations.*;
import com.sufersay.demo.bean.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Title:CommentDao
 * Description:评论数据处理接口
 * */

@Repository
@Mapper
public interface CommentDao {

    @Insert("insert into comment(posting_id,user_id,create_time,content)" +
            " values (#{postingId},#{userId},#{createTime},#{content})")
    void createComment(Comment comment);

    @Select(value = "select comment._id,user.username,comment.create_time,comment.content FROM comment,user " +
            "where comment.user_id=user._id and user_id=#{id}")
    @Results({
            @Result(column="_id",property="id"),
            @Result(column="username",property="username"),
            @Result(column="create_time",property="postTime"),
    })
    List<commentItem> getCommentByUserId(int id);

    @Select(value = "select comment._id,user.username,comment.create_time,comment.content FROM comment,user " +
            "where comment.user_id=user._id and posting_id=#{id}")
    @Results({
            @Result(column="_id",property="id"),
            @Result(column="username",property="username"),
            @Result(column="create_time",property="postTime"),
    })
    List<commentItem> getCommentByPostingId(int id);

}

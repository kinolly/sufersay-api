package com.sufersay.demo.controller;

import com.sufersay.demo.bean.Comment;
import com.sufersay.demo.returnItem.commentItem;
import com.sufersay.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 评论发布
     *
     * @param userId  用户ID
     * @param postingId 帖子ID
     * @param content 评论内容
     * @return
     */
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public boolean postCreate(@RequestParam(name = "userId", required = true,defaultValue = "") Integer userId,
                              @RequestParam(name = "postingId", required = true,defaultValue = "") Integer postingId,
                              @RequestParam(name = "content", required = true,defaultValue = "") String content){
        Comment comment = new Comment();
        Date date = new Date();
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setCreateTime(new Timestamp(date.getTime()));
        comment.setPostingId(postingId);
        boolean flag1=commentService.createComment(comment);
        boolean flag2=commentService.updateComment(comment);
        return flag1 && flag2;
    }

    /**
     * 评论列表获取
     *
     * @param type      获取方式
     * @param userId    用户Id，type为user时传⼊
     * @param postingId 帖⼦id，type为posting时传入
     * @param pageNum   当前页码
     * @param pageSize  页⾯⼤⼩（⼀页中包含⼏条结果）
     * @return
     */
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public List<commentItem> commentGet(@RequestParam(name = "type",required = true,defaultValue = "") String type,
                                        @RequestParam(name = "userId",required = false,defaultValue = "") Integer userId,
                                        @RequestParam(name = "postingId",required = false,defaultValue = "") Integer postingId,
                                        @RequestParam(name = "pageNum",required = true,defaultValue = "") Integer pageNum,
                                        @RequestParam(name = "pageSize",required = true,defaultValue = "") Integer pageSize){
        List<commentItem> result = null;
        List<commentItem> temp=null;
        if(type.equals("user")){
            temp = commentService.getCommentByUserId(userId);
        }else if(type.equals("posting")){
            temp = commentService.getCommentByPostingId(postingId);
        }
        if(temp != null) {
            int items = temp.size();
            int pages = (int) Math.ceil(items * 1.0 / pageSize);
            if (pageNum > pages) return null;
            int reitems;
            int l;
            if (pageNum != 1) {
                reitems = items - (pageNum - 1) * pageSize;
                l = (pageNum - 1) * pageSize;
            } else {
                reitems = items;
                l = 0;
            }
            int tempPageSize = pageSize;
            if (tempPageSize > reitems) tempPageSize = reitems;
            result = temp.subList(l, l + tempPageSize);
            result.forEach(item -> item.setItems(items));
            result.forEach(item -> item.setPageNum(pageNum));
            result.forEach(item -> item.setPageSize(pageSize));
            return result;
        }
        else return result;
    }
}

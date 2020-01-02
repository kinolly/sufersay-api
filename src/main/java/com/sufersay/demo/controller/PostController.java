package com.sufersay.demo.controller;

import com.sufersay.demo.bean.Posting;
import com.sufersay.demo.returnItem.postItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sufersay.demo.service.PostService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posting")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 帖子发布
     *
     * @param userId  用户ID
     * @param content 帖子内容
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public boolean postCreate(@RequestParam(name = "userId", required = true,defaultValue = "") String userId,
                              @RequestParam(name = "content", required = true,defaultValue = "") String content){
        Posting post = new Posting();
        int usrid = Integer.parseInt(userId);
        Date date = new Date();
        int comments = 0;
        int collections = 0;
        int state = 1;
        post.setUserId(usrid);
        post.setContent(content);
        post.setPostTime(new Timestamp(date.getTime()));
        post.setCollectNum(collections);
        post.setCommentNum(comments);
        post.setPostState(state);
        return postService.createPost(post);
    }

    /**
     * 帖子获取
     *
     * @param type      帖子获取方式
     * @param userId    用户Id，type为user或collection时传⼊
     * @param postingId 帖⼦id，type为id时传⼊
     * @param keyword   关键词，type为keyword时传⼊
     * @param pageNum   当前页码
     * @param pageSize  页⾯⼤⼩（⼀页中包含⼏条结果）
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public List<postItem> postGet(@RequestParam(name = "type",required = true,defaultValue = "") String type,
                                  @RequestParam(name = "userId",required = false,defaultValue = "-1") Integer userId,
                                  @RequestParam(name = "postingId",required = false,defaultValue = "") Integer postingId,
                                  @RequestParam(name = "keyword",required = false,defaultValue = "") String keyword,
                                  @RequestParam(name = "pageNum",required = true,defaultValue = "") Integer pageNum,
                                  @RequestParam(name = "pageSize",required = true,defaultValue = "") Integer pageSize) {
        List<postItem> temp = null;
        List<postItem> result = null;
        if(type.equals("user")) {
            temp=postService.getPostByUserId(userId);
        }else if(type.equals("new")){
            temp=postService.getPostByTime();
        }else if(type.equals("hot")){
            temp=postService.getPostByHot();
        }else if(type.equals("keyword")){
            temp=postService.getPostByKeyword(keyword);
        }else if(type.equals("id")){
            if(userId!=-1) temp=postService.getPostByIdUserId(postingId,userId);
            else temp=postService.getPostById(postingId);
        }else if(type.equals("collection")){
            temp=postService.getPostByCollection(userId);
        }
        if(temp != null){
            int items = temp.size();
            int pages = (int) Math.ceil(items*1.0/pageSize);
            if(pageNum > pages) return null;
            int reitems;
            int l;
            if(pageNum != 1) {
                reitems = items - (pageNum - 1) * pageSize;
                l = (pageNum-1)*pageSize;
            }
            else {
                reitems=items;
                l=0;
            }
            int tempPageSize=pageSize;
            if(tempPageSize>reitems) tempPageSize=reitems;
            result = temp.subList(l ,l + tempPageSize);
            result.forEach(item->item.setItems(items));
            result.forEach(item->item.setPageNum(pageNum));
            result.forEach(item->item.setPageSize(pageSize));
            return result;
        }
        else return result;
    }
}

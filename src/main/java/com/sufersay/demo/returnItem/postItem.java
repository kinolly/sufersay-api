package com.sufersay.demo.returnItem;


import java.util.Date;

public class postItem {
    private Integer id;
    private Date postTime;
    private String userName;
    private String content;
    private int commentNumber;
    private int collectNumber;
    private int[] collectionId;
    private int items;
    private int pageNum;
    private int pageSize;

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public int[] getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int[] collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {
        this.collectNumber = collectNumber;
    }

}

package com.org.service;

import com.org.po.Comment;

import java.util.List;

/**
 * @author Create by MengXi on 2021/10/20 14:34.
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}

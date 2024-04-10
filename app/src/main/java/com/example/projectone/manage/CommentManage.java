package com.example.projectone.manage;

import android.util.Log;

import com.example.projectone.table.Comment;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class CommentManage {
    private  static List<Comment> comments = new ArrayList<>();
    private static Comment comment;
    //添加
    public static void AddComment(String username,String commentname,String enjoydate,String commentdate,String commenttext){
        comment = new Comment();
        comment.setUsername(username);
        comment.setCommentname(commentname);
        comment.setEnjoydate(enjoydate);
        comment.setCommentdate(commentdate);
        comment.setCommenttext(commenttext);

        try{
            comment.saveThrows();
        }catch (Exception e){
            Log.e("SQLComment", "异常数据保存到数据库失败"+"  "+e.getMessage() );
        }
    }

    //查找该分享的所有评论,评论时间最早的放最上面
    public static List<Comment> FindALlComment(String commentname,String enjoydate){
        comments = LitePal.where("commentname = ? and enjoydate = ?",commentname,enjoydate).order("commentdate asc").find(Comment.class);
        if (comments.size() == 0){
            comments = new ArrayList<>();
        }
        return comments;
    }

    public static void DeleteAllComment(String username,String commentdate,String commentname,String enjoydate){
        LitePal.deleteAll(Comment.class,"username = ? and commentdate = ? and commentname = ? and enjoydate = ?",username,commentdate,commentname,enjoydate);
    }

    public static void DeleteAllComment(String commentname,String enjoydate){
        LitePal.deleteAll(Comment.class,"commentname = ? and enjoydate = ?",commentname,enjoydate);
    }
}

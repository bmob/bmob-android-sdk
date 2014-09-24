package com.example.bmobexample.relationaldata;

import com.example.bmobexample.MyUser;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;
/**
 * 
 * @ClassName: Weibo
 * @Description: 微博实体
 * @author Queen
 * @date 2014年4月17日 上午11:10:44
 *
 */
public class Weibo extends BmobObject {
	private String content;	// 微博内容
	/**
	 * 发布人，一条微博是对应一个发布人的，这里是一对一情形的体现，故使用了一个继承自BmobUser的类型,
	 * 在BmobSDK中对于一对一情形的使用比较方便，只需要确保属性的数据类型是继承自BmobObject就可以了，
	 * 这里的MyUser的集成关系是MyUser->BmobUser->BmobObject
	 * 
	 */
	private MyUser author;
	/**
	 * 微博的评论，一条微博是对应多条评论的，像这种一对多的情形，请使用BmobRelation类型
	 */
	private BmobRelation comment;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MyUser getAuthor() {
		return author;
	}
	public void setAuthor(MyUser author) {
		this.author = author;
	}
	public BmobRelation getComment() {
		return comment;
	}
	public void setComment(BmobRelation comment) {
		this.comment = comment;
	}
}

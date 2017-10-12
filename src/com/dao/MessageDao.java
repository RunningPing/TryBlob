package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Message;
import com.util.DateUtil;
import com.util.DbUtil;

public class MessageDao {

	public List<Message> messageList(Connection con)throws Exception{
		List<Message> messageList=new ArrayList<Message>();
		StringBuffer sb=new StringBuffer("select * from message t1 order by releaseDate Desc");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Message message=new Message();
			message.setId(rs.getInt("id"));
			message.setName(rs.getString("name"));
			message.setMessage(rs.getString("message"));
			message.setEmail(rs.getString("email"));
			message.setReleaseDate(DateUtil.formatString(rs.getString("releaseDate"), "yyyy-MM-dd HH:mm:ss"));
			messageList.add(message);
		}
		return messageList;
	}
	
	public int messageAdd(Connection con,Message message)throws Exception{
		StringBuffer sb=new StringBuffer("INSERT INTO message VALUES(NULL,?,?,?,now());");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setString(1, message.getName());
		pstmt.setString(2, message.getMessage());
		pstmt.setString(3, message.getEmail());
		return pstmt.executeUpdate();
	}
	
	
	public static void main(String[] args) throws Exception {
		MessageDao test=new MessageDao();
		List<Message> messageList=test.messageList(new DbUtil().getCon());
		for(int i=0;i<messageList.size();i++){
			System.out.println(messageList.get(i).getMessage());
		}
	}
}

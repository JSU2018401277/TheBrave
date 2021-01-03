package jsu.DSystem.dao;



import jsu.DSystem.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UserDao {

    public UserDao() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 登录验证
     * @param con 数据库连接对象
     * @param user 用户
     * @return 找到的用户
     * @throws Exception 数据库异常
     */
    public User login(Connection con, User user)throws Exception{
        User resultUser=null;
        String sql="select * from user_table  where userName=? and userPassword=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2,user.getUserPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
            resultUser=new User();
            resultUser.setUserName(rs.getString("userName"));
            resultUser.setUserPassword(rs.getString("userPassword"));
            resultUser.setUserEmail(rs.getString("userEmail"));
        }
        return resultUser;
    }
    public User adminLogin(Connection con, User user)throws Exception{
        User resultUser=null;
        String sql="select * from admin_table  where userName=? and userPassword=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2,user.getUserPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
            resultUser=new User();
            resultUser.setUserName(rs.getString("userName"));
            resultUser.setUserPassword(rs.getString("userPassword"));
            resultUser.setUserEmail(rs.getString("userEmail"));
        }
        return resultUser;
    }
    /**
     * 注册验证
     * @param con 数据库连接对象
     * @param user 用户
     * @return 注册成功的用户数
     * @throws Exception 数据库异常
     */
    public int register(Connection con,User user)throws Exception{
        User resultUser=null;
        String sql="select * from user_table  where userName=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        int num=0;
        pstmt.setString(1,user.getUserName());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
            resultUser=new User();
            resultUser.setUserName(rs.getString("userName"));
            resultUser.setUserPassword(rs.getString("userPassword"));
            resultUser.setUserEmail(rs.getString("userEmail"));
            return num;
        }else {
            sql="insert into user_table(userName,userPassword,userEmail) values (?,?,?)";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserEmail());
            num=pstmt.executeUpdate();
        }
        return num;
    }
    /**
     * 删除用户
     * @param con 数据库连接对象
     * @param user 用户
     * @return 删除的数据条数
     */
    public int del(Connection con,User user) {
        int num=0;
        String sql="delete from user_table  where userName=?";
        PreparedStatement pstmt=null;
        try {
            pstmt = con.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            pstmt.setString(1,user.getUserName());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            num=pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return num;
    }
    public int updateUser(Connection con,User user) throws SQLException {
        int num=0;
        String sql="update user_table  set userPassword=? where userName=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUserPassword());
        pstmt.setString(2,user.getUserName());
//        pstmt.setInt(3,article.getArticleNum());
//        pstmt.setDouble(4,article.getArticlePrice());
//        pstmt.setString(5,article.getArticleLabel());
//        pstmt.setInt(6,article.getArticleId());
        num=pstmt.executeUpdate();
        return num;
    }
}


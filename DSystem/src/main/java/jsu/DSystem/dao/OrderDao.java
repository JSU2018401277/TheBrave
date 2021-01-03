package jsu.DSystem.dao;

import jsu.DSystem.bean.Order;
import jsu.DSystem.bean.User;
import jsu.DSystem.bean.shopCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderDao {
    public int addOrder(Connection con, shopCar sc)throws Exception{
        int num=0;
//        Date d=new java.sql.Date();
        Order od=new Order();
//        od.setShopDate(d);
        java.util.Date utilDate=new Date();
        java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
        String sql="insert into order_table(userName,shopDate,articleId,articleName,sumPrice) values (?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, sc.getUserName());
        pstmt.setDate(2,sqlDate);
        pstmt.setInt(3, sc.getArticleId());
        pstmt.setString(4, sc.getArticleName());
        pstmt.setDouble(5, sc.getSumPrice());
        num=pstmt.executeUpdate();
        return num;
    }
    public List<Order> orderSort(Connection con, String userName) throws SQLException {
        Order resultOrder=null;
        List<Order> list=new ArrayList<Order>();
        String sql="select * from order_table  where userName=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,userName);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()) {
            resultOrder=new Order();
            resultOrder.setOrderNum(rs.getInt("orderNum"));
            resultOrder.setUserName(rs.getString("userName"));
            resultOrder.setShopDate(rs.getDate("shopDate"));
            resultOrder.setArticleId(rs.getInt("articleId"));
            resultOrder.setArticleName(rs.getString("articleName"));
            resultOrder.setSumPrice(rs.getDouble("sumPrice"));
            list.add(resultOrder);
        }
        return list;
    }
}

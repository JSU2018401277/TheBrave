package jsu.DSystem.dao;



import jsu.DSystem.bean.Article;
import jsu.DSystem.bean.shopCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarDao {

    public CarDao() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 查看购物车
     * @param con 数据库连接对象
     * @param userName 用户名
     * @return 购物车
     * @throws Exception 数据库异常
     */
    public List<shopCar> sortCar(Connection con, String userName)throws Exception{
        List<shopCar> shopList =new ArrayList<shopCar>();
        shopCar resultCar=null;
        String sql="select * from shopCar_table  where userName=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,userName);
        //pstmt.setString(2,user.getUserPassword());
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()) {
            resultCar=new shopCar();
            resultCar.setUserName(rs.getString("userName"));
            resultCar.setArticleId(rs.getInt("articleId"));
            resultCar.setSumPrice(rs.getDouble("sumPrice"));
            resultCar.setShopSum(rs.getInt("shopSum"));
            resultCar.setArticleName(rs.getString("articleName"));
            shopList.add(resultCar);
        }
        return shopList;
    }
    public shopCar sortCar(Connection con, int articleId)throws Exception{
        shopCar resultCar=null;
        String sql="select * from shopCar_table  where articleId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,articleId);
        //pstmt.setString(2,user.getUserPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
            resultCar=new shopCar();
            resultCar.setUserName(rs.getString("userName"));
            resultCar.setArticleId(rs.getInt("articleId"));
            resultCar.setSumPrice(rs.getDouble("sumPrice"));
            resultCar.setShopSum(rs.getInt("shopSum"));
            resultCar.setArticleName(rs.getString("articleName"));
        }
        return resultCar;
    }
    //添加购物车
    public int addCar(Connection con, shopCar sc)throws Exception{
        shopCar resultCar=null;
        String sql="select * from shopcar_table  where articleId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        int num=0;
        pstmt.setInt(1,sc.getArticleId());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
            return num;
        }else {
            sql="insert into shopcar_table(userName,articleId,shopSum,sumPrice,articleName) values (?,?,?,?,?)";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, sc.getUserName());
            pstmt.setInt(2, sc.getArticleId());
            pstmt.setInt(3, sc.getShopSum());
            pstmt.setDouble(4, sc.getSumPrice());
            pstmt.setString(5, sc.getArticleName());
//            pstmt.setString(6, article.getUserName());
//            pstmt.setString(7, article.getArticleLabel());
            num=pstmt.executeUpdate();
        }
        return num;
    }
//    /**
//     * 注册验证
//     * @param con 数据库连接对象
//     * @param user 用户
//     * @return 注册成功的用户数
//     * @throws Exception 数据库异常
//     */
//    public int register(Connection con,User user)throws Exception{
//        User resultUser=null;
//        String sql="select * from user_table  where userName=?";
//        PreparedStatement pstmt=con.prepareStatement(sql);
//        int num=0;
//        pstmt.setString(1,user.getUserName());
//        ResultSet rs=pstmt.executeQuery();
//        if(rs.next()) {
//            resultUser=new User();
//            resultUser.setUserName(rs.getString("userName"));
//            resultUser.setUserPassword(rs.getString("userPassword"));
//            resultUser.setUserEmail(rs.getString("userEmail"));
//            return num;
//        }else {
//            sql="insert into user_table(userName,userPassword,userEmail) values (?,?,?)";
//            pstmt=con.prepareStatement(sql);
//            pstmt.setString(1, user.getUserName());
//            pstmt.setString(2, user.getUserPassword());
//            pstmt.setString(3, user.getUserEmail());
//            num=pstmt.executeUpdate();
//        }
//        return num;
//    }
//    /**
//     * 删除用户
//     * @param con 数据库连接对象
//     * @param user 用户
//     * @return 删除的数据条数
//     */
    public int removeCar(Connection con,int articleId) throws SQLException {
        int num=0;
        String sql="delete from shopcar_table  where articleId=?";
        PreparedStatement pstmt=null;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,articleId);
        num=pstmt.executeUpdate();
        return num;
    }
}



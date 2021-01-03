package jsu.DSystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库工具类
 * @author 汤圆
 *
 */
public class DBUtils {
    private String dbUrl="jdbc:mysql://localhost:3306/二手交易系统?useSSL=false";
    private String dbUserName="root";//用户名
    private String dbPassword="1234";//密码
    private String jdbcName="com.mysql.jdbc.Driver";//驱动程序
    /**
     * 获取数据库连接
     * @return 返回数据库连接
     * @throws Exception 数据库连接错误
     */
    public  Connection getCon() throws Exception {
        Class.forName(jdbcName);
        Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        return con;
    }
    /**
     * 关闭数据库连接
     * @param con 数据库连接对象
     * @throws Exception 数据库连接错误
     */
    public void closeCon(Connection con)throws Exception{
        if(con!=null) {
            con.close();
        }
    }
    /*
    public static void main(String[] args) {
        DBUtils dbUtil=new DBUtils();
        try{
            dbUtil.getCon();
            System.out.println("数据库连接成功");
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }*/
}


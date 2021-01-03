package jsu.DSystem.dao;

import jsu.DSystem.bean.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ArticleDao {

    public ArticleDao() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 登录验证
     * @param con 数据库连接对象
     * @param article 商品
     * @return 找到的商品
     * @throws Exception 数据库异常
     */
    public List<Article> articleSort(Connection con, String keyword,String type)throws Exception{
        Article resultArticle=null;
        List<Article> list=new ArrayList<Article>();
        String sql="select * from article_table  where "+keyword+"=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,type);
        //pstmt.setString(2,user.getUserPassword());
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()) {
            resultArticle=new Article();
            resultArticle.setArticleId(rs.getInt("articleId"));
            resultArticle.setArticleName(rs.getString("articleName"));
            resultArticle.setArticleIntro(rs.getString("articleIntro"));
            resultArticle.setArticleNum(rs.getInt("articleNum"));
            resultArticle.setArticlePrice(rs.getDouble("articlePrice"));
            resultArticle.setUserName(rs.getString("userName"));
            resultArticle.setArticleLabel(rs.getString("articleLabel"));
            list.add(resultArticle);
        }
        return list;
    }
    public Article articleSort(Connection con, int articleId)throws Exception{
        Article resultArticle=null;
        List<Article> list=new ArrayList<Article>();
        String sql="select * from article_table  where articleId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,articleId);
        //pstmt.setString(2,user.getUserPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
            resultArticle=new Article();
            resultArticle.setArticleId(rs.getInt("articleId"));
            resultArticle.setArticleName(rs.getString("articleName"));
            resultArticle.setArticleIntro(rs.getString("articleIntro"));
            resultArticle.setArticleNum(rs.getInt("articleNum"));
            resultArticle.setArticlePrice(rs.getDouble("articlePrice"));
            resultArticle.setUserName(rs.getString("userName"));
            resultArticle.setArticleLabel(rs.getString("articleLabel"));
            list.add(resultArticle);
        }
        return resultArticle;
    }
    public List<Article> allArticleSort(Connection con)throws Exception{
        Article resultArticle=null;
        List<Article> list=new ArrayList<Article>();
        String sql="select *  from article_table limit 9";
        PreparedStatement pstmt=con.prepareStatement(sql);
        //pstmt.setString(1,type);
        //pstmt.setString(2,user.getUserPassword());
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()) {
            resultArticle=new Article();
            resultArticle.setArticleId(rs.getInt("articleId"));
            resultArticle.setArticleName(rs.getString("articleName"));
            resultArticle.setArticleIntro(rs.getString("articleIntro"));
            resultArticle.setArticleNum(rs.getInt("articleNum"));
            resultArticle.setArticlePrice(rs.getDouble("articlePrice"));
            resultArticle.setUserName(rs.getString("userName"));
            resultArticle.setArticleLabel(rs.getString("articleLabel"));
            list.add(resultArticle);
        }
        return list;
    }
    public List<Article> adminArticleSort(Connection con)throws Exception{
        Article resultArticle=null;
        List<Article> list=new ArrayList<Article>();
        String sql="select *  from article_table";
        PreparedStatement pstmt=con.prepareStatement(sql);
        //pstmt.setString(1,type);
        //pstmt.setString(2,user.getUserPassword());
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()) {
            resultArticle=new Article();
            resultArticle.setArticleId(rs.getInt("articleId"));
            resultArticle.setArticleName(rs.getString("articleName"));
            resultArticle.setArticleIntro(rs.getString("articleIntro"));
            resultArticle.setArticleNum(rs.getInt("articleNum"));
            resultArticle.setArticlePrice(rs.getDouble("articlePrice"));
            resultArticle.setUserName(rs.getString("userName"));
            resultArticle.setArticleLabel(rs.getString("articleLabel"));
            list.add(resultArticle);
        }
        return list;
    }
    /**
     * 上架商品
     * @param con 数据库连接对象
     * @param article 商品
     * @return 添加成功的商品数
     * @throws Exception 数据库异常
     */
    public int addArticle(Connection con,Article article)throws Exception{
        Article resultArticle=null;
        String sql="insert into article_table(articleName,articleIntro,articleNum,articlePrice,userName,articleLabel) values (?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        int num=0;
//            pstmt.setString(1, article.getArticleId());
            pstmt.setString(1, article.getArticleName());
            pstmt.setString(2, article.getArticleIntro());
            pstmt.setInt(3, article.getArticleNum());
            pstmt.setDouble(4, article.getArticlePrice());
            pstmt.setString(5, article.getUserName());
            pstmt.setString(6, article.getArticleLabel());
            num=pstmt.executeUpdate();
        return num;
    }
    /**
     * 删除商品
     * @param con 数据库连接对象
     * @param articleId 商品
     * @return 删除的数据条数
     */
    public int delArticle(Connection con,int articleId) {
        int num=0;
        String sql="delete from article_table  where articleId=?";
        PreparedStatement pstmt=null;
        try {
            pstmt = con.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            pstmt.setInt(1,articleId);
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
    public int UpdateArticle(Connection con,Article article) throws SQLException {
        int num=0;
        String sql="update article_table  set articleName=?,articleIntro=?,articleNum=?,articlePrice=?,articleLabel=? where articleId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,article.getArticleName());
        pstmt.setString(2,article.getArticleIntro());
        pstmt.setInt(3,article.getArticleNum());
        pstmt.setDouble(4,article.getArticlePrice());
        pstmt.setString(5,article.getArticleLabel());
        pstmt.setInt(6,article.getArticleId());
        num=pstmt.executeUpdate();
        return num;
    }
}


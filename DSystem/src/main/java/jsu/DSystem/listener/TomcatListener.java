//package jsu.DSystem.listener;
//
//import jsu.DSystem.bean.Article;
//import jsu.DSystem.bean.User;
//import jsu.DSystem.dao.ArticleDao;
//import jsu.DSystem.utils.DBUtils;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebListener
//public class TomcatListener implements ServletContextListener{
//
//    // Public constructor is required by servlet spec
//    public TomcatListener() {
//    }
//
//    // -------------------------------------------------------
//    // ServletContextListener implementation
//    // -------------------------------------------------------
//    public void contextInitialized(ServletContextEvent sce) {
////        ServletContext servletContext = sce.getServletContext();
////        List<Article> articleList=new ArrayList<Article>();
////        ArticleDao adao=new ArticleDao();
//////        HttpSession session=request.getSession();
//////        User user=(User)session.getAttribute("SESSION_USER");
////        DBUtils db=new DBUtils();
////        try {
//////            articleList=adao.articleSort(db.getCon(),"userName",user.getUserName());
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        servletContext.setAttribute("articleList",articleList);
//    }
//
//    public void contextDestroyed(ServletContextEvent sce) {
//
//    }
//
//}
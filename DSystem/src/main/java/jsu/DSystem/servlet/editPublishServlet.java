package jsu.DSystem.servlet;

import jsu.DSystem.bean.Article;
import jsu.DSystem.dao.ArticleDao;
import jsu.DSystem.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/editPublishServlet")
public class editPublishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Article article=new Article();
        int articleId=Integer.parseInt(request.getParameter("articleId"));
        ArticleDao ad=new ArticleDao();
        DBUtils db=new DBUtils();
        try {
            article=ad.articleSort(db.getCon(),articleId);
            request.getSession().setAttribute("SESSION_EDITPUBLISH",article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("editPublish.jsp");
    }
}

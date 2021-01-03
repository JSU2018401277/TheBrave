package jsu.DSystem.servlet;

import jsu.DSystem.bean.Article;
import jsu.DSystem.bean.User;
import jsu.DSystem.dao.ArticleDao;
import jsu.DSystem.utils.DBUtils;
import jsu.DSystem.utils.JsonUtils;
import jsu.DSystem.utils.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/publishServlet")
public class publishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ResultInfo resultInfo=new ResultInfo();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type","text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            Article article=new Article();
//            article.setArticleId(Integer.parseInt(request.getParameter("articleId")));
            article.setArticleIntro(request.getParameter("articleIntro"));
            article.setArticleName(request.getParameter("articleName"));
            article.setArticleNum( Integer.parseInt(request.getParameter("articleNum")));
            article.setArticlePrice(Double.parseDouble(request.getParameter("articlePrice")));
            article.setArticleLabel(request.getParameter("articleLabel"));
            HttpSession session=request.getSession();
            List<Article> articleList=new ArrayList<Article>();
            User user=(User)session.getAttribute("SESSION_USER");
            article.setUserName(user.getUserName());
            DBUtils db=new DBUtils();
            ArticleDao adao=new ArticleDao();
            try {
                if(adao.addArticle(db.getCon(),article)>0){
                    article.toString();
                    resultInfo.setCode(1);
                    resultInfo.setMessage("sucess");
                    articleList=adao.articleSort(db.getCon(),"userName",user.getUserName());
                    request.getSession().setAttribute("SESSION_ARTICLE",articleList);
                }else{
                    resultInfo.setCode(0);
                    resultInfo.setMessage("添加失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            JsonUtils.toJson(response,resultInfo);
            response.sendRedirect("myPublish.jsp");
    }
}

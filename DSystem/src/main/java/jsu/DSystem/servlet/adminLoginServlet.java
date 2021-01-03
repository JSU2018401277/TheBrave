package jsu.DSystem.servlet;


import jsu.DSystem.bean.Article;
import jsu.DSystem.bean.Order;
import jsu.DSystem.bean.User;
import jsu.DSystem.bean.shopCar;
import jsu.DSystem.dao.ArticleDao;
import jsu.DSystem.dao.CarDao;
import jsu.DSystem.dao.OrderDao;
import jsu.DSystem.dao.UserDao;
import jsu.DSystem.utils.DBUtils;
import jsu.DSystem.utils.JsonUtils;
import jsu.DSystem.utils.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/adminLoginServlet")
public class adminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet( request,  response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResultInfo resultInfo=new ResultInfo();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取前台提交的email和密码
        User user=new User();
        user.setUserName(request.getParameter("userName"));
        user.setUserPassword(request.getParameter("userPassword"));
        List<Article> articleList=new ArrayList<Article>();
        ArticleDao adao=new ArticleDao();

        // 根据email 和密码查询申请人
        UserDao userDao = new UserDao();
        DBUtils db=new DBUtils();

        try {
            user=userDao.adminLogin(db.getCon(),user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //判断user == null，返回登录页面，不为空，就进入主页面
        if (user!=null){

            //将登陆用户对象保存到session
            request.getSession().setAttribute("SESSION_USER",user);
//            request.getSession().setAttribute("sucess", "登陆成功");
            System.out.println("登陆成功");
            System.out.println(user.getUserName()+" "+user.getUserPassword());
            try {
                articleList=adao.adminArticleSort(db.getCon());
                request.getSession().setAttribute("SESSION_ALLARTICLE",articleList);
                for(Article article: articleList){
                    System.out.println(article.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            resultInfo.setCode(1);
            resultInfo.setMessage("sucess");
            //response.sendRedirect(request.getContextPath()+"/index.jsp");
            //response.sendRedirect("/DSystem/index.jsp");

        }else {
            System.out.println("用户名或密码错误");
            resultInfo.setCode(0);
            resultInfo.setMessage("用户名不存在");
            //将resultInfo转为json格式字符串传给前台回调函数
            //response.sendRedirect("/login.html");
        }
        JsonUtils.toJson(response, resultInfo);
    }
}

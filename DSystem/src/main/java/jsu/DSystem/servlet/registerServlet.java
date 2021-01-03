package jsu.DSystem.servlet;

import jsu.DSystem.bean.User;
import jsu.DSystem.dao.UserDao;
import jsu.DSystem.utils.DBUtils;
import jsu.DSystem.utils.JsonUtils;
import jsu.DSystem.utils.ResultInfo;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ResultInfo resultInfo=new ResultInfo();
        User user=new User();
        int num=0;
        user.setUserEmail(request.getParameter("userEmail"));
        user.setUserName(request.getParameter("userName"));
        user.setUserPassword(request.getParameter("userPassword"));
        UserDao userDao = new UserDao();
        DBUtils db=new DBUtils();
        try {
            num=userDao.register(db.getCon(),user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0){
            System.out.println("注册成功");
            resultInfo.setCode(1);
            resultInfo.setMessage("sucess");
            //response.sendRedirect(request.getContextPath()+"/DSystem/login.html");
           // response.sendRedirect("/DSystem/login.html");
        }else{
            System.out.println("已有该用户名");
            resultInfo.setCode(0);
            resultInfo.setMessage("fail");
            //response.sendRedirect("/DSystem/register.html");
        }
        JsonUtils.toJson(response, resultInfo);
    }
}


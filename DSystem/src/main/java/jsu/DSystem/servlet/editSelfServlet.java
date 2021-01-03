package jsu.DSystem.servlet;

import jsu.DSystem.bean.Article;
import jsu.DSystem.bean.User;
import jsu.DSystem.dao.ArticleDao;
import jsu.DSystem.dao.UserDao;
import jsu.DSystem.utils.DBUtils;
import jsu.DSystem.utils.JsonUtils;
import jsu.DSystem.utils.ResultInfo;
import org.apache.htrace.fasterxml.jackson.databind.ser.std.NumberSerializer;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns= "/editSelfServlet")
public class editSelfServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user=new User();
        ResultInfo info=new ResultInfo();
        HttpSession sess=request.getSession();
        user=(User)(sess.getAttribute("SESSION_USER"));
        user.setUserPassword(request.getParameter("userPassword"));
        UserDao ud=new UserDao();
        DBUtils db=new DBUtils();
        try {
            ud.updateUser(db.getCon(),user);
            info.setMessage("sucess");
            info.setCode(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonUtils.toJson(response, info);
      //  response.sendRedirect("mySelf.jsp");
    }
}

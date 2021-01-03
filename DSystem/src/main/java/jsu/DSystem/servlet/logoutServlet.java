package jsu.DSystem.servlet;

import jsu.DSystem.utils.JsonUtils;
import jsu.DSystem.utils.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logoutServlet")
public class logoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.getSession().removeAttribute("SESSION_USER");
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setCode(1);
        resultInfo.setMessage("sucess");
        JsonUtils.toJson(response, resultInfo);
    }
}

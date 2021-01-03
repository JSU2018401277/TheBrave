package jsu.DSystem.servlet;

import jsu.DSystem.bean.User;
import jsu.DSystem.bean.shopCar;
import jsu.DSystem.dao.CarDao;
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

@WebServlet(urlPatterns = "/shopCarServlet")
public class shopCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ResultInfo resultInfo=new ResultInfo();
        int articleId=Integer.parseInt(request.getParameter("articleId"));
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("SESSION_USER");
        CarDao cd=new CarDao();
        DBUtils db=new DBUtils();
        List<shopCar> shopCarList=new ArrayList<shopCar>();
        try {
            if(cd.removeCar(db.getCon(),articleId)>0){
                resultInfo.setCode(1);
                resultInfo.setMessage("sucess");
                shopCarList=cd.sortCar(db.getCon(),user.getUserName());
                for(shopCar sc: shopCarList){
                    System.out.println(sc.toString());
                }
                request.getSession().setAttribute("SESSION_SHOPCAR",shopCarList);
            }else{
                resultInfo.setCode(0);
                resultInfo.setMessage("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonUtils.toJson(response, resultInfo);
        response.sendRedirect("shopCar.jsp");
    }
}

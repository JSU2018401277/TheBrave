package jsu.DSystem.servlet;

import jsu.DSystem.bean.Order;
import jsu.DSystem.bean.shopCar;
import jsu.DSystem.dao.CarDao;
import jsu.DSystem.dao.OrderDao;
import jsu.DSystem.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/payServlet")
public class payServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int articleId=Integer.parseInt(request.getParameter("articleId"));
        CarDao cd=new CarDao();
        DBUtils db=new DBUtils();
        Order order=new Order();
        OrderDao od=new OrderDao();
        shopCar sc=new shopCar();
        List<Order> orderList=new ArrayList<Order>();
        try {
            sc=cd.sortCar(db.getCon(),articleId);
            System.out.println(sc.toString());
            od.addOrder(db.getCon(),sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cd.removeCar(db.getCon(),articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            orderList=od.orderSort(db.getCon(),sc.getUserName());
            request.getSession().setAttribute("SESSION_ORDER",orderList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("pay.jsp");
    }
}

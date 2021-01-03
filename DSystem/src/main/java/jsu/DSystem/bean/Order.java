package jsu.DSystem.bean;

import java.util.Date;

public class Order {
    private int orderNum;//订单号
    private String userName;//购买该订单的用户
    private Date shopDate;//购买日期
    private int articleId;//商品ID
    private String articleName;//商品名
    private double sumPrice;//总价格

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Order(){}

    public Order(int orderNum, String userName, Date shopDate, int articleId,String articleName,double sumPrice) {
        this.orderNum = orderNum;
        this.userName = userName;
        this.shopDate = shopDate;
        this.articleId = articleId;
        this.articleName=articleName;
        this.sumPrice=sumPrice;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getShopDate() {
        return shopDate;
    }

    public void setShopDate(Date shopDate) {
        this.shopDate = shopDate;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }
}

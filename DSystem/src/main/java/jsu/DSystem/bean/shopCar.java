package jsu.DSystem.bean;

public class shopCar {
    private String userName;//此购物车的主人
    private int articleId;//加入购物车的商品ID
    private int shopSum;//购买数量
    private double sumPrice;//商品总价
    private String articleName;//商品名

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public shopCar(){}

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public shopCar(String userName, int articleId, int shopSum, double sumPrice,String articleName) {
        this.sumPrice = sumPrice;
        this.userName = userName;
        this.articleId = articleId;
        this.shopSum = shopSum;
        this.articleName=articleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getShopSum() {
        return shopSum;
    }

    public void setShopSum(int shopSum) {
        this.shopSum = shopSum;
    }

    @Override
    public String toString() {
        return "shopCar{" +
                "userName='" + userName + '\'' +
                ", articleId='" + articleId + '\'' +
                ", shopSum=" + shopSum +
                ", sumPrice=" + sumPrice +
                ", articleName='" + articleName + '\'' +
                '}';
    }
}

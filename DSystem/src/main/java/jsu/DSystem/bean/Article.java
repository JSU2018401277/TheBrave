package jsu.DSystem.bean;

public class Article {
    private int articleId;//商品编号
    private String articleName;//商品名
    private String articleIntro;//商品介绍
    private int articleNum;//商品数量
    private double articlePrice;//商品价格
    private String userName;//发布该商品的用户
    private String articleLabel;//商品标签（url地址）
    public Article(){
    }
    public Article(int articleId, String articleName, String articleIntro, int articleNum, double articlePrice, String userName, String articleLabel) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.articleIntro = articleIntro;
        this.articleNum = articleNum;
        this.articlePrice = articlePrice;
        this.userName = userName;
        this.articleLabel = articleLabel;
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

    public String getArticleIntro() {
        return articleIntro;
    }

    public void setArticleIntro(String articleIntro) {
        this.articleIntro = articleIntro;
    }

    public int getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(int articleNum) {
        this.articleNum = articleNum;
    }

    public double getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(double articlePrice) {
        this.articlePrice = articlePrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getArticleLabel() {
        return articleLabel;
    }

    public void setArticleLabel(String articleLabel) {
        this.articleLabel = articleLabel;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId='" + articleId + '\'' +
                ", articleName='" + articleName + '\'' +
                ", articleIntro='" + articleIntro + '\'' +
                ", articleNum=" + articleNum +
                ", articlePrice=" + articlePrice +
                ", userName='" + userName + '\'' +
                ", articleLabel='" + articleLabel + '\'' +
                '}';
    }
}

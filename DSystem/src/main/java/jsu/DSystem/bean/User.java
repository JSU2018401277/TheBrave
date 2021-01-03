package jsu.DSystem.bean;


public class User{
    private String userName;//用户名
    private String userPassword;//用户密码
    private String userEmail;//邮箱
    public User(){}
    public User(String userName,String userPassword){
        this.userName=userName;
        this.userPassword=userPassword;
    }
    //初始化构造方法
    public User(String userName,String userPassword,String userEmail){
        this.userName=userName;
        this.userPassword=userPassword;
        this.userEmail=userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
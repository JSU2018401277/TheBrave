
function register() {
    var userEmail=$("#userEmail").val();
    var userName = $("#userName").val();
    var userPassword = $("#userPassword").val();
    if (userName == "" || userPassword == "") {
        $("#hint").text("用户名或密码不能为空");
    } else {
        $("#hint").text("");
        $.ajax({
            type: "GET",
            // dataType: "json",
            url: "/DSystem/registerServlet" ,
            data:{
                userEmail:userEmail,
                userName:userName,
                userPassword:userPassword,
            },
            // data: {identity_type:'userPassword', identifier:userName, password:userPassword},
            statusCode : {
                404 : function() {
                    alert("请检查url路径是否指定正确");
                }
            },
            success:function(result) {
                //判断返回的code的结果来判断登陆是否成功
                if (result.code == 1 && result.message == "sucess") {
                    //登陆成功则在ajax里跳转页面
                    window.location.href = "login.html";
                } else {
                    //失败则在前台提示后台传的错误信息
                    $("#hint").html(result.message);
                }
            },
            error : function() {
                $("#hint").text("连接异常，请稍后再试！");
            }
        });
    }

    return false;
}
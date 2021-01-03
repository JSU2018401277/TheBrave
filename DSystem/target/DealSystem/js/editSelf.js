function  updateUser() {
    var userPassword = $("#userPassword").val();
    if (userPassword == "") {
        alert("密码不能为空")
    } else {
        $("#hint").text("");
        $.ajax({
            type: "GET",
            // dataType: "json",
            url: "/DSystem/editSelfServlet" ,
            data:{
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
                    // window.location.href = "mySelf.jsp";
                    alert("修改成功");
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
}
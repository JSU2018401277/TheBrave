function addArticle(){
    var articleName=$(".articleName").val();
    var articleIntro=$(".articleIntro").val();
    var articleNum= $(".articleNum").val();
    var articlePrice = $(".articlePrice").val();
    var articleLabel = $(".articleLabel").val();
    // var articleId = $(".articleId").val();
    // var userName=${SESSION_USER.userName};
    if (articleName == "" || articleNum == ""||articlePrice=="") {
        $("#hint").text("信息不能为空");
    } else {
        $("#hint").text("");
        $.ajax({
            type: "GET",
            // dataType: "json",
            url: "/DSystem/publishServlet" ,
            data:{
                articleName:articleName,
                articleIntro:articleIntro,
                articleNum:articleNum,
                articlePrice:articlePrice,
                articleLabel:articleLabel,
                // articleId:articleId
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
                    //登陆成功则在ajax里跳转页面lo
                    alert("添加成功");
                    window.location.reload();
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
function addArticle(){
    var articleName=$(".articleName").val();
    var articleIntro=$(".articleIntro").val();
    var articleNum= $(".articleNum").val();
    var articlePrice = $(".articlePrice").val();
    var articleLabel = $(".articleLabel").val();
    // var articleId = $(".articleId").val();
    // var userName=${SESSION_USER.userName};
    if (articleName == "" || articleNum == ""||articlePrice=="") {
        $("#hint").text("信息不能为空");
    } else {
        $("#hint").text("");
        $.ajax({
            type: "GET",
            // dataType: "json",
            url: "/DSystem/publishServlet" ,
            data:{
                articleName:articleName,
                articleIntro:articleIntro,
                articleNum:articleNum,
                articlePrice:articlePrice,
                articleLabel:articleLabel,
                // articleId:articleId
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
                    //登陆成功则在ajax里跳转页面lo
                 alert("添加成功");
                     window.location.reload();
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
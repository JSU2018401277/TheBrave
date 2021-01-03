function update(articleId){
    var articleIntro=$(".articleIntro").val();
        var articleName=$(".articleName").val();
        var articleNum=$(".articleNum").val();
        var articlePrice=$(".articlePrice").val();
        var articleLabel=$(".articleLabel").val();
    $.ajax({
        type: "get",
        url: "/DSystem/myPublishDateServlet",
        data: {
            articleId: articleId,
            articleIntro:articleIntro,
            articleName:articleName,
            articleNum:articleNum,
            articlePrice:articlePrice,
            articleLabel:articleLabel,
        },
        statusCode: {
            404: function () {
                alert("请检查url路径是否指定正确");
            }
        },
        success: function (result) {
            //判断返回的code的结果来判断登陆是否成功
            if (result.code == 1 && result.message == "sucess") {
                //登陆成功则在ajax里跳转页面
                // alert("添加成功");
                window.location.reload();

            } else {
                //失败则在前台提示后台传的错误信息
                alert("修改失败，请稍后再试");
            }
        },
        error: function () {
            alert("连接异常，请稍后再试！");
        }
    });
}
function del(articleId){
       $.ajax({
            type: "get",
            url: "/DSystem/myPublishServlet",
            data: {
                articleId: articleId,
            },
            statusCode: {
                404: function () {
                    alert("请检查url路径是否指定正确");
                }
            },
            success: function (result) {
                //判断返回的code的结果来判断登陆是否成功
                if (result.code == 1 && result.message == "sucess") {
                    //登陆成功则在ajax里跳转页面
                    // alert("添加成功");
                    window.location.reload();

                } else {
                    //失败则在前台提示后台传的错误信息
                    alert("下架失败，请稍后再试");
                }
            },
            error: function () {
                alert("连接异常，请稍后再试！");
            }
        });
}
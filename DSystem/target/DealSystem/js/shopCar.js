function del(articleId){
    $.ajax({
        type: "get",
        url: "/DSystem/shopCarServlet",
        data: {
            articleId: articleId,
        },
        statusCode: {
            404: function () {
                alert("请检查url路径是否指定正确");
            }
        },
        success: function (result) {
            //判断返回的code的结果来判断删除是否成功
            if (result.code == 1 && result.message == "sucess") {
                //刷新页面
                window.location.reload();

            } else {
                //失败则在前台提示后台传的错误信息
                alert("移除失败，请稍后再试");
            }
        },
        error: function () {
            alert("连接异常，请稍后再试！");
        }
    });
}
<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@page isELIgnored="false" %>
<html xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="order by dede58.com"/>
    <title>编辑基本信息</title>
    <link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css">
    <link href="css/cropper.min.css" rel="stylesheet">
    <link href="css/sitelogo.css" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/cropper.js"></script>
    <script src="js/sitelogo.js"></script>
    <script src="./js/httpHelper.js"></script>
</head>

<body>

<!-- start header -->
<header>
    <div class="top center">
        <div class="left fl">
            <ul>
                <li><a href="#" id="location">当前定位城市：位置获取中</a></li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="right fr">
            <div class="fr">
                <ul id="login_ul">
                    <li><a href="mySelf.jsp" target="_blank">${SESSION_USER.userName}</a></li>
                    <li>|</li>
                    <li><a href="./login.html" onclick="logout();">注  销</a></li>
                </ul>
                <ul id="userinfo" class="userinfo hidden">
                    <li>
                        <a href="">
                            <img src="image/avatar.png">
                            <span>Hi, 莉莉</span>
                        </a>
                        <ul>
                            <li><a href="./mySelf.jsp">个人中心</a></li>
                            <li><a href="#" onclick="window.localStorage.removeItem('token');window.location.reload();">退出登录</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
</header>
<!--end header -->

<!-- start banner_x -->
<div class="banner_x center" style="position: relative;left: -146px;">
    <a href="./index.jsp"><div class="logo fl"></div></a>
    <div class="nav fl">
        <ul>
            <li><a href="myPublish.jsp" onclick="">发布闲置</a></li>
            <li><a href="goodList.jsp?type=nearby">逛附近</a></li>
            <li><a href="#" onclick="overlay();">下载客户端</a></li>
            <!--<li><a href="">关于我们</a></li>-->
        </ul>
    </div>
    <div class="search fr">
        <form action="" method="post">
            <div class="text fl">
                <input type="text" class="shuru" name="key"  placeholder="搜索闲置物品" required>
            </div>
            <div class="submit fl">
                <input type="submit" class="sousuo" value="搜索" onclick="return search();"/>
            </div>
            <div class="clear"></div>
        </form>
        <div class="clear"></div>
    </div>
</div>
<!-- end banner_x -->

<!-- self_info -->
<div class="grzxbj">
    <div class="selfinfo center">
        <div class="lfnav fl">
            <div class="ddzx">订单中心</div>
            <div class="subddzx">
                <ul>
                    <li><a href="order.jsp" >进行中的订单</a></li>
                    <li><a href="finshOrder.jsp" >已完成的订单</a></li>
                </ul>
            </div>
            <div class="ddzx">个人中心</div>
            <div class="subddzx">
                <ul>
                    <li><a href="mySelf.jsp" >基本信息</a></li>
                    <li><a href="myPublish.jsp" class="activity">我的发布</a></li>
                    <li><a href="receiving_address.html">收货地址</a></li>
                    <li><a href="shopCar.jsp">购物车</a></li>
                </ul>
            </div>
        </div>
        <div id="usrinfo" class="rtcont fr">
            <div class="ddzxbt">发布商品</div>
            <table class="baseinfo edit-info">
                <tr>
                    <td>
                        <a href="#" data-toggle="modal" data-target="#avatar-modal">
                            <img id="avatar" v-bind:src="(userinfo.avatar==null || userinfo.avatar=='[]')?'./image/avatar.png' : getUrl() + JSON.parse(userinfo.avatar)[0]" alt="">
                        </a>
                    </td>
                    <td>
                        <p>&nbsp;商&nbsp;品&nbsp;名&nbsp;：<input type="text" v-model="userinfo.nickname" class="articleName" value="${SESSION_EDITPUBLISH.articleName}"></p>
                        <p>商品介绍：<input type="text" name="birthday" id="datepicker" v-model="userinfo.birthday" class="articleIntro"value="${SESSION_EDITPUBLISH.articleIntro}"></p>
                        <p>商品价格：<input type="text" v-bind:value="userinfos.phoneNum" class="articlePrice" value="${SESSION_EDITPUBLISH.articlePrice}"></p>
                        <p>商品标签：<input type="text" name="birthday"  v-model="userinfo.birthday" class="articleLabel" value="${SESSION_EDITPUBLISH.articleLabel}"></p>
                        <p>商品数量：<input type="text" name="birthday"  v-model="userinfo.birthday" class="articleNum" value="${SESSION_EDITPUBLISH.articleNum}"></p>
                        <%--                        <p>商品编号：<input type="text" name="birthday"  v-model="userinfo.birthday" class="articleId"></p>--%>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <a href="myPublish.jsp" onclick="update(${SESSION_EDITPUBLISH.articleId});">
                            <div class="save" >确定</div></a></td>
                </tr>
            </table>
        </div>
        <div class="clear"></div>
    </div>
</div>
<!-- self_info -->

<!-- start upload image-->
<!--<div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form class="avatar-form" action="upload-logo.php" enctype="multipart/form-data" method="post">
            <form class="avatar-form">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal" type="button">&times;</button>
                    <h4 class="modal-title" id="avatar-modal-label">修改头像</h4>
                </div>
                <div class="modal-body">
                    <div class="avatar-body">
                        <div class="avatar-upload">
                            <input class="avatar-src" name="avatar_src" type="hidden">
                            <input class="avatar-data" name="avatar_data" type="hidden">
                            <label for="avatarInput" style="line-height: 35px;">图片上传</label>
                            <button class="btn btn-danger"  type="button" style="height: 35px;" onClick="$('input[id=avatarInput]').click();">请选择图片</button>
                            <span id="avatar-name"></span>
                            <input class="avatar-input hide" id="avatarInput" name="avatar_file" type="file"></div>
                        <div class="row">
                            <div class="col-md-9">
                                <div class="avatar-wrapper"></div>
                            </div>
                            <div class="col-md-3">
                                <div class="avatar-preview preview-lg" id="imageHead"></div>
                                <div class="avatar-preview preview-md"></div>
                        <div class="avatar-preview preview-sm"></div>
                            </div>
                        </div>
                        <div class="row avatar-btns">
                            <div class="col-md-4">
                                <div class="btn-group">
                                    <button class="btn btn-danger fa fa-undo" data-method="rotate" data-option="-90" type="button" title="Rotate -90 degrees"> 向左旋转</button>
                                </div>
                                <div class="btn-group">
                                    <button class="btn  btn-danger fa fa-repeat" data-method="rotate" data-option="90" type="button" title="Rotate 90 degrees"> 向右旋转</button>
                                </div>
                            </div>
                            <div class="col-md-5" style="text-align: right;">
                                <button class="btn btn-danger fa fa-arrows" data-method="setDragMode" data-option="move" type="button" title="移动">
                        <span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;setDragMode&quot;, &quot;move&quot;)">
                        </span>
                                </button>
                                <button type="button" class="btn btn-danger fa fa-search-plus" data-method="zoom" data-option="0.1" title="放大图片">
                        <span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;zoom&quot;, 0.1)">
                          <span class="fa fa-search-plus"></span>
                        </span>
                                </button>
                                <button type="button" class="btn btn-danger fa fa-search-minus" data-method="zoom" data-option="-0.1" title="缩小图片">
                        <span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;zoom&quot;, -0.1)">
                          <span class="fa fa-search-minus"></span>
                        </span>
                                </button>
                                <button type="button" class="btn btn-danger fa fa-refresh" data-method="reset" title="重置图片">
                                    <span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="$().cropper(&quot;reset&quot;)" aria-describedby="tooltip866214"/>
                                </button>
                            </div>
                            <div class="col-md-3">
                                <button class="btn btn-danger btn-block avatar-save fa fa-save" type="button" data-dismiss="modal"> 保存修改</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>-->
<!-- end upload image-->

<div id="modal-overlay"></div>
<div id="modal-data">
    <img class="ico" src="image/ico_close.png" onclick="overlay()">
    <p class="title">使用更多功能，请下载贰货App</p>
    <div class="qr-code">
        <p style="padding-bottom: 10px;">手机扫描二维码下载</p>
        <div id="d-qrcode"></div>
    </div>
</div>

<%--<script src="./js/jquery-1.11.3.min.js" type="text/javascript"></script>--%>
<script src="./js/modal.js"></script>
<!-- 定位 -->
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.10&key=a7f4096ab664c49344b8727904690561"></script>
<script type="text/javascript" src="./js/location.js"></script>
<!-- 修改头像 -->
<script src="js/html2canvas.min.js" type="text/javascript" charset="utf-8"></script>
<!-- 日期选择器 -->
<%--<script src="//code.jquery.com/jquery-1.9.1.js"></script>--%>
<%--<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>--%>
<!-- vue -->
<script src="./js/vue.min.js"></script>
<script src="./js/editInfo.js"></script>
<%--<!-- 二维码生成 -->--%>
<%--<script src="./js/utf.js"></script>--%>
<%--<script src="./js/jquery.qrcode.js"></script>--%>
<%--<script src="./js/downloadApk.js"></script>--%>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/myPublish.js"></script>

</body>

</html>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@page isELIgnored="false" %>
<html xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>闲置列表</title>
    <link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <link rel="stylesheet" type="text/css" href="./css/pagination.css">
    <!-- <script src="./js/httpHelper.js"></script>-->
    <script src="./js/timeUtil.js"></script>
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
                            <li><a href="mySelf.jsp">个人中心</a></li>
                            <li><a href="#" onclick="javascript:window.localStorage.removeItem('token');window.location.reload();">退出登录</a></li>
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
<div class="banner_x center">
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

<!-- start list-->
<div id="list">
    <div id="nearby" class="nearby w">
        <div class="biaoti center">
            <ul>
                <li><a href="#" onclick="sortLists(4);">价格降序</a></li>
                <li><a href="#" onclick="sortLists(3);">价格升序</a></li>
                <li><a href="#" onclick="sortLists(2);">离我最近</a></li>
                <li><a href="#" onclick="sortLists(1);">最新发布</a></li>
            </ul>
        </div>
        <div class="main center">
            <div v-for="n in (Math.ceil(lists.length/5)<5?Math.ceil(lists.length/5):5)" class="content">
                <div class="remen fl" v-for="(item,index) in lists" v-if="index >= n*5-5 & index <= n*5-1">
                    <div class="condition"><span>{{item.goods.conditions}}</span></div>
                    <div class="tu">
                        <a v-bind:href="'goods_detail.html?id=' + item.goods.id">
                            <img v-if="item.goods.images == null" src="./image/goods.jpg">
                            <img v-else v-bind:src="getUrl() + JSON.parse(item.goods.images)[0]">
                        </a>
                    </div>
                    <div class="miaoshu"><a v-bind:href="'goods_detail.html?id=' + item.goods.id">{{item.goods.title}}</a></div>
                    <div class="jiage">{{item.goods.price}}元</div>
                    <div class="place">{{formatPublishTime(item.goods.publish_time)}}&nbsp;·&nbsp;{{item.goods.locationName}}</div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="pager">
                <div class="wrapper">
                    <div class="M-box1"></div>
                </div>
            </div>
            <div class="pager">
                <footer class="mt20 center">
                    <div>&copy;erhuo.immi.com 版权所有</div>
                    <div>违法和不良信息举报电话：xxx-xxxx-xxxx</div>
                </footer>
            </div>
        </div>
    </div>
</div>
<!-- end list-->

<div id="modal-overlay"></div>
<div id="modal-data">
    <img class="ico" src="image/ico_close.png" onclick="overlay()">
    <p class="title">使用更多功能，请下载贰货App</p>
    <div class="qr-code">
        <p style="padding-bottom: 10px;">手机扫描二维码下载</p>
        <div id="d-qrcode"></div>
    </div>
</div>

<script src="./js/jquery.min.js"></script>
<script src="./js/jquery.pagination.js"></script>
<script src="./js/modal.js"></script>
<!-- 定位 -->
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.10&key=a7f4096ab664c49344b8727904690561"></script>
<script type="text/javascript" src="./js/location.js"></script>
<!-- carousel -->
<script src="./js/wySilder.min.js"></script>
<!-- vue.js -->
<script src="./js/vue.min.js"></script>
<script src="./js/goodslist.js"></script>
<!-- 二维码生成 -->
<script src="./js/utf.js"></script>
<script src="./js/jquery.qrcode.js"></script>
<script src="./js/downloadApk.js"></script>

</body>
</html>
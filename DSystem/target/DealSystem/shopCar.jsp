<%@ page contentType="text/html; charset=utf-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="order by dede58.com"/>
    <title>我的收藏</title>
    <link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
<%--    <script src="./js/httpHelper.js"></script>--%>
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
                    <li><a href="./mySelf.jsp" target="">${SESSION_USER.userName}</a></li>
                    <li>|</li>
                    <li><a href="#" onclick="logout();">注 销</a></li>
                </ul>
                <ul id="userinfo" class="userinfo hidden">
                    <li>
                        <a href="">
                            <img src="image/avatar.png">
                            <span>Hi, 莉莉</span>
                        </a>
                        <ul>
                            <li><a href="./self_info.html">个人中心</a></li>
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
<div class="banner_x center">
    <a href="./index.jsp"><div class="logo fl"></div></a>
    <div class="nav fl">
        <ul>
            <li><a href="#" onclick="">发布闲置</a></li>
            <li><a href="goodList.jsp?type=nearby">逛附近</a></li>
            <li><a href="#" onclick="">下载客户端</a></li>
            <!--<li><a href="">关于我们</a></li>-->
        </ul>
    </div>
    <div class="search fr">
        <form action="" method="post">
            <div class="text fl">
                <input type="text" class="shuru" name="key" placeholder="搜索闲置物品" required>
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
                    <li><a href="order.html">进行中的订单</a></li>
                    <li><a href="finshOrder.jsp">已完成的订单</a></li>
                </ul>
            </div>
            <div class="ddzx">个人中心</div>
            <div class="subddzx">
                <ul>
                    <li><a href="mySelf.jsp">基本信息</a></li>
                    <li><a href="myPublish.jsp">我的发布</a></li>
                    <li><a href="receiving_address.html">收货地址</a></li>
                    <li><a href="shopCar.jsp" class="activity">购物车</a></li>
                </ul>
            </div>
        </div>
        <div class="rtcont fr">
            <div class="ddzxbt">购物车</div>
            <table id="done-order" class="order_list">
                <c:if test="${not empty SESSION_SHOPCAR}">
                <c:forEach var="shopCar" items="${SESSION_SHOPCAR}">
                <tr>
                    <td>
                        <img v-if="collection.goods.images == null" src="./image/goods.jpg">
                        <img v-else v-bind:src="">
                    </td>
                    <td>
                        <p class="username">
                            <a  class="name">${shopCar.userName}</a>
                            <span hidden="display:none" class="flag" id="articleId">${shopCar.articleId}</span>
                        </p>
                        <p class="title">${shopCar.articleName}</p>
                    </td>
                    <td class="price">￥${shopCar.sumPrice}</td>
<%--                    <td>--%>
<%--                                <span style="color: #8a8a8a !important;font-size: 12px !important;">--%>
<%--                                    ${shopCar.userName}--%>
<%--                                </span>--%>
<%--                    </td>--%>
                    <td><a href="#" onclick="del(${shopCar.articleId})" >移除购物车</a></td>
                    <td><a href="/DSystem/payServlet?articleId=${shopCar.articleId}" onclick="" >支付</a></td>
<%--                    <td><a v-bind:href="'goods_detail.html?id=' + collection.goods.id">详情</a></td>--%>
                </tr>
                </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
</div>
<!-- self_info -->

<div id="modal-overlay"></div>
<div id="modal-data">
    <img class="ico" src="image/ico_close.png" onclick="overlay()">
    <p class="title">使用更多功能，请下载贰货App</p>
    <div class="qr-code">
        <p style="padding-bottom: 10px;">手机扫描二维码下载</p>
        <div id="d-qrcode"></div>
    </div>
</div>

<script src="./js/jquery.min.js" type="text/javascript"></script>
<script src="./js/modal.js"></script>
<!-- 定位 -->
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.10&key=a7f4096ab664c49344b8727904690561"></script>
<script type="text/javascript" src="./js/location.js"></script>
<!-- vue.js -->
<script src="./js/vue.min.js"></script>
<!-- 二维码生成 -->
<%--<script src="./js/utf.js"></script>--%>
<script src="./js/jquery.qrcode.js"></script>

<script src="./js/shopCar.js"></script>

</body>
</html>
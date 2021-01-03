<%@ page contentType="text/html; charset=utf-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>付款</title>
    <link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
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
                    <li><a href="./login.html" target="_blank">${SESSION_USER.userName}</a></li>
                    <li>|</li>
                    <li><a href="#" onclick="overlay();">注  册</a></li>
                </ul>
                <ul id="userinfo" class="userinfo hidden">
                    <li>
                        <a href="">
                            <img src="image/avatar.png">
                            <span>Hi, 莉莉</span>
                        </a>
                        <ul>
                            <li><a href="./self_info.html">个人中心</a></li>
                            <li><a href="">退出登录</a></li>
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

<!-- self_info -->
<div class="grzxbj">
    <div class="selfinfo center">
        <div class="rtcont">
            <div class="ddzxbt">付款</div>
            <table>
                <tr>
<%--                    <td class="le">--%>
<%--                        <p id="outTradeNo" class="title">订单号：${SESSION_ORDER.orderNum}<span></span></p>--%>
<%--                        <p id="title" class="title">标&emsp;题：<span></span></p>--%>
<%--                        <p id="totalAmount" class="title">金&emsp;额：<span class="price"></span></p>--%>
<%--                    </td>--%>
                    <td class="ri">
                        <p class="title">欢迎使用支付宝付款</p>
                        <div id="qrcode"></div>
                        <p class="hint"><span>支付宝扫码说明：</span>打开支付宝手机客户端扫一扫，确认即可完成付款！</p>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<!-- self_info -->

<script src="./js/jquery.min.js" type="text/javascript"></script>
<!-- 定位 -->
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.10&key=a7f4096ab664c49344b8727904690561"></script>
<script type="text/javascript" src="./js/location.js"></script>
<!-- 二维码生成 -->
<script src="./js/utf.js" type="text/javascript"></script>
<script src="./js/jquery.qrcode.js" type="text/javascript"></script>
<script src="./js/payment.js"></script>

</body>
</html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>贰货</title>
    <link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <!--<script src="./js/httpHelper.js"></script>-->
    <style type="text/css">


    </style>
</head>
<body>
<div id="index">

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
                                <li><a href="#" onclick="">退出登录</a></li>
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
                <li><a href="#" onclick="overlay();">下载客户端</a></li>
                <!--<li><a href="">关于我们</a></li>-->
            </ul>
        </div>
        <div class="search fr">
            <form action="" method="post">
                <div class="text fl">
                    <input type="text" class="shuru" name="key" placeholder="搜索闲置物品" required>
                </div>
                <div class="submit fl">
                    <input type="submit" class="sousuo" onclick="return search();" value="搜索"/>
                </div>
                <div class="clear"></div>
            </form>
            <div class="clear"></div>
        </div>
    </div>
    <!-- end banner_x -->

    <!-- start banner_y and carousel -->
    <div class="banner_y center">
        <div class="nav left">
            <ul>
                <li v-for="category in categories">
                    <a v-bind:href="'goodlists.html?type=category&c_id=' + category.id" v-on:click="clickCategory(category.id);">{{category.title}}</a>
                    <div class="pop">
                        <div class="left fl">
                            <div v-for="node in category.nodes">
                                <div class="xuangou_left fl">
                                    <a v-bind:href="encodeUrl(node.title)">
                                        <div class="img fl"><img v-bind:src="node.ico"></div>
                                        <span class="fl">{{node.title}}</span>
                                        <div class="clear"></div>
                                    </a>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>

        <!-- carousel -->
        <div class="carousel">
            <div class="silder-scroll">
                <div class="silder-main">
                    <div v-for="carousel in carousels" class="silder-main-img">
                        <img v-bind:src="carousel.url">
                    </div>
                </div>
            </div>
        </div>
        <!-- end carousel -->
    </div>
    <!-- end banner_y and carousel -->

    <!-- start banner -->
    <div class="sub_banner center">
        <div class="datu1 fl"><a href="#" onclick="overlay();"><img src="./image/bg_publich.png" alt=""></a></div>
        <div class="datu fl"><a href="#nearby"><img src="./image/bg_fjhh.png" alt=""></a></div>
        <div class="datu fl"><a href="#youlike"><img src="./image/bg_cnxh.png" alt=""></a></div>
        <div class="datu fr"><a href="#" onclick="overlay();"><img src="./image/bg_download.png" ></a></div>
        <div class="clear"></div>
    </div>
    <!-- end banner -->

    <!-- start nearby-->
    <div id="nearby" class="nearby w">
        <div class="biaoti center">附近好货</div>
        <div class="main center">


            <div  class="content">
                <c:if test="${not empty SESSION_ALLARTICLE}">
                <c:forEach var="Article" items="${SESSION_ALLARTICLE}">
                <div class="remen fl" >
                    <div class="condition"><span>${Article.articleName}</span></div>
                    <div class="tu">
                        <a href="">
                            <img  src="./image/goods.jpg">
                        </a>
                    </div>
                    <div class="miaoshu"><a href="">${Article.articleIntro}</a></div>
                    <div class="jiage">${Article.articlePrice}元</div>
                    <a class="place"  href="#2" onclick="addCar(${Article.articleId});">加入购物车</a>
                </div>
                </c:forEach>
                </c:if>
                <div  class="remenlast fr">
                    <div class="more"><a href="goodList.jsp"><img src="./image/more.png" alt=""></a></div>
                </div>
                <div class="clear"></div>
            </div>


        </div>
    </div>
    <!-- end nearby-->

    <!-- start youlike -->
<%--    <div id="youlike" class="nearby w">--%>
<%--        <div class="biaoti center">猜你喜欢</div>--%>
<%--        <div class="main center">--%>


<%--            <div v-for="n in Math.ceil(youlikes.length/5)" class="content">--%>
<%--                <c:if test="${not empty SESSION_ALLARTICLE}">--%>
<%--                    <c:forEach var="Article" items="${SESSION_ALLARTICLE}">--%>
<%--                <div class="remen fl" v-for="(item,index) in youlikes" v-if="index >= n*5-5 & index <= n*5-1">--%>
<%--                    <div class="condition"><span>${Article.articleName}</span></div>--%>
<%--                    <div class="tu">--%>
<%--                        <a v-bind:href="'goods_detail.html?id=' + item.goods.id">--%>
<%--                            <img v-if="item.goods.images == null" src="./image/goods.jpg">--%>
<%--                            <img v-else v-bind:src="getUrl() + JSON.parse(item.goods.images)[0]">--%>
<%--                        </a>--%>
<%--                    </div>--%>
<%--                    <div class="miaoshu"><a v-bind:href="'goods_detail.html?id=' + item.goods.id">${Article.articleIntro}</a></div>--%>
<%--                    <div class="jiage">${Article.articlePrice}元</div>--%>
<%--&lt;%&ndash;                    <div class="place">{{item.goods.locationName}}</div>&ndash;%&gt;--%>
<%--                </div>--%>
<%--                </c:forEach>--%>
<%--                </c:if>--%>

<%--                <div v-if="n == Math.ceil(youlikes.length/5)" class="remenlast fr">--%>
<%--                    <div class="more"><a href="goodList.jsp?type=youlike"><img src="./image/more.png" alt=""></a></div>--%>
<%--                </div>--%>
<%--                <div class="clear"></div>--%>
<%--            </div>--%>

<%--        </div>--%>
<%--    </div>--%>
    <!-- end youlike -->

    <div id="modal-overlay"></div>
    <div id="modal-data">
        <img class="ico" src="image/ico_close.png" onclick="overlay()">
        <p class="title">使用更多功能，请下载贰货App</p>
        <div class="qr-code">
            <p style="padding-bottom: 10px;">手机扫描二维码下载</p>
            <div id="d-qrcode"></div>
        </div>
    </div>

    <footer class="mt20 center">
        <div>&copy;erhuo.immi.com 版权所有</div>
        <div>违法和不良信息举报电话：xxx-xxxx-xxxx</div>
    </footer>

</div>

<script src="./js/jquery.min.js"></script>
<script src="./js/modal.js"></script>
<!-- 定位 -->
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.10&key=a7f4096ab664c49344b8727904690561"></script>
<script type="text/javascript" src="./js/location.js"></script>
<!-- carousel -->
<script src="./js/wySilder.min.js"></script>
<!-- vue.js -->
<script src="./js/vue.min.js"></script>
<script src="./js/index.js"></script>
<!-- 二维码生成 -->
<%--<script src="./js/utf.js"></script>--%>
<%--<script src="./js/jquery.qrcode.js"></script>--%>
<%--<script src="./js/downloadApk.js"></script>--%>

</body>
</html>

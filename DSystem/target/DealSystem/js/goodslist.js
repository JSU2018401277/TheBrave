/**
 * 初始化Vue
 */
var vue;
initVue();

/** 判断用户是否登陆 **/

/**
 * 解析定位结果
 * @param data
 */
var lng; // 经度
var lat; // 纬度
var city; // 城市
var type; // 类型
function onComplete(data) {
    lng = data.position.getLng();
    lat = data.position.getLat();
    city = data.addressComponent.city;
    type = getQueryParam("type");

    if (type === "nearby") {
        vue.getNearbyGoods(1); // 获取附近的闲置物品
    } else if (type === "youlike") {
        vue.getYoulikeGoods(1);
    } else if (type === "category") { // 分类
        vue.getCategoryGoods(1, getQueryParam("c_id"));
    } else if(type === "search") {
        vue.getSearchGoods(1, getQueryParam("describe"));
    }
}


/**
 * 初始化浏览次数
 */
if (window.localStorage.getItem("yourBrower") === null) {
    var yourBrower = [
        {id:1, name:"二手书籍", time: 0},
        {id:2, name:"二手手机", time: 0},
        {id:3, name:"二手电脑", time: 0},
        {id:4, name:"二手相机", time: 0},
        {id:5, name:"3C配件", time: 0},
        {id:6, name:"服饰鞋包", time: 0},
        {id:7, name:"美妆捡漏", time: 0},
        {id:8, name:"二手乐器", time: 0},
        {id:9, name:"办公用品", time: 0},
        {id:10, name:"生活用品", time: 0}
    ];
    window.localStorage.setItem("yourBrower", JSON.stringify(yourBrower));
}


/**
 * 获取地址栏参数
 * @param name
 * @returns {*}
 */
function getQueryParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

/**
 * 初始化Vue
 */
function initVue() {
    vue = new Vue({
        el: '#list',
        data: {
            lists: []
        },
        methods: {
            getNearbyGoods : function (pageIndex) {
                var _this = this;
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: getUrl() + "/goods/get_nearby_goodsList" ,
                    data: {locationName:city, page:pageIndex, size:20},
                    success: function (result) {
                        if(result.code === 200 && result.message === "Success") {
                            _this.$data.lists = result.data;
                            _this.$forceUpdate();
                        } else {
                            _this.$data.lists = [];
                            _this.$forceUpdate();
                            //alert("error code: " + result.code + ", error msg : " + result.message);
                        }
                    },
                    error : function() {
                        alert("连接异常，请稍后再试");
                    }
                });
            },
            getYoulikeGoods: function (pageIndex) {
                var _this = this;
                // 浏览类别次数-降序排序
                var yourBrower = JSON.parse(window.localStorage.getItem("yourBrower"));
                var compare = function (x, y) {
                    if (x.time < y.time) {
                        return 1;
                    } else if (x.time > y.time) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                yourBrower.sort(compare)

                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: getUrl() + "/goods/get_recommend_goodsList" ,
                    data: {page:pageIndex, paramOne:yourBrower[0].id, OneSize:12, paramTwo:yourBrower[1].id, TwoSize:8},
                    success: function (result) {
                        if(result.code === 200 && result.message === "Success") {
                            _this.$data.lists = result.data;
                            _this.$forceUpdate();
                        } else {
                            _this.$data.lists = [];
                            _this.$forceUpdate();
                            //alert("error code: " + result.code + ", error msg : " + result.message);
                        }
                    },
                    error : function() {
                        alert("连接异常，请稍后再试");
                    }
                });
            },
            getCategoryGoods: function(pageIndex, categoryId) { // 获取分类物品
                var _this = this;
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: getUrl() + "/goods/get_goods_list" ,
                    data: {category_id:categoryId, page:pageIndex, count:20},
                    success: function (result) {
                        if(result.code === 200 && result.message === "Success") {
                            _this.$data.lists = result.data;
                            _this.$forceUpdate();
                        } else {
                            _this.$data.lists = [];
                            _this.$forceUpdate();
                            // alert("error code: " + result.code + ", error msg : " + result.message);
                        }
                    },
                    error : function() {
                        alert("连接异常，请稍后再试");
                    }
                });
            },
            getSearchGoods: function(pageIndex, describe) {
                var _this = this;
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: getUrl() + "/goods/get_goods_list_search",
                    data: {describe:decodeURI(describe), page:pageIndex, count:20},
                    success: function (result) {
                        if(result.code === 200 && result.message === "Success") {
                            console.log(result.data);
                            _this.$data.lists = result.data;
                            _this.$forceUpdate();
                        } else {
                            _this.$data.lists = [];
                            _this.$forceUpdate();
                            // alert("error code: " + result.code + ", error msg : " + result.message);
                        }
                    },
                    error : function() {
                        alert("连接异常，请稍后再试");
                    }
                });
            },
            formatPublishTime : function (publishTime) {
                return timeago(getMillisecond(publishTime));
            },
            switchPage : function (pageIndex) { // 切换页面
                var _this = this;
                if (type === "nearby") {
                    _this.getNearbyGoods(pageIndex);
                } else if (type === "youlike") {
                    vue.getYoulikeGoods(pageIndex);
                } else if (type === "category") {
                    vue.getCategoryGoods(pageIndex, getQueryParam("c_id"));
                } else if(type === "search") {
                    vue.getSearchGoods(pageIndex, getQueryParam("describe"));
                }
            }
        }
    });
}

/**
 * 初始化页码
 */
$(function () {
    $('.M-box1').pagination({
        showData:20,
        callback:vue.switchPage
    });
});

/**
 * 排序
 */
function sortLists(type) {

    if (type == 1) { // 最新发布
        vue.$data.lists = vue.$data.lists.sort(sortNew);
    } else if (type == 2) { // 离我最近
        vue.$data.lists = vue.$data.lists.sort(sortDistance);
    } else if (type == 3) { // 价格升序
        vue.$data.lists = vue.$data.lists.sort(sortPriceAsc);
    } else if (type == 4) { // 价格降序
        vue.$data.lists = vue.$data.lists.sort(sortPriceDesc);
    }
    vue.$forceUpdate(); // 刷新vue
}

/**
 * 距离最近
 */
function sortDistance(a, b) {
    return getDistance(lat, lng, a.goods.latitude, a.goods.longitude) - getDistance(lat, lng, b.goods.latitude, b.goods.longitude);
}

/**
 * 最新发布
 */
function sortNew(a,b) {
    return getMillisecond(b.goods.publish_time) - getMillisecond(a.goods.publish_time);
}

/**
 * 价格升序
 */
function sortPriceAsc(a, b) {
    return a.goods.price - b.goods.price;
}

/**
 * 价格降序
 */
function sortPriceDesc(a, b) {
    return b.goods.price - a.goods.price;
}


/**
 * 搜索
 */
function search() {
    var key = $("input[name='key']").val();
    if (key !== "") {
        window.location.href = "goodlists.html?type=search&describe=" + encodeURI(encodeURI(key));
    }
    return false;
}


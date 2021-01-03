
function addCar(articleId) {
    $.ajax({
        type: "get",
        url: "/DSystem/addCarServlet",
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
                alert("添加成功");

            } else {
                //失败则在前台提示后台传的错误信息
                alert("加入失败，请稍后再试");
            }
        },
        error: function () {
            alert("连接异常，请稍后再试！");
        }
    });
}
/** 判断用户是否登陆 **/
var token = window.localStorage.getItem("token");
if (token != null && token !== "") {
    // 获取用户信息
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/user/get_userInfo" ,
        data: {token:token},
        success: function (result) {
            if(result.code === 200 && result.message === "Success") {
                window.localStorage.setItem("user", JSON.stringify(result.data)); // 将用户信息存入localStorage中

                $("#login_ul").addClass("hidden");
                $("#userinfo").removeClass("hidden");

                if (result.data.avatar === null || result.data.avatar === '[]') {
                    $("#userinfo img").attr('src',"image/avatar.png");
                } else {
                    $("#userinfo img").attr('src',getUrl()+ JSON.parse(result.data.avatar)[0]);
                }
                $("#userinfo span").text("Hi，" + result.data.nickname);

            } else {
                $("#hint").text(result.message);
            }
        },
        error : function() {
            $("#hint").text("连接异常，请刷新页面");
        }
    });
} else {
    $("#login_ul").removeClass("hidden");
    $("#userinfo").addClass("hidden");
}

/**
 * 初始化轮播图
 */
$(function () {
    $(".carousel").silder({
        auto: true, // 自动播放，传入任何可以转化为true的值都会自动轮播
        speed: 20, // 轮播图运动速度
        sideCtrl: true,// 是否需要侧边控制按钮
        bottomCtrl: true,// 是否需要底部控制按钮
        defaultView: 0,// 默认显示的索引
        interval: 3000,// 自动轮播的时间，以毫秒为单位，默认3000毫秒
        activeClass: "active",// 小的控制按钮激活的样式，不包括作用两边，默认active
    });
});

var vue;
initVue(); // 初始化Vue

/**
 * 解析定位结果
 * @param data
 */
function onComplete(data) {
    vue.getNearbyGoods(data.addressComponent.city); // 获取附近的闲置物品
    vue.getYoulikeGoods(); // 获取推荐商品
}


// /**
//  * 初始化浏览次数
//  */
// if (window.localStorage.getItem("yourBrower") === null) {
//     var yourBrower = [
//         {id:1, name:"二手书籍", time: 0},
//         {id:2, name:"二手手机", time: 0},
//         {id:3, name:"二手电脑", time: 0},
//         {id:4, name:"二手相机", time: 0},
//         {id:5, name:"3C配件", time: 0},
//         {id:6, name:"服饰鞋包", time: 0},
//         {id:7, name:"美妆捡漏", time: 0},
//         {id:8, name:"二手乐器", time: 0},
//         {id:9, name:"办公用品", time: 0},
//         {id:10, name:"生活用品", time: 0}
//     ];
//     window.localStorage.setItem("yourBrower", JSON.stringify(yourBrower));
// }

/**
 * 初始化Vue
 */
function initVue() {
    vue = new Vue({
        el: '#index',
        data: {
            categories: [
                {
                    id:2,
                    title:'闲置手机',
                    nodes:[
                        {title:'苹果', ico:'image/logo/logo_apple.png'},
                        {title:'小米', ico:'image/logo/logo_mi.png'},
                        {title:'华为', ico:'image/logo/logo_huawei.png'},
                        {title:'魅族', ico:'image/logo/logo_meizu.png'},
                        {title:'OPPO', ico:'image/logo/logo_oppo.png'},
                        {title:'vivo', ico:'image/logo/logo_vivo.png'}
                    ]
                },
                {
                    id:3,
                    title:'闲置电脑',
                    nodes:[
                        {title:'Alienware/外星人', ico:'image/logo/logo_alienware.png', url:'#'},
                        {title:'MSI/微星', ico:'image/logo/logo_msi.png', url:'#'},
                        {title:'苹果', ico:'image/logo/logo_apple.png', url:'#'},
                        {title:'Lenovo/联想', ico:'image/logo/logo_lenovo.png', url:'#'},
                        {title:'Dell/戴尔', ico:'image/logo/logo_dell.png', url:'#'},
                        {title:'HP/惠普', ico:'image/logo/logo_hp.png', url:'#'},
                    ]
                },
                {
                    id:4,
                    title:'闲置相机',
                    nodes:[
                        {title:'佳能', ico:'image/logo/logo_canon.png', url:'#'},
                        {title:'尼康', ico:'image/logo/logo_nikon.png', url:'#'},
                        {title:'索尼', ico:'image/logo/logo_sony.png', url:'#'},
                        {title:'富士', ico:'image/logo/logo_fujifilm.png', url:'#'},
                        {title:'宾得', ico:'image/logo/logo_pentax.png', url:'#'},
                        {title:'徕卡', ico:'image/logo/logo_leica.png', url:'#'}
                    ]
                },
                {
                    id:5,
                    title:'3C配件',
                    nodes:[
                        {title:'耳机', ico:'image/categories/ico_erji.png', url:'#'},
                        {title:'键盘', ico:'image/categories/ico_jianpan.png', url:'#'},
                        {title:'鼠标', ico:'image/categories/ico_shubiao.png', url:'#'},
                        {title:'内存条', ico:'image/categories/ico_nct.png', url:'#'},
                        {title:'CPU/中央处理器', ico:'image/categories/ico_cpu.png', url:'#'},
                        {title:'硬盘', ico:'image/categories/ico_yingpan.png', url:'#'}
                    ]
                },
                {
                    id:1,
                    title:'闲置书籍',
                    nodes:[]
                },
                {
                    id:6,
                    title:'服饰鞋包',
                    nodes:[
                        {title:'Adidas/阿迪达斯', ico:'image/logo/logo_adidas.png', url:'#'},
                        {title:'LV/路易威登', ico:'image/logo/logo_lv.png', url:'#'},
                        {title:'Gucci/古驰', ico:'image/logo/logo_gucci.png', url:'#'},
                        {title:'Chanel/香奈儿', ico:'image/logo/logo_chanel.png', url:'#'},
                        {title:'Tucano/啄木鸟', ico:'image/logo/logo_tucano.png', url:'#'},
                        {title:'Playboy/花花公子', ico:'image/logo/logo_playboy.png', url:'#'}
                    ]
                },
                {
                    id:7,
                    title:'美妆捡漏',
                    nodes:[
                        {title:'欧莱雅', ico:'image/logo/logo_loreal.png', url:'#'},
                        {title:'雅诗兰黛', ico:'image/logo/logo_esteelauder.png', url:'#'},
                        {title:'Chanel/香奈儿', ico:'image/logo/logo_chanel.png', url:'#'},
                        {title:'CARSLAN/卡姿兰', ico:'image/logo/logo_carslan.png', url:'#'},
                        {title:'Lancome/兰蔻', ico:'image/logo/logo_lancome.png', url:'#'}
                    ]
                },
                {
                    id:8,
                    title:'闲置乐器',
                    nodes:[
                        {title:'吉他', ico:'image/categories/ico_jita.png', url:'#'},
                        {title:'尤克里里', ico:'image/categories/ico_ykll.png', url:'#'},
                        {title:'二胡', ico:'image/categories/ico_erhu.png', url:'#'},
                        {title:'萨克斯', ico:'image/categories/ico_sks.png', url:'#'},
                        {title:'小提琴', ico:'image/categories/ico_xtq.png', url:'#'},
                        {title:'钢琴', ico:'image/categories/ico_gangqin.png', url:'#'}
                    ]
                },
                {
                    id:9,
                    title:'办公用品',
                    nodes:[
                        {title:'财务计算器', ico:'image/categories/ico_jsq.png', url:'#'},
                        {title:'印章箱', ico:'image/categories/ico_yzx.png', url:'#'},
                        {title:'美工刀', ico:'image/categories/ico_mgd.png', url:'#'},
                        {title:'白板', ico:'image/categories/ico_baiban.png', url:'#'},
                        {title:'书架', ico:'image/categories/ico_shujia.png', url:'#'},
                        {title:'电脑桌', ico:'image/categories/ico_dnz.png', url:'#'}
                    ]
                },
                {
                    id:10,
                    ico:'img/curtain.png',
                    title:'生活百货',
                    nodes:[]
                }
            ],
            carousels: [
                {url:'image/bander_0.jpg'},
                {url:'image/bander_1.png'},
                {url:'image/bander_2.png'},
                {url:'image/bander_3.jpg'},
            ],
            nearbys: [],
            youlikes: []
        },
        methods: {
            getNearbyGoods: function (city) { // 获取附近的闲置物品
                var _this = this;
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: getUrl() + "/goods/get_nearby_goodsList",
                    async: true,
                    data: {locationName:city, page:1, size:9},
                    success: function (result) {
                        if(result.code === 200 && result.message === "Success") {
                            _this.$data.nearbys = result.data;
                            _this.$forceUpdate();
                        } else {
                            alert("error code: " + result.code + ", error msg : " + result.message);
                        }
                    },
                    error : function() {
                        alert("连接异常，请稍后再试");
                    }
                });
            },
            getYoulikeGoods: function () {
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
                    url: getUrl() + "/goods/get_recommend_goodsList",
                    async: true,
                    data: {page:1, paramOne:yourBrower[0].id, OneSize:5, paramTwo:yourBrower[1].id, TwoSize:4},
                    success: function (result) {
                        if(result.code === 200 && result.message === "Success") {
                            _this.$data.youlikes = result.data;
                            _this.$forceUpdate();
                        } else {
                            alert("error code: " + result.code + ", error msg : " + result.message);
                        }
                    },
                    error : function() {
                        alert("连接异常，请稍后再试");
                    }
                });
            },
            encodeUrl: function(key) {
                return 'goodlists.html?type=search&describe=' + encodeURI(encodeURI(key));
            },
            clickCategory: function (categoryId) { // 保存用户浏览的次数
                var yourBrower = JSON.parse(window.localStorage.getItem("yourBrower"));
                for (var index = 0; index < yourBrower.length; index ++) {
                    if (yourBrower[index].id === categoryId) {
                        yourBrower[index].time = yourBrower[index].time + 1;
                    }
                }
                window.localStorage.setItem("yourBrower", JSON.stringify(yourBrower));
            }
        }
    });

}

// /**
//  * 搜索
//  */
// function search() {
//     var key = $("input[name='key']").val();
//     if (key !== "") {
//         window.location.href = "goodlists.html?type=search&describe=" + encodeURI(encodeURI(key));
//     }
//     return false;
// }



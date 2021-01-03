var map = new AMap.Map('container', {
    resizeEnable: true
});
AMap.plugin('AMap.Geolocation', function() {
    var geolocation = new AMap.Geolocation({
        enableHighAccuracy: true, // 是否使用高精度定位，默认:true
        timeout: 10000          // 超过10秒后停止定位，默认：5s
    });
    map.addControl(geolocation);
    geolocation.getCurrentPosition(function(status,result){
        if(status=='complete') {
            onComplete0(result)
        } else {
            onError0(result)
        }
    });
});

/**
 * 解析定位结果
 * @param data
 */
function onComplete0(data) {
    $("#location").text("当前定位城市：" + data.addressComponent.city);
    onComplete(data);
}

/**
 * 解析定位错误信息
 * @param data
 */
function onError0(data) {
    $("#location").text("当前定位城市：位置获取失败");
    // onError(data);
}

/**
 * 进行经纬度转换为距离的计算
 */
function Rad(d){
    return d * Math.PI / 180.0;//经纬度转换成三角函数中度分表形式。
}

/**
 * 计算距离，参数分别为第一点的纬度，经度；第二点的纬度，经度
 */
function getDistance(lat1,lng1, lat2,lng2){

    var radLat1 = Rad(lat1);
    var radLat2 = Rad(lat2);
    var a = radLat1 - radLat2;
    var  b = Rad(lng1) - Rad(lng2);
    var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
    s = s * 6378.137 ;// EARTH_RADIUS;
    s = Math.round(s * 10000) / 10; //输出为米
    //s=s.toFixed(4);
    return s;
}

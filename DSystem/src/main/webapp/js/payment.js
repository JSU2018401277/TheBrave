/**
 * 生成二维码
 */
$('#qrcode').qrcode({
    render: "canvas",
    width: 260,
    height: 260,
    text: data.qr_code,
    src: "image/logo/logo_alipay.jpg"
});







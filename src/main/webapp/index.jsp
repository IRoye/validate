<%--
  Created by IntelliJ IDEA.
  User: royeyu
  Date: 2017/10/5
  Time: 下午7:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui/layui.js"></script>
    <style>
        .error {
            border: 1px solid #FF5722!important
        }
        .disable{
            cursor: none;
            color: #f2f2f2;
        }
    </style>
</head>
<body>
<!--  from 上加上 novalidate 会默认去除h5 的非空验证-->
<form class="layui-form" action="" novalidate>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号码</label>
        <div class="layui-input-inline" style="width: 250px;">
            <input type="text" maxlength="11" name="phone" id="phone" required lay-verify="phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">验证手机</label>
        <div class="layui-input-inline" style="position: relative;width: 250px;">
            <input type="password" name="password" maxlength="6" required lay-verify="validateCode" placeholder="验证码" autocomplete="off"
                   class="layui-input" style="width: 60%;">
            <button type="button" id="getPhoneCode" class="layui-btn" style="padding:0;font-weight: 300;background-color: #f2f2f2;color: #666;position: absolute;top:0;right: 0;width: 40%;">获取验证码</button>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        </div>
    </div>
</form>
<script>
    <%-- 保证点击获取验证码手机号有值，同时加入error标红; 发送短信验证码--%>
    $(document).ready(function () {
        $("#getPhoneCode").unbind();

        $("#phone").focus(function () {
            $("#phone").removeClass("error");
        });

        $("#getPhoneCode").on('click', function (e) {
            var value = $("#phone").val();
            // 非空校验
            var reg1 = /^\s*$/g;
            // 手机号格式校验
            var reg2 = /^((1[3-8][0-9])+\d{8})$/;
            if(!reg2.test(value)){
                $("#phone").addClass('error');
                layer.msg('请输入正确的手机号格式', { icon: 5 });
            }else{
                $.ajax({
                    type : "POST",
                    url : "sendSMS",
                    data : {phone: value},
                    dataType: "json",
                    success: function(data, textStatus){

                        if(data.code == 0){
                            // 按钮显示
                            console.log("in");
                            updateButton();
                        }else{
                            layer.msg('短信发送失败，请稍后重试', { icon: 5 });
                        }
                    },
                    error: function(){
                        layer.msg('短信发送失败，请稍后重试', { icon: 5 });
                    }
                });
            }
        });
    });
    var countdown = 120;
    function updateButton(){
        var getPhoneCode = $("#getPhoneCode");
        if (countdown == 0) {
            getPhoneCode.attr("disabled", false);
            getPhoneCode.removeClass("disabled");
            getPhoneCode.text("获取验证码");
            countdown = 120;
            return;
        } else {
            getPhoneCode.attr("disabled", true);
            getPhoneCode.addClass("disabled");
            getPhoneCode.text("重新发送(" + countdown + ")");
            countdown--;
        }
        setTimeout(function() { updateButton() } ,1000);
    }
    /*  input 验证 */
    layui.use('form', function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });

        form.verify({
            validateCode: function (value, item) {
                var reg = /^\s*$/g;
                if (reg.test(value)) {
                    return '请先获取验证码'
                }
            }
        });
    });

/*  发送短信验证码  */


</script>
</body>
</html>

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
    <script src="js/jquery-3.2.1.js"></script>
    <script src="layui/layui/layui.js"></script>
    <style>
        .error {
            border: 1px solid #FF5722!important
        }
    </style>
</head>
<body>
<!--  from 上加上 novalidate 会默认去除h5 的非空验证-->
<form class="layui-form" action="" novalidate>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号码</label>
        <div class="layui-input-inline" style="width: 250px;">
            <input type="number" name="phone" id="phone" required lay-verify="phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">验证手机</label>
        <div class="layui-input-inline" style="position: relative;width: 250px;">
            <input type="password" name="password" maxlength="6" required lay-verify="validateCode" placeholder="验证码" autocomplete="off"
                   class="layui-input" style="width: 60%;">
            <button type="button" id="getPhoneCode" class="layui-btn" style="position: absolute;top:0;right: 0;width: 40%;">获取验证码</button>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
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
            }
        });
    });
    //Demo
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
</script>

</html>

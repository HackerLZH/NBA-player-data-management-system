//jquery-validate
$(function () {
    //表单验证
    $("#registerForm").validate({
        rules: {
            username: {
                required: true,
                minlength: 3,
                maxlength: 10
            },
            email: {
                required: true,
                email: true,
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
            checkPassword: {
                required: true,
                equalTo: "#password"
            },
            code: {
                required: true
            }
        },
        messages: {
            username: {
                required: "网名不能为空！"
            },
            email: {
                required: "邮箱不能为空！",
                email: "邮箱格式应为XXX@XXX.com"
            },
            password: {
                required: "密码不能为空！"
            },
            checkPassword: {
                required: "确认密码不能为空！",
                equalTo: "确认密码和密码不一致！"
            },
            code: {
                required: "验证码不能为空！"
            }
        },
        //指定使用什么标签标记错误，默认是label
        errorElement: "em",
        //自定义错误样式和位置
        errorPlacement: function (error, element) {
            error.addClass("error");
            error.insertAfter(element);
        },
        //给未通过验证的元素加效果、闪烁等
        highlight: function (element, errorClass, validClass) {
            $(element).addClass("is-invalid").removeClass("is-valid");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).addClass("is-valid").removeClass("is-invalid");
        }
    });
    //验证网名是否被使用
    jQuery.validator.addMethod("checkIfNameUsed", function (value, element) {
        let flag = false; //false表示未被使用
        $.ajax({
            url: "/user/register/checkName?username=" + $("#username").val(),
            type: "get",
            async: false,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data.code === 500300) flag = true;
            }
        });
        return this.optional(element) || !flag;
    }, "该网名已被使用！");
    //验证邮箱是否被注册过
    jQuery.validator.addMethod("checkIfEmailRegistered", function (value, element) {
        let flag = false; //false表示未注册
        $.ajax({
            url: "/user/login/checkEmail?email=" + $("#email").val(),
            type: "get",
            async: false,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data.code === 500205) flag = true;
            }
        });
        return this.optional(element) || !flag;
    }, "一个邮箱只能注册一次！");
    //检查验证码是否过期
    jQuery.validator.addMethod("checkIfCodeExpired", function (value, element) {
        let flag = false; //false表示未过期
        $.ajax({
            url: "/user/register/checkCode?code=" + $("#code").val(),
            type: "get",
            async: false,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data.code === 555) flag = true;
            }
        });
        return this.optional(element) || !flag;
    }, "验证码已过期|验证码错误！");

    $('svg').click(function () {
        let email = $("#email").val();
        if (email === '') {
            $(".alert-warning").show().delay(1000).fadeOut();
            return;
        }
        $(".alert-success").show().delay(1000).fadeOut();
        $.ajax({
            url: "/user/mail/sendCode?email=" + email,
            type: "get",
            dataType: 'json',
            success: function (data) {
                console.log(data);
            }
        });
    });
    //表单提交
    $('#registerBtn').click(function(){
        $('.alert-info').show().delay(1000).fadeOut();
        setTimeout(function(){
            $('#registerForm').submit();
        }, 1500);
    })
});
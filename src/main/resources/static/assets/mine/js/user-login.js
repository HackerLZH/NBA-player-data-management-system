//jquery-validate
$(function () {
    //表单验证
    $('#loginForm').validate({
        rules: {
            email: {
                required: true,
                email: true,
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 20
            }
        },
        messages: {
            email: {
                required: "邮箱不能为空！",
                email: "邮箱格式应为XXX@XXX.com"
            },
            password: {
                required: "密码不能为空！"
            }
        },
        //指定使用什么标签标记错误，默认是label
        errorElement: "em",
        //自定义错误样式和位置
        errorPlacement: function (error, element) {
            error.addClass("error");
            if (element.prop("type") === "checkbox") {
                error.insertAfter(element.next("label"));
            } else {
                error.insertAfter(element);
            }
        },
        //给未通过验证的元素加效果、闪烁等
        highlight: function (element, errorClass, validClass) {
            $(element).addClass("is-invalid").removeClass("is-valid");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).addClass("is-valid").removeClass("is-invalid");
        }
    });
    //检查邮箱是否注册过
    jQuery.validator.addMethod("checkIfEmailRegistered", function (value, element) {
        let flag = false; //false表示未注册
        $.ajax({
            url: "/user/login/checkEmail?email=" + $("#email").val(),
            type: "get",
            async: false,
            dataType: 'json',
            success: function(data){
                console.log(data);
                if (data.code === 500205) flag = true;
            }
        });
        return this.optional(element) || flag;
    }, "该邮箱未曾注册！");
    //检查密码是否匹配邮箱
    jQuery.validator.addMethod("checkIfPasswordMatched", function (value, element) {
        let flag = false; //false表示密码不正确
        const email = $("#email").val();
        const password = $("#password").val();
        $.ajax({
            url: "/user/login/checkPassword?email=" + email + "&password=" + password,
            type: "get",
            async: false,
            dataType: 'json',
            success: function(data){
                console.log(data);
                if (data.code === 500206) flag = true;
            }
        });
        return this.optional(element) || flag;
    }, "密码错误！");
    //表单提交
    $('#loginBtn').click(function(){
        $('.alert-info').show().delay(1000).fadeOut();
        setTimeout(function(){
            $('#loginForm').submit();
        }, 1500);
    })
});
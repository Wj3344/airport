var checkSignUpFunction = function () {
    var checkButton = $("#signButton");
    checkButton.attr("disabled", "disabled");
    var username = $("#username");
    var password = $("#password");
    var repeatPassword = $("#re-password");
    if (!(password.val() === repeatPassword.val())) {
        alert("两次填写的密码不一致！请重新填写！");
        checkButton.removeAttr("disabled");
        return false;
    }
    if ($("#flightNumber").val() == null) {
        checkButton.removeAttr("disabled");
        alert("请选择账号等级！");
        return false;
    }
    var postData = {};
    postData.username = username.val();
    postData.password = password.val();
    postData.identity = $('#flightNumber option:selected').val();
    // 封装数据
    $.ajax({
        type: "POST",
        url: "/signUp",
        dataType: "json",
        data: postData,
        success: function (response) {
            alert(response.msg);
            if (response.status === 200) {
                window.location.href = "/login";
            }
        },
        error: function (xhr) {
            console.log("错误提示： " + xhr + " ---- " + xhr.status + " " + xhr.statusText);
        },
        //请求完成后回调函数 (请求成功或失败之后均调用)。参数： XMLHttpRequest 对象和一个描述成功请求类型的字符串
        complete: function (XMLHttpRequest, textStatus) {
            console.log("函数调用完成，将按钮设置为可用状态");
            // 请求完成，将按钮重置为可用
            checkButton.removeAttr("disabled");
        }
    });
};
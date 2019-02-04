/**
 * post请求提交数据
 * @param url 数据提交地址
 * @param data 数据
 * @param btn 释放按钮
 * @param callbackAddress 回调地址
 */
var postRequest = function (url, data, btn, callbackAddress) {
    // 封装数据
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: data,
        success: function (response) {
            alert(response.msg);
            if (response.status === 200) {
                // 数据修改成功，重新跳转回查询界面
                if (isEmpty(callbackAddress)) {
                    callbackAddress = "/index";
                }
                window.location.href = callbackAddress;
            }
        },
        error: function (xhr) {
            console.log("错误提示： " + xhr + " ---- " + xhr.status + " " + xhr.statusText);
        },
        //请求完成后回调函数 (请求成功或失败之后均调用)。参数： XMLHttpRequest 对象和一个描述成功请求类型的字符串
        complete: function (XMLHttpRequest, textStatus) {
            console.log("函数调用完成，将按钮设置为可用状态");
            // 请求完成，将按钮重置为可用
            btn.removeAttr("disabled");
        }
    });
};

var isEmpty = function (v) {
    switch (typeof v) {
        case 'undefined':
            return true;
        case 'string':
            if (v.replace(/(^[ \t\n\r]*)|([ \t\n\r]*$)/g, '').length === 0) return true;
            break;
        case 'boolean':
            if (!v) return true;
            break;
        case 'number':
            if (0 === v || isNaN(v)) return true;
            break;
        case 'object':
            if (null === v || v.length === 0) return true;
            v.forEach(function (element) {
                return false;
            })
            return true;
    }
    return false;
};

var cleanPageCheckSubmit = function () {
    // 将上传按钮设置位不可用状态
    var checkButton = $("#check-btn");
    checkButton.attr("disabled", "disabled");
    var inPlace = $("#inPlace");
    var inPlaceValue = inPlace.val();
    if (inPlaceValue === "") {
        alert("请填写到位时间！");
        checkButton.removeAttr("disabled");
        return false;
    }
    // 将时间转换长时间戳
    var dateTime = new Date(inPlaceValue).getTime();
    // 查询清洁用时时间
    var usedTime = $("#usedTime");
    if (usedTime.val() === "") {
        alert("请填写清洁用时！");
        usedTime.focus();
        checkButton.removeAttr("disabled");
        return false;
    }
    // 提交数据
    var postData = {};
    postData.flightInformationId = $('#flightNumber option:selected').val();
    postData.readTime = dateTime;
    postData.usedTime = usedTime.val();
    postData.specialCase = $("#specialCircumstances").val();
    console.log(postData);
    postRequest("/clean/add", postData, checkButton, "/clean/list");
    checkButton.removeAttr("disabled");
};
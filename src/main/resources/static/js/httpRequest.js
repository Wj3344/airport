/**
 * post请求提交数据
 * @param postUrl 请求地址
 * @param postData 请求数据
 * @param checkButton 检查按钮
 * @param callbackAddress 回调地址
 */
var postRequest = function (postUrl, postData, checkButton, callbackAddress) {
    // 封装数据
    $.ajax({
        type: "POST",
        url: postUrl,
        dataType: "json",
        data: postData,
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
            checkButton.removeAttr("disabled");
        }
    });
};

/**
 * 判断输入的参数是否为空
 * @param str 参数
 * @returns {boolean} true表示为输入参数为空
 */
var isEmpty = function (str) {
    //空引用  空字符串  空输入
    return str == null || typeof str == "undefined" || str.toString().replace(/^\s*|\s*$/g, "") === "";
};


/**
 * 清洁信息添加
 * @returns {boolean}
 */
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
    postRequest({
        parameters: {
            parameters: {
                url: "/clean/add",
                data: postData,
                btn: checkButton,
                callbackAddress: "/clean/list"
            }
        }
    });
    checkButton.removeAttr("disabled");
};

/**
 * 清洁信息修改提交
 */
var cleanModifyCheckSubmit = function () {
    // 调用代码
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
    // 数据封装
    var modifyData = {};
    modifyData.id = $("#recordId").val();
    modifyData.flightInformationId = $('#flightNumber option:selected').val();
    modifyData.readTime = dateTime;
    modifyData.usedTime = usedTime.val();
    modifyData.specialCase = $("#specialCircumstances").val();
    postRequest("/clean/modify", modifyData, checkButton, "/clean/list");
    checkButton.removeAttr("disabled");
};

/**
 * 值机信息提交
 */
var checkInPageSubmit = function () {
    var checkButton = $("#check-btn");
    checkButton.attr("disabled", "disabled");
    // 封装请求数据
    var postData = {};
    postData.flightInformationId = $('#flightNumber option:selected').val();
    postData.realNumber = $("#peopleNumber").val();
    postData.luggageNumber = $("#baggageNumber").val();
    postData.specialCase = $("#specialCircumstances").val();
    // 提交数据
    postRequest({
        parameters: {
            parameters: {
                url: "/checkIn/add",
                data: postData,
                btn: checkButton,
                callbackAddress: "/checkIn/search"
            }
        }
    });
    checkButton.removeAttr("disabled");

};
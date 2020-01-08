//登出用户
function loginOut() {
    $.ajax({
        url: "/logout",
        type: "POST",
        data: {},
        dataType: "json",
        success: function(data){
            if (data.type === 'success'){
                //注销成功
                $("#personalInformation").html("" +
                    "<li>\n" +
                    "   <a href=\"/login.html\">\n" +
                    "       登录\n" +
                    "   </a>\n" +
                    "   </li>");
            }
        },
        error: function () {
            alert("系统异常，注销失败");
        }
    });
}
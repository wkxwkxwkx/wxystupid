var searchResult = function (){
    //搜索框的内容
    var globalSearch = $("#searchInput").val();

    //城市
    $(".city").click(function (){
        var city = $(this).attr("index");
    })
    //工作经验
    $(".jobExp").click(function (){
        var jobExp = $(this).attr("index");
    })
    //学历要求
    $(".edu").click(function (){
        var edu = $(this).attr("index");
    })
    //薪资水平
    $(".salary").click(function (){
        var alary = $(this).attr("index");
    })
    $.ajax({
        type:'post',
        url:'ResultServlet1',
        data:{"global":globalSearch},//这三行是前端向后端的请求
        success:function (value){
            console.log(value.data);
            var data = value.data;
            var html ="";
            for (var i = 0; i < data.length; i++) {
                html+=`<li class="k1" index="${data[i].id}">`
                html+=`    <a href="#javascript" class="wxwx1">${data[i].job_name.split("（")[0]}</a>`
                html+=`    <span>${data[i].uptime.split(" ")[0]}</span>`
                html+=`    <div class="lianxi"></div>`
                html+=`    <p class="pay">${data[i].salary}</p>`
                html+=`    <p class="ask">`
                html+=`        <span>&nbsp;&nbsp;</span>`
                html+=`        <span>&nbsp;&nbsp;</span>`
                html+=`    </p>`
                html+=`    <div class="input" style="margin-top: -10px">${data[i].job_address}</div>`
                html+=`    <div class="center input" style="margin-top: -10px">${data[i].job_exp}</div>`
                html+=`    <div class="input" style="margin-top: -10px">${data[i].edu}</div>`
                html+=`    <div class="bottom">`
                html+=`        <a><img style="width:30px;" src="${data[i].company_logo}" alt=""></a>`
                html+=`        <a  class="wz1">${data[i].company}</a>`
                html+=`        <a  class="wz2" style="text-decoration:none;color: #999">${data[i].source}</a>`
                html+=`    </div>`
                html+=`</li>`
            }
            $("#inf").html(html);
        }
    })
}
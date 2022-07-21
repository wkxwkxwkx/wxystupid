var userId = getCookie("userId");
var jobId = getCookie("jobId");
var collection1 = function (){
    $.ajax({
        type:'post',
        url:'CollectionServlet1',
        data:{"userId":userId},
        success:function (value){
            console.log(value.data);
            var data = value.data;
            var html='';
            for (var i = 0; i < data.length ; i++) {
                html+=`<li class="k1" style="height: 100px;">`
                html+=`    <a>${data[i].job_name.split("（")[0]}</a>`
                html+=`    <span>${data[i].job_address}</span>`
                html+=`    <div class="lianxi"></div>`
                html+=`    <p class="pay">${data[i].salary}</p>`
                html+=`    <p class="ask">${data[i].company}</p>`
                html+=`    <a  class="wz3" index="${data[i].id}" style="text-decoration:none;color: #999;margin-top: -30px">取消收藏</a>`
                html+=`</li>`
            }
            $("#inf").html(html);
        }
    })
}


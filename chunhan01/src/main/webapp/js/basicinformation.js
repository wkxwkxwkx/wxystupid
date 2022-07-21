function message(){
    var userId = getCookie("userId");
    $.ajax({
        type:'post',
        data:{userId},
        url:'basicInformationServlet',
        success:function (value){
            console.log(value.data);
            var data = value.data;
            var html="";
            html+=`  <form>`
            html+=`      <div class="input-group mb-3" style="width:60%;margin: 0 auto ; ">`
            html+=`          <input type="text" id="school" class="form-control" placeholder="${data[0].school}">`
            html+=`      </div>`
            html+=`      <div class="input-group" style="width: 60%;margin: 0 auto ;">`
            html+=`          <input type="text" id="address" class="form-control" placeholder="${data[0].address}">`
            html+=`              <span class="input-group-text"></span>`
            html+=`      </div>`
            html+=`      <br/>`
            html+=`      <select class="form-select" id="sel2" name="sellist2" style="width: 60%;margin: 0 auto ;">`
            html+=`          <option value="none" selected disabled hidden>${data[0].edu}</option>`
            html+=`          <option>大专</option>`
            html+=`          <option>本科</option>`
            html+=`          <option>硕士</option>`
            html+=`          <option>博士</option>`
            html+=`          <option>博士后</option>`
            html+=`          <option>其他</option>`
            html+=`      </select>`
            html+=`      <br/>`
            html+=`      <select class="form-select" id="sel3" name="sellist2" style="width: 60%;margin: 0 auto ;"required>`
            html+=`          <option value="none" selected disabled hidden>${data[0].job_exp}</option>`
            html+=`          <option>在校生</option>`
            html+=`          <option>应届生</option>`
            html+=`          <option>1-3年</option>`
            html+=`          <option>3-5年</option>`
            html+=`          <option>5年以上</option>`
            html+=`      </select>`
            html+=`      <br/>`
            html+=`      <br/>`
            html+=`      <input id="update" index="${data[0].id}" type="button" value="修改" class="btn btn-primary">`
            html+=`  </form>`
            $("#superDIVV").html(html);
        }
    })
}

var updateInformation = function (){
    $("#superDIVV").on("click","#update",function (){
        var userId = $(this).attr("index");
        var school = $("#school").val();
        var address = $("#address").val();
        var edu = $("#sel2").val();
        var job_exp = $("#sel3").val();
        $.ajax({
            type:'post',
            url:'updateInformationServlet',
            data: {
                "userId":userId,
                "school":school,
                "address":address,
                "edu":edu,
                "job_exp":job_exp},
            success:function (value){
                console.log(value)
                if (value.code=="000"){
                    alert("修改成功");
                    location.reload();
                }
            }
        })

    })
}
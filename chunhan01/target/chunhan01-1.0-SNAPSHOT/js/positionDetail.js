var jobId = getCookie("jobId");
var userId = getCookie("userId");

var view =function (){
    $.ajax({
        type:'get',
        url:'PositionDetailServlet1',
        data:{"jobId":jobId},
        success:function (value){
            console.log(value.data);
            var data = value.data;
            var html="";
            for (var i = 0; i < data.length; i++) {
                html+=`           <div id="superDIVvv">`
                html+=`               <div id="job-banner">`
                html+=`                   <div class="inner">`
                html+=`                       <div class="banner-job-detail">`
                html+=`                           <div class="job-info">`
                html+=`                               <div class="job-status">   </div>`
                html+=`                               <div class="job-name">${data[i].job_name} <span class="salary">${data[i].salary}</span></div>`
                html+=`                               <p>`
                html+=`                                   <a href="#">${data[i].job_address}</a>`
                html+=`                                   <em class="dot"></em> ${data[i].job_exp}`
                html+=`                                   <em class="dot"></em> ${data[i].edu}`
                html+=`                               </p>`
                html+=`                           </div>`
                html+=`                           <div class="job-op">`
                html+=`         <button type = "button" style = "width:160px;height: 47.5px;margin-bottom:3px" id = "collect" onclick="collectFunction()" class = "btn btn-outline-warning"> 收藏 </button>`
                html+=`         <button type = "button" style = "width:160px;height: 47.5px;margin-bottom:3px;display: none;" id = "deleteCollect" onclick="delCollectFunction()" class = "btn btn-warning"> 取消收藏 </button>`
                html+=`                               <a href="${data[i].source}"> <button class="contact">前往</button> </a>`
                html+=`                           </div>`
                html+=`                       </div>`
                html+=`                   </div>`
                html+=`               </div>`
                html+=`               <div id="job-box">`
                html+=`                   <div class="inner">`
                html+=`                       <div class="side-bar">`
                html+=`                           <div class="company-summary">`
                html+=`                               <h3>公司基本信息</h3>`
                html+=`                               <div class="company-info">`
                html+=`                                   <a class="company-info-logo" href="#">`
                html+=`                                       <img src="${data[i].company_logo}">`
                html+=`                                   </a>`
                html+=`                                   <a class="company-info-name" href="#">`
                html+=`                                       ${data[i].company}`
                html+=`                                   </a>`
                html+=`                               </div>`
                html+=`                               <p style="color:rgb(68,76,96);">`
                html+=`                                   <i class="icon-stage icon-company-sumary"></i>${data[i].finance_stage}`
                html+=`                               </p>`
                html+=`                               <p style="color:rgb(68,76,96);">`
                html+=`                                   <i class="icon-scale icon-company-sumary"></i> ${data[i].company_size}`
                html+=`                               </p>`
                html+=`                               <p style="color:rgb(68,76,96);">`
                html+=`                                   <i class="icon-industry icon-company-sumary"></i> ${data[i].industry_field}`
                html+=`                               </p>`
                html+=`                              <p class="upload-date">${data[i].uptime}</p>`
                html+=`                          </div>`

                html+=`                           <div class="history">`
                html+=`                               <div class="history-title-wapper">`


                html+=`                               </div>`
                html+=`                               <div class="history-list">`
                html+=`                                   <ul>`

                html+=`                                   </ul>`
                html+=`                               </div>`
                html+=`                           </div>`
                html+=`                       </div>`
                html+=`                       <div class="job-detail">`
                html+=`                           <div class="detail-op">`

                html+=`                           </div>`
                html+=`                           <div class="detail-content">`
                html+=`                               <div class="job-desc">`
                html+=`                                   <h3>职位描述</h3>`
                html+=`                                   <div class="text">`
                html+=`                                       <p>岗位职责：</p>`
                html+=`                                   </div>`
                html+=`                               </div>`
                html+=`                               <div class="company-desc">`

                html+=`                               </div>`
                html+=`                               <div class="competition-desc">`

                html+=`                               </div>`
                html+=`                               <div class="business-desc">`

                html+=`                               </div>`
                html+=`                               <div class="work-address">`

                html+=`                               </div>`
                html+=`                               <div class="related-job">`

                html+=`                               </div>`
                html+=`                           </div>`
                html+=`                           <div class="recommend-job">`

                html+=`                           </div>`
                html+=`                       </div>`
                html+=`                   </div>`
                html+=`               </div>`
                html+=`           </div>`
                html+=`       </div>`
                html+=`       </div>`

            }
            $("#superDIVvv").html(html);
        }
    })
}

var collectFunction = function (){
    document.getElementById('collect').style.display='none';
    document.getElementById('deleteCollect').style.display='block';
}

var delCollectFunction = function (){
    document.getElementById('deleteCollect').style.display='none';
    document.getElementById('collect').style.display='block';
}

var collectionJob = function (){
    $.ajax({
        type: "post",
        url:"AddCollection1",
        data:{
            "jobId":jobId,
            "userId":userId
        },
        success:function (data){
            console.log(data)
            if (data.code == "000"){
                alert("收藏成功");
            }else {
                alert("该职位已在收藏列表中");
            }
        }
    })

}
var delCollectionJob = function (){

    $.ajax({
        type:"post",
        data:{
            "jobId":jobId,
            "userId":userId
        },
        url:"DelCollectionPosition",
        success:function (data) {
            if (data.code = "000") {
                alert("成功取消收藏");
            }
        }
    })
}
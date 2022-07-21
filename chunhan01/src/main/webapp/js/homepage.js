$(function(){
    $('.pic').html($('.pic').html()+$('.pic').html());  // 复制一份放在最后
    $('.pic').css('width',$('.pic li').width()*$('.pic li').length);  // 计算ul的总宽度
    var n = 0;
    var len = $('.pic li').length;
    var str = '';
    var timer = null;
    for(var i=0; i<len/2; i++){
        str += '<li></li>';
    }
    $('.dot').html(str);
    $('.dot li').eq(0).css({width:'15px',opacity:1});
    // 自动播放
    timer = setInterval(autoPlay,3000);
    function autoPlay(){
        n++;
        $('.dot li').removeClass('active');
        $('.dot li').css({width:'7px',opacity:'0.5'});
        if(n > len/2){
            n=1;
            $('.pic').css('left','0');
        }
        if(n == len/2){
            $('.dot li').eq(0).animate({width:'15px',opacity:1});
        }
        $('.dot li').eq(n).animate({width:'15px',opacity:1});
        $('.pic').animate({left:-($('.pic li').width()*n)});
    }
    $('.dot li').click(function(){
        clearInterval(timer);
        n = $(this).index();
        $('.dot li').css({width:'7px',opacity:'0.5'});
        $(this).animate({width:'15px',opacity:1});
        $('.pic').animate({left:-($('.pic li').width()*n)});
        timer = setInterval(autoPlay,3000);
    })
    $('.lb_banner').hover(function(){
        $('.btn').show();
    },function(){
        $('.btn').hide();
    });
    $('.next').click(function(){
        clearInterval(timer);
        autoPlay();
        timer = setInterval(autoPlay,3000);
    });
    $('.prev').click(function(){
        clearInterval(timer);
        n = n - 2;
        if(n<-1){
            n = len/2;
            $('.pic').css('left',-($('.pic li').width()*n));
            n = n -2;
        }
        autoPlay();
        timer = setInterval(autoPlay,3000);
    });
})

 var JobRecommendation = function(){
    $.ajax({
        url: "AdviceWorkServlet1",
        type: "get",
        data:{},
        success:function (value) {
            console.log(value.data);
            var data = value.data;
            var html ="";
            for (var i = 0; i <data.length; i++) {
                html+=`<li class="k1" index="${data[i].id}">`
                html+=`    <a class="wxwx1">${data[i].job_name.split("（")[0]}</a>`
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
                html+=`        <a href=""><img style="width:30px;" src="${data[i].company_logo}" alt=""></a>`
                html+=`        <a class="wz1">${data[i].company}</a>`
                html+=`        <a  href="#javascript" class="wz2" style="text-decoration:none;color: #999">${data[i].source}</a>`
                html+=`    </div>`
                html+=`</li>`
            }
            $("#inf").html(html);
        }
    });
}



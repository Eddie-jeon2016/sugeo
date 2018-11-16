$(document).ready(function () { 
            //   인터넷윤리대전 슬로건 수상작 배너
                $('.prizelst').bxSlider({
                    mode:"vertical",
                    speed:1000,
                    pause:7000,
                    moveSlides:1,
                    slideMargin:0,
                    auto:true,
                    autoHover:false,
                    autoControls:false,
                    pager:false,
                    controls:false,
                    infiniteLoop: true
                });

            //   메인배너 슬라이드
                $('.visuslider').bxSlider({
                    mode:"horizontal", 
                    speed:1000,
                    pause:7000,
                    moveSlides:1,
                    slideMargin:0,
                    auto:true,
                    autoHover:false, 
                    autoControls:true,
                    infiniteLoop: true
                });

                //   아인세 드림 프로젝트 슬라이드
                var drmSlider = $('.dreamslide').bxSlider({
                    mode:"horizontal",
                    speed:1000,
                    pause:7000,
                    moveSlides:1,
                    slideMargin:0,
                    auto:true,
                    autoHover:false, 
                    autoControls:true,
                    pager:false,
                    controls:true,
                    infiniteLoop: true
                });

                // $( '#prevbtn' ).on( 'click', function () {
                //        drmSlider.goToPrevSlide(); 
                //        return false; 
                //  } );

                // $( '#nextbtn' ).on( 'click', function () {
                //        drmSlider.goToNextSlide(); 
                //        return false; 
                //  } );
                $('.dreamslidewrp .bx-start').hide();
                $('.dreamslidewrp .bx-stop').click(function(e){
                    $(this).hide();
                    $(this).parents(".bx-controls-auto").find('.bx-start').show();
                });
                $('.dreamslidewrp .bx-start').click(function(e){
                    $(this).hide();
                    $(this).parents(".bx-controls-auto").find('.bx-stop').show();
                }); 

                $('#mainvisu .bx-start').hide();
                $('#mainvisu .bx-stop').click(function(e){
                    $(this).hide();
                    $(this).parents(".bx-controls-auto").find('.bx-start').show();
                });
                $('#mainvisu .visuslider .bx-start').click(function(e){
                    $(this).hide();
                    $(this).parents(".bx-controls-auto").find('.bx-stop').show();
                }); 

            //   전체메뉴
                //$('#totalview').css({'display':'none'});
                $('.allmenu a').click(function(){
                    if(($('#totalview').css("display")) != "none"){
                        $('#totalview').css({display:'none'});
                    }else{
                        $('#totalview').css({display:'block'});
                    }
                });
                $('.closeTotalView').click(function(){
                    $('#totalview').css({display:'none'});
                });


                //   통합검색
                $('#total-search').css({display:'none'});
                $('.alltotalsrch a').click(function(){

                    if(($('#total-search').css("display")) != "none"){
                        $('#total-search').css({display:'none'});
                    }else{
                        $('#total-search').css({display:''});
                    }
                }); 

  
        });







// // introvdeo

 
// var bannerPlay = true;

// var iBannerCnt = 0;

// bannerInt = setInterval("moveBanner('R')", 3000);

// function moveBanner(reqDir)
// { 

//   var ListSize = $(".box_bannerZone > ul >li").length;
//   var viewSize = 5;

// 	if(reqDir == "L")
// 	{
// 		if(iBannerCnt <= 1) iBannerCnt = ListSize;
// 		else
// 			iBannerCnt -= 1;
// 	}
// 	else
// 	{
// 		if(iBannerCnt >= ListSize) iBannerCnt = 1;
// 		else
// 			iBannerCnt += 1;
// 	}
// 	$(".box_bannerZone > ul > li").hide();
	
// 	var thisId = iBannerCnt - 1;

// 	var $thisLi = $("#banner"+thisId);
// 	var $thisParentUl = $thisLi.parent();
// 	var $thisCloneLi = $thisLi.clone();
// 	$thisLi.remove();
	
// 	if(reqDir == "L") {
// 		$thisParentUl.prepend($thisCloneLi);
// 	}else {
// 		$thisParentUl.append($thisCloneLi);
// 	}
// 	$thisParentUl.children("li:nth-child(1), li:nth-child(2), li:nth-child(3), li:nth-child(4), li:nth-child(5)").show();
	
// 	clearInterval(bannerInt);
	
// 	if(bannerPlay == true)
// 		bannerInt = setInterval("moveBanner('R')", 3000);
// }

// //�˸��� �̵�����
// function goBannerPlay() {
// 	clearInterval(bannerInt);
// 	bannerInt = setInterval("moveBanner('R')", 3000);
// 	document.getElementById('bannerStart').style.display = "none";
// 	document.getElementById('bannerStop').style.display = "block";
// 	bannerPlay = true;
// }

// //�˸��� �̵�����
// function goBannerStop() {
// 	clearInterval(bannerInt);
// 	document.getElementById('bannerStart').style.display = "block";
// 	document.getElementById('bannerStop').style.display = "none";
// 	bannerPlay = false;
// }




$(document).ready(function(){
	/* GNB */
	var current;

	$(".gnb > .gnbbg").hover(function(){
		current = $(this).index();

		$(".gnb > .gnbbg").each(function(i){
			if(current == i){

				//over
				$(this).find(" > a").addClass("on");
				$(this).find(".gnb_depth").show();
			}else{
				//out
				$(this).find(" > a").removeClass("on");
				$(this).find(".gnb_depth").hide();
			};
	});

	},  function(){

			current = $(".gnb > .gnbbg").length;

			$(".gnb > .gnbbg").each(function(i){
			if(current == i){
				//over
				$(this).find(" > a").addClass("on");
				$(this).find(".gnb_depth").show();
			}else{
				//out
				$(this).find(" > a").removeClass("on");
				$(this).find(".gnb_depth").hide();
			};
		});
	});


 	function snb() {
		var snb = $(".snb > li");
		var snbA = snb.find("> a.tlocation");

		snbA.on("mouseenter focusin", function () {
			if ($(this).hasClass("on")) {
				$(this).next("ul").stop(true, true).slideUp();
				$(this).removeClass("on");
			}
			else {
				$(this).addClass("on");
				$(this).next("ul").stop(true, true).slideDown();
			}
			return false;
		});
		snb.on("mouseleave focusout", function () {
			if (snbA.hasClass("on")) {
				snbA.next("ul").stop(true, true).slideUp();
				snbA.removeClass("on");
			}
			return false;
		});
		snb.find(".depth").on("mouseleave focusout", function () {
			if (snbA.hasClass("on")) {
				snbA.next("ul").stop(true, true).slideUp();
				snbA.removeClass("on");
			}
			return false;
		});

		 
		
		for (var j=0; j<snb.length; j++) {
			var snbArray = snb.eq(j);

			snbArray.each(function(){
				var aaa=$(this).find("> .depth > li");
				for (var i=0; i<aaa.length; i++) {
					var aa = aaa.eq(i);

					if ( $(this).children("a.tlocation").children("span").text() == aa.text() ){
						aa.addClass("on");
					} else if ( $(this).children("a.tlocation").children("span").text() == $(".depth > li.first > a").text()) {
						$(".depth > li.first > a").parent().addClass("on");
					} else {
						aa.removeClass("on");
					}
				}
			});
		}
		
		$(".snb > li:last > a").css({"font-weight":"700", "color":"#232323"});
	}
	snb();	


	/* scroll Top */
	var _product = false;
	var printInter;

	    $("#cmTopScroll").click(function (){
	       // $("html, body").animate({scrollTop:0}, 500);
	    });
	    $(window).scroll(function (){
	        var top = $(document).scrollTop();

	        if(top > 50){
	            $("#cmTopScroll").stop().animate({"bottom":80}, 150);
	        }else{
	            $("#cmTopScroll").stop().animate({"bottom":-80}, 150);
	        }
	    });






	    /* 
	    ----------------------------------------------------------------
	    --------------- 아래부터 서브 관련 스크립트 -------------------
	    ----------------------------------------------------------------
	    */
	   
	    //   tbt_twodep 태블릿이하 2뎁스 
                $('.tbt_twodep .picka').click(function(){

                    if(($('#innerdep').css("display")) != "none"){
                        $('#innerdep').css({display:'none'});
                        $(this).css({'background-image':'url(../../images/sub_common/tbtclose.png)'});
                    }else{
                        $('#innerdep').css({display:'block'});
                        $(this).css({'background-image':'url(../../images/sub_common/tbtopen.png)'});
                    }
                });

             //   tbt_thrdep 태블릿이하 3뎁스 
                var tbtthrSlider = $('.tbt_thrdep #innerdep > ul').bxSlider({
                    mode:"horizontal", 
                    speed:1000,
                    moveSlides:2,
                    minSlides: 2,
			maxSlides: 2,
                    slideMargin:0,
                    slideWidth: 500,
                    auto:false,
                    autoHover:false, 
                    autoControls:false,
                    pager:false,
                    controls:false 
                });

                $( '.tbt_thrdep #prvbtn' ).on( 'click', function () {
                       tbtthrSlider.goToPrevSlide(); 
                       return false; 
                 } );

                $( '.tbt_thrdep #nxtbtn' ).on( 'click', function () {
                       tbtthrSlider.goToNextSlide(); 
                       return false; 
                 } );


		                
		/* flow label */
		$(document).ready(function(){
			if(!$("label").is(".flow")) return false;
			
			var o1 = $("label.flow");
			var o2 = $("label.flow").next();
			
			o1.css({"position":"absolute"});

			o1.bind("click focusin",function(){
				$(this).css({"visibility":"hidden"});
			});
			o2.bind("click focusin",function(){
				$(this).prev().css({"visibility":"hidden"});
			});

			o2.bind("focusout",function(){
				if($(this).val() == ""){
					$(this).prev().css({"visibility":"visible"});
				}
			});

			if(o2.val() != "") o1.css({"visibility":"hidden"});
		});
 

		/* 댓글 이모티콘 */
                //$('.emo_view').css({display:'none'});
                $('.emo_atg').click(function(){

                    if(($('.emo_view').css("display")) != "none"){
                        $('.emo_view').css({display:'none'});
                        //$('.emo_atg').removeClass("on");
                    }else{
                        $('.emo_view').css({display:'block'});
                        //$('.emo_atg').addClass("on");
                    }
                }); 
                 $('.emono').click(function(){
                    $('.emo_view').css({display:'none'});
                });


		/* 댓글 이모티콘 */
                //$('.emo_view').css({display:'none'});
                $('.detailsrchbtn').click(function(){

                    if(($('.detailsrch_view').css("display")) != "none"){
                        $('.detailsrch_view').css({display:'none'});
                        $('.detailsrchbtn').removeClass("on");
                    }else{
                        $('.detailsrch_view').css({display:'block'});
                        $('.detailsrchbtn').addClass("on");
                    }
                }); 


             	/* 170816 추가 */

		  // $(".dotdot01").dotdotdot({
		  //   ellipsis: '... ',  /*  말줄임표를 뭘로 할지 */
		  //   wrap  : 'word',    /*  자를 단위. 다음 옵션 중 하나 선택: 'word'/'letter'/'children' */
		  //   watch  : true,    /*  윈도우가 리사이즈될 때 업데이트할 건지: true/'window' */
		  //   height  : null
		  // });
		  // $(".dotdot02").dotdotdot({
		  //   ellipsis: '... ',  /*  말줄임표를 뭘로 할지 */
		  //   wrap  : 'word',    /*  자를 단위. 다음 옵션 중 하나 선택: 'word'/'letter'/'children' */
		  //   watch  : true,    /*  윈도우가 리사이즈될 때 업데이트할 건지: true/'window' */
		  //   height  : null
		  // });


		  /* // 170816 추가 */

		


		 
 


});




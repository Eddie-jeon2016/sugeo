$(document).ready(function($) {

/*180906 수정 추가(s)*/
gnbMenu();
function gnbMenu(){
	$('.gnb_bt').on('click', function() {
		$('#totalview').toggleClass('on');
		$('.gnbTotalBack').toggle();
	});

	$('#totalview .allmenu .d1').on('click',function(){
		$(this).toggleClass('active');
		$(this).find('.gnb_depth').slideToggle();
	});
	$('#totalview .allmenu .d1 faq').on('click',function(){
		/*$(this).toggleClass('active');
		$(this).find('.gnb_depth').slideToggle();*/
	});

	var winW = $(window).width();
	var winH = $(window).height();

	$('#totalview').css('min-height',winH);
	$('.gnbTotalBack').css('min-height',winH);
	$( window ).resize(function() {
		if(winW<1025){
			$('#totalview').css('min-height',winH);
			$('.gnbTotalBack').css('min-height',winH);
		}
	});	
	}
	/*180906 수정 추가(e)*/
});


/* 모바일 전체보기 */
$(function(){
	var toggleList='.lst_menu_mbi';
	var toggleListBtn=toggleList+'>li>ul>li>a.btn_menu_open';
	$(toggleListBtn).next().hide();
	//$(lst_menu_mbi+'>li:first-child>.title').addClass('on').next().show();
	$(toggleListBtn).click(function(){
		//$(this).parent().siblings().children().removeClass('on').next().hide();
		//$(this).addClass('on');
		$('.btn_menu_depth1').next().children().siblings().children('a').removeClass('on').next().hide();
		//$(this).parent().siblings().children('a').removeClass('on').next().hide();
		$(this).toggleClass('on');
		$(this).next().slideToggle(200);
	});
});


/* GNB */ 
jQuery(function(){

	$('.gnb>li>a').bind('keyup mouseover',function(){
		if($(window).width()>=768){
			$('.gnb>li>ul').show();
			$('.gnb>li>ul').show();
			$('.bG').show();
			$('.gnb>li').each(function(){
				$(this).removeClass('on');
			});
			$(this).parent().addClass('on');
		}else{
			$('.gnb').css('display','none');
			
		}
	});
		$('.header').bind('mouseleave',function(){
			$(window).resize(function(){
				if($(window).width()>=768){
					$('.gnb>li>ul').hide();
					$('.bG').hide();
					$('.gnb>li>a').each(function(){
						$(this).removeClass('on');
						
					});
				}
			}).resize();
		});
});


/* 헤더 스크롤 그림자 */ 
/* 180913 추가 (S) */ 
jQuery(function(){
var header = $('.hdbtm');

    $(window).scroll(function(e){
        if(header.offset().top !== 28){
            if(!header.hasClass('shadow')){
                header.addClass('shadow');
            }
        }else{
            header.removeClass('shadow');
        }
    });
});
/* //180913 추가 (E) */




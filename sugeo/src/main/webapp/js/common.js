/****** 전체 메뉴 ******/
function openMobileMenu() {
	$('.total_menu').show().toggleClass('on'); 
	$('body').append('<div class="mask" style="display:none"></div>');
	$('.mask').fadeIn(); 
	$('html').css({position:"fixed", width:"100%"});
}
$(function(){
	var wh = $(window).height();
	$('.total_menu').height(wh).css({overflowY:"auto", height:"100%"});
	
	// 닫기버튼
	$('.total_menu .txt button').click(function(){ 
		$(this).parent().parent().removeClass('on'); 
		$('.mask').remove(); 
		$('html').css({position:"relative"});
	});
});
 
 
/****** top Button ******/

$(function() { 
        $(".top_btn").click(function() {
            $('html, body').animate({
                scrollTop : 0
            }, 400);
            return false;
        });
    });
  

/****** faq / drop ******/
$(function () {
		$('div.faq dl dd').css('display', 'none');
		$('div.faq dl dt').click(function () {
			$(this).find('.search_btn').toggleClass('selected');
			$('div.faq dl dd').slideUp('fast');
			if ($(this).find('.search_btn').hasClass('selected')) {
				$('+dd', this).slideDown('fast');
				$('div.faq dl dt .search_btn').removeClass('selected');
				$(this).find('.search_btn').addClass('selected');
			} else {
				$('+dd', this).slideUp('fast');
			}
		});

	});
	
/****** 탭 ******/
$(document).ready(function(){
	
	$('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})
})

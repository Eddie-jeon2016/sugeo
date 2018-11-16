// JavaScript Document

$( document ).ready(function() {

/*이용안내 faq (s)*/
faqEvt();
function faqEvt(){
$('.faq dl dt').click(function(){
   $(this).find('.search_btn').toggleClass('on');
   $('.faq dl dd').slideUp('1000');
   if ($(this).find('.search_btn').hasClass('on')){
      $('+dd',this).slideDown('1000');
      $('.faq dl dt .search_btn').removeClass('on');
      $(this).find('.search_btn').addClass('on');
   }else{
      $('+dd',this).slideUp('10000');
   }
});
$('.faq_tab li').click(function(){
	$('.faq_tab li').removeClass('on')
	$(this).addClass('on');
});
}
/*이용안내 faq (e)*/



});//document.ready (e)
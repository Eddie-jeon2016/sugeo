$(document).ready(function(){
	/* GNB */
	$("nav").hover(function(){
		$(".gnb_depth").css("display","block");
		$(".nav_closd").css("display","block");
	},  function(){
		$(".gnb_depth").css("display","none");
		$(".nav_closd").css("display","none");
	});

	$('.nav_closd').bind('click blur',function(){
		$('.gnb_depth').fadeOut('fast');
		$(".nav_closd").css("display","none");
		return false;
	});

	//Mng left 아코디언 효과
	var accordion = {
        init: function() {
            this.accordionMenu();
        },
        accordionMenu: function() {
            var $tgWrap = $(".gnbtab"),
				$tgAnchor = $tgWrap.find('.gnbbt'),
                $tgContent = $tgWrap.find(".twodep"),
                cntClass = "current";
           	// $tgAnchor.first().addClass(cntClass).find('span').text('-');
            // $tgContent.first().show();
           	$tgAnchor.each(function() {
            	$(this).on('click', function(e) {
                	e.preventDefault();
                    $tgAnchor.removeClass(cntClass).find('span').text('+')
                        .end()
                    $tgContent.slideUp();
                    if ( $(this).next($tgContent).is(':hidden') ) {
                        $(this).next($tgContent).slideDown();
                    }
                });
            });
        }
	}; accordion.init();

	/* 서브 path */
	$(".pathSub a").mouseover(function(){
		$(".pathS").show();
		$(".pathS ul").show();
	});
	$(".pathS ul").mouseover(function(){
		$(".pathS ul").show();
	});

	$(".pathSub").mouseleave(function(){
		jQuery(".pathS").hide();
		$(".pathS ul").hide();
	});
	$(".pathSub").mouseleave(function(){
		$(".pathS ul").hide();
	});


	$(".pathSub02 a").mouseover(function(){
	$(".pathS02").show();
	$(".pathS02 ul").show();
	});
	$(".pathS02 ul").mouseover(function(){
	$(".pathS02 ul").show();
	});

	$(".pathSub02").mouseleave(function(){
	$(".pathS02").hide();
	$(".pathS02 ul").hide();
	});
	$(".pathSub02").mouseleave(function(){
	$(".pathS02 ul").hide();
	});


	/* 메인 제작영화 */
	var currentJ = 0;
    $(".mbnod1 > li").click(function(){
        currentJ = $(this).index();
        imageSetJ();
        wrapboxSetJ();
    });
    $(".wrapBox1 > ul > li").first().css("display","block");
    function imageSetJ(){
        $(".wrapBox1 > ul > li").each(function(i){

            if( i == currentJ){
            	$(".wrapBox1 > ul > li").css("display","none");
                $(this).css("display","block");


            }else{
                $(this).css("display","none");

            };
        });
    };
    function wrapboxSetJ(){
        $(".mbnod1 > li").each(function(i){
			if(i == currentJ){
				$(".mbnod1 > li").removeClass("on");
				$(this).addClass("on");
			}else{

				$(this).removeClass("on");
			};
		});
    };



    /* 메인 수상현황 */
	var currentS = 0;
    $(".mbnod2 > li").click(function(){
        currentS = $(this).index();
        imageSetS();
        wrapboxSetS();
    });
    $(".wrapBox2 > ul > li").first().css("display","block");
    function imageSetS(){
        $(".wrapBox2 > ul > li").each(function(i){

            if( i == currentS){
            	$(".wrapBox2 > ul > li").css("display","none");
                $(this).css("display","block");


            }else{
                $(this).css("display","none");

            };
        });
    };
    function wrapboxSetS(){
        $(".mbnod2 > li").each(function(i){
			if(i == currentS){
				$(".mbnod2 > li").removeClass("on");
				$(this).addClass("on");
			}else{

				$(this).removeClass("on");
			};
		});
    };















});









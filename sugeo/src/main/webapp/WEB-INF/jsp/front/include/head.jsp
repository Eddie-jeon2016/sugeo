<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> 
  <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi"/>
  <meta property="og:title" content="2018 이통사와 함께하는 개인정보보호캠페인"/>
  <meta property="og:description" content="2018 이통사와 함께하는 개인정보보호캠페인"/>
   <!-- 180913 추가 (S) -->
  <!-- <script type="text/javascript" language="javascript" src="/js/jquery.bpopup.min.js"></script> -->
  <!-- //180913 추가 (E) -->
  <!-- <script type="text/javascript" language="javascript" src="/js/migration-1.4.1.min.js"></script> -->
  <script type="text/javascript" language="javascript" src="/js/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" language="javascript" src="/js/jquery.dotdotdot.min.js"></script>    
  <script type="text/javascript" language="javascript" src="/js/common.js"></script>
  <script type="text/javascript" language="javascript" src="/js/slick.min.js"></script>
  <link href="/css/common.css" rel="stylesheet" type="text/css"/>
  <link href="/css/main.css" rel="stylesheet" type="text/css"/> 
  <link href="/css/slick.css" rel="stylesheet" type="text/css"/> 
  <!-- <link rel="shortcut icon" type="image/x-icon" href="/images/common/favicon.ico" /> -->
  <link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css">
  <link rel="icon" href="/images/common/favicon/cropped-safety-favicon-32x32.png" sizes="32x32" />
  <link rel="icon" href="/images/common/favicon/cropped-safety-favicon-192x192.png" sizes="192x192" />
  <link rel="apple-touch-icon-precomposed" href="/images/common/favicon/cropped-safety-favicon-180x180.png" />
  <meta name="msapplication-TileImage" content="/images/common/favicon/cropped-safety-favicon-270x270.png" />
    
  <title>2018 이통사와 함께하는 개인정보보호캠페인</title>
	<!-- 170313 파비콘 추가 (s) -->
<!--    	<link rel="shortcut icon" type="image/x-icon" href="/images/kafafvc.ico" /> -->
    <!-- 170313 파비콘 추가 (e) -->
    <!--[if lt IE 9]>
        <script type="text/javascript" src="/js/tmp/html5shiv.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
  //url 복사
    function urlCopy(url) {
    	var IE=(document.all)?true:false;
        if (IE) {
            if(confirm("이 글의 트랙백 주소를 클립보드에 복사하시겠습니까?"))
                window.clipboardData.setData("Text", url);
        } else {
            temp = prompt("이 글의 트랙백 주소입니다. Ctrl+C를 눌러 클립보드로 복사하세요", url);
        }
    }
    function TopSearch(){
    	document.topSearchForm.action ="/front/SearchMain.do";
    	document.topSearchForm.submit();
    }
    function snsTo(sTarget, sUri, sDsc) {
    	var o;

        var _url = "";
//         var _url = encodeURI(encodeURIComponent('http://sbtv.kr/usr/bbs/BbsMain.do?cmd=view&selectId=326&smenuNo=2040100'));
        var _txt = encodeURI(sDsc);
        var _br  = encodeURIComponent('\r\n');
        var pWidth = 640;
        var pHeight = 380;
        var pLeft = (screen.width - pWidth) / 2;
        var pTop = (screen.height - pHeight) / 2;
        switch(sTarget)
        {
            case 'facebook':
		        _url = encodeURIComponent(sUri);
                o = {
                    method:'popup',
                    url:'https://www.facebook.com/sharer/sharer.php?u=' + _url
                };
                break;
            case 'blog':
            	_url = encodeURI(encodeURIComponent(sUri));
            	o = {
            		method:'popup',
            		url:'http://share.naver.com/web/shareView.nhn?url=' + _url + '&title=' + _txt
            	};
            	break;
            case 'twitter':
            	_url = encodeURIComponent(sUri);
                o = {
                    method:'popup',
                    url:'http://twitter.com/intent/tweet?text=' + _txt + '&url=' + _url
                };
                break;
            case 'google':
            	_url = encodeURIComponent(sUri);
                o = {
                    method:'popup',
                    url:'//plus.google.com/share?url=' + _url
                };
                break;

            default:
                alert('지원하지 않는 SNS입니다.');
                return false;
        }

        switch(o.method)
        {
            case 'popup':
                //window.open(o.url);
                window.open(o.url,"","width="+ pWidth +",height="+ pHeight +",left="+ pLeft +",top="+ pTop +",location=no,menubar=no,status=no,scrollbars=no,resizable=no,titlebar=no,toolbar=no");
                break;

            case 'web2app':
                if(navigator.userAgent.match(/android/i))
                {
                    // Android
                    setTimeout(function(){ location.href = 'intent://' + o.param + '#Intent;' + o.g_proto + ';end'}, 100);
                }
                else if(navigator.userAgent.match(/(iphone)|(ipod)|(ipad)/i))
                {
                    // Apple
                    setTimeout(function(){ location.href = o.a_store; }, 200);
                    setTimeout(function(){ location.href = o.a_proto + o.param }, 100);
                }
                else
                {
                    alert('이 기능은 모바일에서만 사용할 수 있습니다.');
                }
                break;
        }
    }

    </script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-99294417-1', 'auto');
  ga('send', 'pageview');

</script>
<!-- AceCounter Log Gathering Script V.7.5.2017020801 -->
<script language='javascript'>
	var _AceGID=(function(){var Inf=['gtt4.acecounter.com','8080','AB6A31560820883','AW','0','NaPm,Ncisy','ALL','0']; var _CI=(!_AceGID)?[]:_AceGID.val;var _N=0;var _T=new Image(0,0);if(_CI.join('.').indexOf(Inf[3])<0){ _T.src =( location.protocol=="https:"?"https://"+Inf[0]:"http://"+Inf[0]+":"+Inf[1]) +'/?cookie'; _CI.push(Inf);  _N=_CI.length; } return {o: _N,val:_CI}; })();
	var _AceCounter=(function(){var G=_AceGID;var _sc=document.createElement('script');var _sm=document.getElementsByTagName('script')[0];if(G.o!=0){var _A=G.val[G.o-1];var _G=(_A[0]).substr(0,_A[0].indexOf('.'));var _C=(_A[7]!='0')?(_A[2]):_A[3];var _U=(_A[5]).replace(/\,/g,'_');_sc.src=(location.protocol.indexOf('http')==0?location.protocol:'http:')+'//cr.acecounter.com/Web/AceCounter_'+_C+'.js?gc='+_A[2]+'&py='+_A[4]+'&gd='+_G+'&gp='+_A[1]+'&up='+_U+'&rd='+(new Date().getTime());_sm.parentNode.insertBefore(_sc,_sm);return _sc.src;}})();
</script>
<noscript><img src='http://gtt4.acecounter.com:8080/?uid=AB6A31560820883&je=n&' border='0' width='0' height='0' alt=''></noscript>
<!-- AceCounter Log Gathering Script End -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/login.css">
    <script src="<%=request.getContextPath() %>/jquery/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	$('#loginbtn').click(function(){
    		//로그인을 요청했을때 아이디는 개발자의도에 맞게끔
    		var inValurel = document.getElementById("userid").value;
    		var inValurel = document.getElementById("userpw").value;
    		
    		if(inValuel.length <= 0){
    			// 사용자가 아이디를 입력하지 않은 상태, css로 상태 변화
    			$("#userid").css("boder-color", "red");
    			alert("아이디를 입력해주세요.");
    			return;
    			//이후 로그인이 안되고 빠져나오게 return
    		}
    		else{
    			//사용자가 아이디 길이를 0을 초과해서 문제없음
    			$("#userid").css("boder-color","initial");
    		}
    		
    		if(inValuel.length <= 0){
    			$("#userpw").css("boder-color", "red");
    			alert("패스워드를 입력해주세요.");
    			return;
    			//이후 로그인이 안되고 빠져나오게 return
    		}
    		else{
    			//사용자가 패스워드 길이를 0을 초과해서 문제없음
    			$("#userpw").css("boder-color","initial");
    		}
    		
    		if(inValue1.length >= 17){
    			inValue1 = inValue1.substr(0, 15);
    			document.getElementById("userid").value = inValue1;
    		}
    		
    		if(inValue2.length >= 20){
    			inValue2 = inValue2.substr(0, 18);
    			document.getElementById("userpw").value = inValue2;
    		}
    		
    		$('form#loginForm').submit();
    	});
    });
    </script>
    
</head>
<body onload="loadinit()">
    <div class="login">
        <div class="login_left">
            <img src="<%=request.getContextPath() %>/images/lefimg.jpg" class="img1">
        </div>
        <div class="login_right">
            <form name="loginForm" id="loginForm" class="loginForm" action="loginsubmit.do" method="post">  
            <div><span style="color:green;">AAA 회사</span></div>
            <div style="height: 20px;"></div>
            <div>아이디</div><input type="text" id="userid" name="userid" placeholder="아이디" autocomplete="off"/>
            <div>비밀번호</div><input type="password" id="userpw" name="userpw" placeholder="비밀번호" autocomplete="off"/>
            <div class="captchamargin1"></div>
	        <img src="/Egov_WEB/captcha.do" class="captchaimg" alt="캡차 이미지" title="클릭시 새로고침"/>
	        <input type="text" id="captcha" class="captchatxt" name="captcha" placeholder="위에있는 글자를 입력해주세요" autocomplete="off"/>
            <div class="captchamargin2"></div>
            <input type="button" value="로그인" id="loginbtn">
            <div style="height: 20px;"></div>
            <div style="font-size:11pt;"><a href="" class="login_right_a">아이디찾기</a>/<a href="" class="login_right_a">비밀번호찾기</a>&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/usercondition.do" class="login_right_a">회원가입</a></div>
        	</form>
        </div>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./common/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/userenter.css">
    <script src="<%=request.getContextPath() %>/jquery/jquery-1.12.4.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/components/util_ajax.js"></script>
    <script src="<%=request.getContextPath() %>/js/components/util_validation.js"></script>
    <script type="text/javascript">
	var check1 = false;
	var check2 = false;
	var check3 = false;
	var check4 = false;

	//아이디체크
    function checkid()
    {
		if(validNoSpecial("userid"))
        {
            $("#userid").css("border-color","red");
            $("#warningmessage").text("아이디는 영어, 숫자만 입력해주세요.");
            check1 = false;
            return;
        }
        else if(validLength(6,12,"userid"))
        {
            $("#userid").css("border-color","red");
            $("#warningmessage").text("아이디는 영어, 숫자 6~12자리로 입력해주세요.");
            check1 = false;
            return;
        }
        else if(removeTag("userid"))
    	{
            $("#userid").css("border-color","red");
            $("#warningmessage").text("아이디에 문자 < 또는 >는 입력하실 수 없습니다.");
            check1 = false;
            return;
    	}
        else
        {
            $("#warningmessage").text("");
            $("#userid").css("border-color","initial");
        }

    	var in_id = $("#userid").val();
    	ajaxCall("<%=request.getContextPath() %>/checkid.do?userid="+in_id,checkid_success);
    }

	//아이디체크 결과
    function checkid_success(data)
    {
        var jsondata = data["state"];
        if(jsondata=="1"){
            $("#userid").css("border-color","red");
            $("#warningmessage").text("아이디가 이미 사용중입니다.");
            check1 = false;
        }else if(jsondata=="-1"){
            $("#userid").css("border-color","red");
            $("#warningmessage").text("네트워크 문제가 발생했습니다. 새로고침을 해주세요.");
            check1 = false;
        }
        else{
            $("#warningmessage").text("");
            $("#userid").css("border-color","initial");
            check1 = true;
        }
    }
	
	//패스워드체크
	function checkpw()
    {
    	var passwordOK = validPassword("password1","password2",10,19);

   	    if(passwordOK==-1)
   	    {
   	 		check2 = false;
            $("#password1").css("border-color","red");
            $("#warningmessage").text("비밀번호를 입력해주세요.");
   	 		return;
   	    }
   	    else if(passwordOK==-2)
   	    {
   	 		check2 = false;
            $("#password1").css("border-color","red");
            $("#warningmessage").text("비밀번호를 10~19자까지 입력해주세요.");
   	 		return;
   	    }
   	    else if(passwordOK==-3)
   	    {
   	 		check2 = false;
            $("#password1").css("border-color","red");
            $("#warningmessage").text("비밀번호에 공백문자는 입력하실 수 없습니다.");
   	 		return;
   	    }
   	    else if(passwordOK==-4)
   	    {
   	 		check2 = false;
            $("#password1").css("border-color","red");
            $("#warningmessage").text("비밀번호에 &문자는 입력하실 수 없습니다.");
   	 		return;
   	    }
   	    else if(passwordOK==-5)
   	    {
   	 		check2 = false;
            $("#password1").css("border-color","red");
            $("#warningmessage").text("비밀번호에 숫자,영어,특수기호만 사용가능합니다.");
   	 		return;
   	    }
   	    else if(passwordOK==-6)
   	    {
   	 		check2 = false;
            $("#password1").css("border-color","red");
            $("#warningmessage").text("비밀번호는 특수문자,숫자,영문 각 1번이상 사용해야합니다.");
   	 		return;
   	    }
   	    else if(removeTag("password1"))
   		{
   	 		check2 = false;
            $("#password1").css("border-color","red");
            alert("비밀번호에 < 또는 >는 입력하실 수 없습니다.");
   	 		return;
   		}
   	    else
   	    {
            $("#warningmessage").text("");
	        $("#password1").css("border-color","initial");
   	 		check2 = true;
   	    }
    }
	
	//패스워드 재입력
 	function checkpw2()
    {
    	var passwordOK = validPassword("password1","password2",10,19);

   	    if(passwordOK==-8)
   	    {
   	 		check3 = false;
            $("#password2").css("border-color","red");
            $("#warningmessage").text("비밀번호를 한번 더 입력해주세요.");
   	 		return;
   	    }
   	    else if(passwordOK==-9)
   	    {
   	 		check3 = false;
            $("#password2").css("border-color","red");
            $("#warningmessage").text("비밀번호가 일치하지 않습니다.");
   	 		return;
   	    }
   	    else
   	    {
            $("#warningmessage").text("");
	        $("#password2").css("border-color","initial");
   	 		check3 = true;
   	 		return;
   	    }
    }
 	
	//닉네임검증
    function checknm()
    {
    	
   	   if(validNoSpecial("nickname"))
   	   {
   	 		check4 = false;
            $("#nickname").css("border-color","red");
            $("#warningmessage").text("닉네임은 한글,영어,숫자만 입력 가능합니다.");
   	 		return;
   	   }
       else if(validLength(2,10,"nickname"))
       {
  	 		check4 = false;
            $("#nickname").css("border-color","red");
            $("#warningmessage").text("닉네임은 한글,영어,숫자 2~10자리 안으로 입력해주세요.");
   	 		return;
       }
   	   else if(removeTag("nickname"))
   	   {
   	 		check4 = false;
            $("#nickname").css("border-color","red");
            $("#warningmessage").text("닉네임에 < 또는 >는 입력하실 수 없습니다.");
   	 		return;
   	   }
   	   else
   	   {
            $("#warningmessage").text("");
	        $("#nickname").css("border-color","initial");
   	   }

	   var in_nm = $("#nickname").val();
	   ajaxCall("<%=request.getContextPath() %>/checknm.do?nickname="+in_nm,checknm_success);
    }
	
	//닉네임 서버단 검증결과
	

	//아이디체크 결과
    function checknm_success(data)
    {
        var jsondata = data["state"];
        if(jsondata=="1"){
            $("#nickname").css("border-color","red");
            $("#warningmessage").text("닉네 이미 사용중입니다.");
            check4 = false;
        }else if(jsondata=="-2"){
            $("#nickname").css("border-color","red");
            $("#warningmessage").text("닉네임에 비속어는 입력하실 수 없습니다.");
            check4 = false;
        }else if(jsondata=="-1"){
            $("#nickname").css("border-color","red");
            $("#warningmessage").text("네트워크 문제가 발생했습니다. 새로고침을 해주세요.");
            check4 = false;
        }
        else{
            $("#warningmessage").text("");
            $("#nickname").css("border-color","initial");
            check4 = true;
        }
    }
	
    $(function(){
    	//ST 버튼1클릭
        $('#button1').click( function() 
        {

          	if(check1==true&&check2==true&&check3==true&&check4==true)
        	{
               $(".myloading").css("display", "block");
      		   $('form#joinForm').submit();
        	}
          	else
        	{
          		if(check1==false)
        		{
                    $("#userid").css("border-color","red");
                    $("#warningmessage").text("아이디를 다시 확인해주세요.");
        		}
          		if(check2==false)
        		{
                    $("#password1").css("border-color","red");
                    var message = $("#warningmessage").text();
                    if(message=="")
                   	{
                        $("#warningmessage").text("비밀번호를 다시 확인해주세요.");
                   	}
        		}
          		if(check3==false)
        		{
                    $("#password2").css("border-color","red");
                    var message = $("#warningmessage").text();
                    if(message=="")
                   	{
                        $("#warningmessage").text("비밀번호를 다시 확인해주세요.");
                   	}
        		}
          		if(check4==false)
        		{
                    $("#nickname").css("border-color","red");
                    var message = $("#warningmessage").text();
                    if(message=="")
                   	{
                        $("#warningmessage").text("닉네임을 다시 확인해주세요.");
                   	}
        		}
          		
        	} 
		});
    	//ED 버튼1클릭
        
	});
    </script>
</head>
<body>
    <div class="login">
        <div class="login_left">
            <img src="<%=request.getContextPath() %>/images/lefimg.jpg" class="img1">
        </div>
        <div class="login_right">
            <div><span style="color:green;">AAA 회사</span> 회원가입</div>
            <div style="height: 20px;"></div>
        
            <form name="joinForm" id="joinForm" method="post" action="userenter2.do">
            <div>아이디</div><input type="text" name="userid" id="userid" onfocusout="checkid()"/>
            <div>비밀번호</div><input type="password" name="password1" id="password1" onfocusout="checkpw()" placeholder="비밀번호 (10자 ~ 19자)" autocomplete="off"/>
            <div>비밀번호 확인</div><input type="password" name="password2" id="password2" onfocusout="checkpw2()" placeholder="비밀번호 재입력" autocomplete="off"/>
            <div>닉네임</div><input type="text" name="nickname" id="nickname" onfocusout="checknm()" placeholder="닉네임 (2자 ~ 10자)"/>
            <div class="warningmessage" id="warningmessage"></div>
            <div><input type="button" value="회원가입" id="button1"></div>
            </form>
        </div>
    </div>
</body>
</html>
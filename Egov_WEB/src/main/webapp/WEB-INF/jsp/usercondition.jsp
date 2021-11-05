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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/usercondition.css">
    <script src="<%=request.getContextPath() %>/jquery/jquery-1.12.4.min.js"></script>
    <script language="JavaScript">

    $(function(){
        
        $('#check1').click( function() 
        {
            if($("input#check1").prop("checked")){
                if(!$("input#check2").prop("checked")){
                    $('input#check2').prop('checked', true);
                }
                if(!$("input#check3").prop("checked")){
                    $('input#check3').prop('checked', true);
                }
            }
            else
            {
                if($("input#check2").prop("checked")){
                    $('input#check2').prop('checked', false);
                }
                if($("input#check3").prop("checked")){
                    $('input#check3').prop('checked', false);
                }
            }
        });

        $('#continue').click( function() 
        {
            if(!$("input#check2").prop("checked")){
                alert('서비스 이용약관에 동의해 주세요');
                return false;
            }
            else if(!$("input#check3").prop("checked")){
                alert('개인정보처리리에 동의해 주세요');
                return false;
            }

            $('form#joinForm').submit();
        });
    });
    </script>
</head>
<body>
    <div class="login">
        <div class="login_left">
            <img src="<%=request.getContextPath() %>/images/lefimg.jpg" class="img1">
        </div>
        <div class="login_right">
            <form name="joinForm" id="joinForm" action="userenter.do" method="post">
            <div><span style="color:green;">AAA 회사</span> 회원가입</div>
            <input type="checkbox" class="check1" id="check1"/>
            <div class="info1">모두동의</div>
            <input type="checkbox" class="check2" id="check2"/>
            <div class="info2">AAA회사 이용약관 동의</div>
            <textarea class="textarea1"></textarea>
            <input type="checkbox" class="check3" id="check3"/>
            <div class="info3">AAA회사 개인정보처리 동의</div>
            <textarea class="textarea2"></textarea>
            <input type="button" value="회원가입 계속" class="button" id="continue">
            </form>
        </div>
    </div>
</body>
</html>
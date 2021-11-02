<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">

	var count = 0;
	
	$(function(){	
		$('.myButton').click(function()
		{
		
			var formselect = $("#myForm");
			var formdata = formselect.serialize();
			
			//웹페이지 새로고침이 필요없는 요청
			 $.ajax({
			    url : "<%=request.getContextPath() %>/testXmlView.do",
			    type: "POST",
				data: formdata,
			    async: false,
			    timeout: 10000,
			        success : function(xml){
			            var animal1 = $(xml).find("MYAnimal").find('animal1').text();
			            var animal2 = $(xml).find("MYAnimal").find('animal2').text();
			            var animal3 = $(xml).find("MYAnimal").find('animal3').text();
			            
			            $(".myList").append("<div>"+animal1+","+animal2+","
			            +animal3+"</div>");
			
			        },
			error: function() {
			alert("실패");
			}
		            
		        });
		});
	
	});
</script>
</head>
<body>
<form name="myForm" id="myForm">
내용:<input type="text" name="mytext">
<br/>
<input type="button" class="myButton" value="추가">
</form>
<div class="myList"></div>
</body>
</html>
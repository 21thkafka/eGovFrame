/*Util*/

function ajaxCall(url, doFunction) {
    //var abcd = $('#callid').text();
    //, url : '<%=request.getContextPath() %>/main/main.do?date='+abcd
    //----------------------------------------------------------------------------------
    //데이터 받기
    $.ajax({
      type: "POST",
      url: url,
      dataType: "json", //전송받을 데이터 타입
      contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	  timeout: 6000,
      error: function(request, status, error) {
        //에러처리 코드
        alert("오류가 발생하였습니다."+"\n"+"새로고침 후 다시시도해주세요."+"\n");
      },
      success: function(data) {
        //alert("성공");
        doFunction(data);
      }
    });
    //----------------------------------------------------------------------------------
}

//ajax 폼전송.(jquery사용)
function ajaxCallForm(url,doFunction,formName) {
	$(function() {
	var frmname = formName+"";
	var frm = $("#"+frmname);
	var frm_data = frm.serialize();
    	//----------------------------------------------------------------------------------
		//데이터 받기
		$.ajax({
			type: "POST",
			url: url,
			data: frm_data,	  //전송할 데이터.
			dataType: "json", //전송받을 데이터 타입
		    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    timeout: 6000,
			error: function(request, status, error) {
			//에러처리 코드
		    alert("오류가 발생하였습니다."+"\n"+"새로고침 후 다시시도해주세요."+"\n");
			},
			success: function(data) {
			doFunction(data);
			}
		});
	});
//----------------------------------------------------------------------------------
}

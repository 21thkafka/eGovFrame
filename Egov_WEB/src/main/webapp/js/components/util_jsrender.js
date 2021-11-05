/*기본적으로 rendering(ui)과 js exec가 함께 이루어지지 않는 특성이 있다.
   ui갱신후 js가 실행되도록 돕는 Util*/
/*로딩 프로세스 표시-서버의 동작시간이 많이필요한 작업 실행시*/
//필요한준비물:<link rel="stylesheet" href="<%=request.getContextPath() %>/css/components/loadingImage.css">
//로딩사진준비물:loading.gif

//로딩 프로세스 시작(사용방법:longProcess("email()","이메일전송중입니다.");)
function longProcess(doFunction,msg)
{
	document.getElementById("loading").style.visibility = "visible";
    alert(msg+"잠시만기다려주세요.");
    setTimeout(doFunction, 30);
}

//로딩 프로세스 종료
function exitProcess()
{
    setTimeout('loadingclose()', 30);
}

function loadingclose()
{
	document.getElementById("loading").style.visibility = "hidden";	
}
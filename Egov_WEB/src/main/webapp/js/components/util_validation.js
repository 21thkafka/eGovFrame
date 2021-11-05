/* 일반모듈*/
//포커스사용 커서이동
function doFocusing(inID)
{
    document.getElementById(""+inID).focus();
}

//빨간글씨 보여주기showError("#warning1",".message1","아이디에 동일한 문자를 5번연속 입력하실 수 없습니다.");
function showError(inID,inID2,warnText){
    $(''+inID).css('color','red');
    $(''+inID).children(''+inID2).css('color','red');  
    $(''+inID).children(''+inID2).text(''+warnText);
}

//빨간끌시 삭제showClear("#warning1",".message1","");
function showClear(inID,inID2,clearText){
    $(''+inID).css('color','#333');
    $(''+inID).children(''+inID2).css('color','rgb(150, 150, 150)');  
    $(''+inID).children(''+inID2).text(''+clearText);
}

//태그제거
function removeTag(inID)
{
	var inValue=document.getElementById(""+inID).value;	
    if(inValue.indexOf("<")!=-1)
    {
    	return 1;
        //alert('경고');
    }
    else if(inValue.indexOf(">")!=-1)
    {
    	return 1;
        //alert('경고');
    }
    return 0;
}

//공백 검사
function isEmpty(inID)
{
    var inValue=document.getElementById(""+inID).value;
    //앞뒤 공백체크
    inValue = inValue.replace(/(^[\s]*)|([\s]*$)/g, "");
    if (inValue.length < 1) {
        
        //myalert("ㅁ값을 입력해주세요.");
        return 1;
    }
    return 0;
}


//입력길이 검사
function validLength(min,max,inID)
{
    var inValue=document.getElementById(""+inID).value;
    //앞뒤 공백체크
    inValue = inValue.replace(/(^[\s]*)|([\s]*$)/g, "");

    if( inValue.length < min || inValue.length > max)
	{
        //alert("ㅁ를 4~12자까지 입력해주세요.");
        return 1;
    }
    return 0;
}

//갯수제한
//최소,최대,숫자만 입력가능
function validNumber(min,max,inID)
{
    var inValue=document.getElementById(""+inID).value;
    
    if( inValue.length < min || inValue.length > max)
    {
        //alert("ㅁ를 4~12자까지 입력해주세요.");
        return -1;
    }

    var table = "0123456789";
    var char;
	for(var i=0; i<inValue.length; i++)
	{
	    char = inValue.substr(i, 1);
	    if(table.indexOf(char) == -1)
	    {
            //alert("숫자만 입력 가능합니다");
 	    	return -2;
	    }
    }
    
	return 0;
}

//한글만 허용
function validOnlyKor(inID){
  var inValue1=document.getElementById(""+inID).value;
  
	  var table = new RegExp(/^[가-힣]+$/);
	  if(!table.test(inValue1))
	  {
		  //alert("한글만을 입력하세요");
	      return 1;
	  }
	  return 0;
} 

//한글,영어 검사(한글,영문만 허용)
function validKorEng(inID){
    var inValue1=document.getElementById(""+inID).value;
    
	  var table = new RegExp(/^[가-힣|a-z|A-Z]+$/);
	  if(!table.test(inValue1))
	  {
		  //alert("영문자와 한글만을 입력하세요");
	      return 1;
    }
	  return 0;
} 

//숫자만 허용
function isNumber(inID)
{
    var inValue=document.getElementById(""+inID).value;
	var table = "0123456789";
	var char;
	for(var i=0; i<inValue.length; i++)
	{
	    char = inValue.substr(i, 1);
	    if(table.indexOf(char) == -1)
	    {
            //alert("숫자만 입력 가능합니다");
 	    	return 1;
	    }
	}
	return 0;
}

//영문,숫자만 허용
function validEngNum(inID)
{
    var inValue=document.getElementById(""+inID).value;
    var table = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    var char;
    for(var i=0; i<inValue.length; i++)
    {
        char = inValue.substr(i, 1);
        if(table.indexOf(char) == -1)
        {
            //alert("영어,숫자만 입력 가능합니다");
            return 1;
        }
    }
    return 0;
}

//특수문자 검사(한글,영문,숫자만 허용)
function validNoSpecial(inID){
      var inValue1=document.getElementById(""+inID).value;
      
	  var table = new RegExp(/^[가-힣|a-z|A-Z|0-9]+$/);
	  if(!table.test(inValue1))
	  {
		  //alert("영문자와 한글,숫자만을 입력하세요");
	      return 1;
      }
	  return 0;
} 

//동일문자5개체크
function validContinueChar(inID){
    var inValue1=document.getElementById(""+inID).value;
	var testchar = new RegExp(/(\w)\1\1\1\1/);
	if( testchar.test(inValue1) ){
		return 1;
	}
	return 0;
}


/* 회원가입 모듈*/
// 패스워드 체크
//비밀번호,비밀번호2번쨰,문자열최소길이,최대길이
function validPassword(inID,inID2,min,max)
{
    var inValue1=document.getElementById(""+inID).value;
    var inValue2=document.getElementById(""+inID2).value;

	if(inValue1.length <= 0 ) {
        //alert(비밀번호를 입력해주세요.)
		return -1;
    }
    //최소최대길이
	if( validLength(min,max, inID)){
        //alert(비밀번호를 4~12자까지 입력해주세요.)
        return -2;
    }
    //공백체크
	if( inValue1.indexOf(" ") != -1){
        //alert(공백문자는 입력하실 수 없습니다.)
		return -3;
    }
    //&문자열체크
	if(inValue1.indexOf("&") != -1 ){
        //alert(&문자는 입력하실 수 없습니다.)
		return -4;
	}

    //특수문자 숫자 영어 조합
	var combineChar = new RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]+$/);
	if( !combineChar.test(inValue1) ){
        return -6;
	}
	
    //재확인 비밀번호 미입력.
	if( inValue1.length > 0 && inValue2.length <= 0 ){
        //alert(비밀번호를 한번 더 입력해주세요.)
        return -8;
    }
    
    //비밀번호 일치x
	if( inValue1 != inValue2 ) {
		return -9;
    }
    

	return 0;
}

//이메일양식 검사
function validEmail(inID)
{
    var inValue=document.getElementById(""+inID).value;
    var table = new RegExp(/^[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+@[-!#$%&'*+/0-9=?A-Z^_a-z{|}~]+.[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+$/);
    if(!table.test(inValue))
    {
        return 1;
    }
    return 0;
}

//휴대폰번호 검사(-없이)
//var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
function validPhone(inID){
    var inValue=document.getElementById(""+inID).value;
    
    var table = new RegExp(/^\d{3}\d{3,4}\d{3,4}$/);
    if(!table.test(inValue))
    {
        return 1;
    }
    return 0;
}

//미사용->서버단에서 체크로 변경.
//닉네임체크
function validName(inID){
    var inValue=document.getElementById(""+inID).value;
    
	var table = new RegExp(/^[씨발\w+]+|[씨발\w+]+|[개새\w+]+$/);
    var validNickname = inValue.replace(table,"");
    if(validNickname) {
        return 0;
    }

    return 1;
}
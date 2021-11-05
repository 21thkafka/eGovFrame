/*Util*/
/*입력폼 용 */

//form전송 버튼클릭시
function formSubmit(inID, url)
{
	document.getElementById(""+inID).action = url;
    document.getElementById(""+inID).submit();
}

//Ctrl키 제한
function KeyBlock() {
    var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();
    if (event.ctrlKey) {
        event.returnValue = false;
    }
}

//파라미터 분리
function getParameterByName(name) {
  name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
  var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
      results = regex.exec(location.search);
  return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
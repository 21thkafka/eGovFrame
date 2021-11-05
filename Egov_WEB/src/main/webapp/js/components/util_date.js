/*Util*/
/*날짜시간 함수*/

//오늘날짜 1초단위로갱신
function workDate(inID) {
    $('#'+inID).text('' + getDateStamp());
      setTimeout("workDate()", 1000);
}

//현재시간 1초단위로갱신
function realtimeClock(inID) {
    $('#'+inID).text('' + getTimeStamp());
    setTimeout("realtimeClock()", 1000);
}


/*참조함수*/
function getDateStamp() { // 24시간제
    var d = new Date();
  
    var s =
        leadingZeros(d.getFullYear(), 4) + '-' +
        leadingZeros(d.getMonth() + 1, 2) + '-' +
        leadingZeros(d.getDate(), 2);
    return s;
}

function getTimeStamp() { // 24시간제
    var d = new Date();
  
    var s =
        leadingZeros(d.getFullYear(), 4) + '-' +
        leadingZeros(d.getMonth() + 1, 2) + '-' +
        leadingZeros(d.getDate(), 2) + ' ' +
  
        leadingZeros(d.getHours(), 2) + ':' +
        leadingZeros(d.getMinutes(), 2) + ':' +
        leadingZeros(d.getSeconds(), 2);
  
    return s;
}

function leadingZeros(n, digits) {
    var zero = '';
    n = n.toString();
  
    if (n.length < digits) {
        for (i = 0; i < digits - n.length; i++)
            zero += '0';
    }
    return zero + n;
}
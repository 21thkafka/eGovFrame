<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--jstl 태그라이브러리 추가.-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 정부 라이브러리 추가 -->
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function movePage(pageNo){
		location.href="<%=request.getContextPath()%>/borderList.do?pageNo=" + pageNo;
	}
</script>
<style>
	table {
		  border-collapse: collapse;
		margin:100px auto;
		width: 800px;
		height: auto;
	}
	
	table, th, td {
	  border: 1px solid black;
	}
	
	tr
	{
	height: 34px;
	}
	
	.col1
	{
	width:65px;
	}
	.col2
	{
	width:385px;
	}
	.col3
	{
	width:85px;
	}
	
	a
	{
	color:black;
	text-decoration:none;
	}

</style>
</head>
<body>
	<table>
		<tr>
			<th class="col1">번호</th>
			<th class="col2">제목</th>
			<th class="col3">닉네임</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach items="${borderlist}" var="map">
		<tr>
			<td>${map["BORDERID"]}</td>
			<td><a href="borderView.do?no=${map.BORDERID}"><c:forEach begin="1" end="${map.GROUP_TAB}" step="1">RE:</c:forEach>${map.TITLE}</a></td>
			<td>${map.NICKNAME}</td>
			<td>${map["WRITEDAY"]}</td>
			<td>${map["SEECOUNT"]}</td>
		</tr>
		</c:forEach>
		<tr style="height:30px;">
			<td colspan="5" style="text-align:center">
			<ui:pagination paginationInfo = "${paginationInfo}"
			type="image"
			jsFunction="movePage"/>
			</td>
		</tr>
	</table>
</body>
</html>

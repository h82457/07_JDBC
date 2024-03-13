<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL Core 라이브러리 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>전체 부서 조회</title>
</head>
<body>

	<h1>전제 부서 조회</h1>

	
	<table border="1">
	
		<thead>
			<tr>
				<th>행 번호</th>
				<th>부서 코드 (DEPT_ID)</th>
				<th>부서 명   (DEPT_TITLE)</th>
				<th>지역 코드 (LOCATION_ID)</th>
			</tr>
		</thead>
		
		
		<tbody>
			<c:forEach items="${deptList}" var="dept" varStatus="vs">
				
				<tr>
					<%-- vs.count : 현재 반복 횟수 (1부터 시작) --%>
					<td>${vs.count}</td>
					
					<td>${dept.deptId}</td>
					
					<td>${dept.deptTitle}</td>
					
					<td>${dept.locationId}</td>
				</tr>
			
			</c:forEach>
		</tbody>
	
	
	</table>

	<%-- session scope로 전달 받은 message가 있으면 alert() 출력 --%>
	<%-- EL 내에서 Null값은 공란으로 해석, 비어있지 않을경우 -> null값이 아닌 값 --%>
	<%-- page ~ application 까지 message 속성이 있는지 확인해서 존재하는 scope의 값을 얻어옴 --%>
	<c:if test="${not empty message}" >

		<script>
			const message = "${message}";
			alert(message);

			<%-- session : 브라우저 종료/ 만료시까지 유지 -> 현재 페이지에 들어올때마다 session의 message가 계속 출력 --%>
			<%--  ㄴ> 1회만 message 출력후 제거 --%>

			<c:remove var="message" scope="session" />

		</script>
	</c:if>


</body>
</html>
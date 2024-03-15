<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${param.keyword} 검색 결과</title>
</head>
<body>
    <h1>"${param.keyword}" 검색 결과</h1>
	
	<%-- empty : 비어있거나 null인 경우 true 반환 --%>
	<c:if test="${empty deptList}" >

		<h3>검색 결과가 없습니다.</h3>
	</c:if>

	
	<%-- not empty : 비어있지않거나, null이 아닌 경우 true --%>
	<c:if test="${not empty deptList}" >
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
	</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List</title>
</head>
<body>
    <h1>Todo List</h1>
    <hr>

    <form action="/todo/add" method="POST">
        <h4>할 일 추가</h4>

        <div>
            제목 : <input type="text" name="todoTitle">
        </div>

        <div>
            <textarea name="todoContent" cols="50" rows="5" placeholder="상세 내용"></textarea>
        </div>

        <button>추가하기</button>
    </form>

    <hr>

    <h3>전체 Todo 개수 : ${fn:length(todoList)}개 / 완료된 Todo 개수 : ${completeCount}개</h3>
        <%-- fn:length(문자열|컬렉션) : 길이 반환 --%>
    <table border="1" style="border-collapse:collapse;">
        <thead>
            <th>번호</th>
            <th>할 일 제목</th>
            <th>완료 여부</th>
            <th>등록 날짜</th>
        </thead>

        <tbody>

            <c:forEach items="${todoList}" var="todo">

                <tr>
                    <td>${todo.todoNo}</td>
                    <td><a href="/todo/detail?todoNo=${todo.todoNo}">${todo.todoTitle}</a></td>
                    <td>${todo.complete}</td>
                    <td>${todo.regDate}</td>
                    <%-- db 조회 결과 이용 화면 출력 --%>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${not empty message}">
        <script>alert("${message}");</script>

       <c:remove var="message" />
    </c:if>

</body>
</html>
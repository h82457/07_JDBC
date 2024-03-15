<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${todo.todoTitle}</title>

    <style><%-- white-space : pre태그처럼 처리, HTML에 작성된 모양 그대로 화면에 출력 --%>
        table{border-collapse: collapse;}
        .todo-content{white-space: pre-wrap;}
    </style>
</head>
<body>
    
    <h3>${todo.todoTitle}</h3>

    <table border="1">
        <tr>
            <th>번호</th>
            <td>${todo.todoNo}</td>
        </tr>
       
        <tr>
            <th>등록 날짜</th>
            <td>${todo.regDate}</td>
        </tr>
        
        <tr>
            <th>완료 여부</th>
            <%-- data-* 속성
            - 데이터에 대한 확장성을 고려하여 설계된 속성
            - js에서 요소.dataset 을 이용해 해당 값을 얻어갈 수 있음
            --%>
            <td><button type="button" class="complete-btn" data-todo-no="${todo.todoNo}">${todo.complete}</button></td>
        </tr>

        <tr>
            <th>내용</th>
            <td class="todo-content">${todo.todoContent}</td>
        </tr>
    </table>

  <button id="goToList">목록으로</button>

    <%-- 할 일 번호를 숨겨둠 --%>
    <input type="hidden" name="todoNo" value="${param.todoNo}">
  <button id="updateBtn" data-todo-no="${todo.todoNo}">수정</button>
  <button id="deleteBtn" data-todo-no="${todo.todoNo}">삭제</button>

    <script src="/resources/js/detail.js"></script>

    <c:if test="${not empty message}">
        <script>alert("${message}");</script>

       <c:remove var="message" />
    </c:if>

</body>
</html>
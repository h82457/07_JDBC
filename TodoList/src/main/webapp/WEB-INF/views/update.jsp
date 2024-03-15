<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${todo.todoNo} 할 일 수정</title>
</head>
<body>
    <h1>${todo.todoNo} 할 일 수정</h1>

    <hr>

  <form action="/todo/update" method="POST">
    <div>
      제목 : <input type="text" name="todoTitle" value="${todo.todoTitle}">
    </div>


        <div> <%-- textarea: 값 세팅시 시작,종료 태그 사이에 작성, white-space : pre-wrap; 으로 설정-> 태그 사이 공백 모두 인식 --%>
      <textarea name="todoContent" 
        cols="50" rows="5" placeholder="상세 내용">${todo.todoContent}</textarea>
    </div>

    <%-- 할 일 번호를 숨겨둠 --%>
    <input type="hidden" name="todoNo" value="${param.todoNo}">

    <button>수정하기</button>
  </form>

    <c:if test="${not empty message}">
        <script>alert("${message}");</script>

       <c:remove var="message" />
    </c:if>
    
</body>
</html>
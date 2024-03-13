<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>부서 추가 페이지</title>
</head>
<body>
  
  <h1>부서 추가 페이지</h1>

  <%-- 주소에서 절대/상대 경로

    주소 형태  :  http://IP주소(도메인):PORT/요청주소

    ex) 현재 페이지 주소 : http://localhost/department/insert (GET)

    절대 경로 : PORT 번호 뒤에 '/' 부터  요청하려는 주소를 모두 작성
              /department/insert

    상대 경로 : 주소 제일 마지막 경로 부터 원하는 요청 주소까지를 작성
              insert
   --%>

  <%-- 상대 경로 방식으로 주소 작성 --%>
  <form action="insert" method="POST">
  
    <div>
      부서 코드(DEPT_ID) : <input type="text" name="deptId">
    </div>

    <div>
      부서 이름(DEPT_TITLE) : <input type="text" name="deptTitle">
    </div>

    <div>
      지역 코드(LOCATION_ID) : <input type="text" name="locationId">
    </div>

    <%-- button의 type 기본값은 submit --%>
    <button type="submit">추가 하기</button> <%-- ~> insertServlet --%>
  </form>

    <hr><hr>


  <h1>여러 부서 한 번에 추가 하기</h1>
  <form action="/department/multiInsert" method="post" name="multiInsertForm">
    
    <button type="button" id="addBtn">입력 추가</button>

    <table>
      <thead>
        <tr>
          <th>부서 코드</th>
          <th>부서명</th>
           <th>지역 코드</th>
          <th>삭제버튼</th>
        </tr>
      </thead>

      <tbody id="tbody">
        <tr>
          <td>
            <input type="text" name="deptId">
          </td>
           <td>            <input type="text" name="deptTitle">
          </td>
          <td>
            <input type="text" name="locationId">
          </td>
          <th>
            <button type="button" class="remove-btn">삭제</button>
          </th>
        </tr>
      </tbody>
    </table>

    <button>부서 추가 하기</button>
  </form>

<script src="/resources/js/insert.js"></script>
</body>
</html>
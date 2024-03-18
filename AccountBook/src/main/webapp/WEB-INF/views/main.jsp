<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>main</title>
    <link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>

    <div class="container">
        <div>
            <label>수입</label>
            <table>
                <thead>
                    <th>항목</th>
                    <th>금액</th>
                </thead>
                
                <tbody>
                    <c:forEach items="${accList}" var="acc" varStatus="vs">
                        <tr>
                            <td>${acc.inTitle}</td>
                            <td>${acc.inAmount}</td>
                            <td>${acc.inDate}</td>
                            <td>${acc.inBalance}</td>
                        </tr>                    
                    </c:forEach>





                </tbody>
            </table>         
        </div>

        <div>
            <label>지출</label>
            <table;>
                <thead>
                    <th>항목</th>
                    <th>금액</th>
                </thead>
                
                <tbody>
                    <tr>
                        <td>샘플1</td>
                        <td>3000</td>
                    </tr>
                </tbody>
            </table;>         
        </div>
   
    </div>

    

</body>
</html>
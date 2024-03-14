/* selectAll.js */

// 삭제 버튼 클릭시 해당 행의 부서 코드 얻어오기

const deleteBtnList = document.querySelectorAll(".delete-btn");

// deleteBtnList.addEventListner() <-- 불가!
// ㄴ> EventListner는 DOM 요소에 추가해야 동작, deleteBtnList 는 DOM 요소 x, 배열(유사배열, NodeList)
//     ㄴ> [해결방법] 배열 내 각 요소를 하나씩 꺼내서 EventListner를 추가

for(let btn of deleteBtnList){ // 향상된 for문

    btn.addEventListener("click", e => {

        // 클릭된 삭제 버튼이 존재하는 행의 부서 코드 얻어오기

        // const deptId = e.target.parentElement.parentElement.children[1].innerText;
        // 
        const deptId = e.target.closest("tr").children[1].innerText;
        // 요소.closest("CSS선택자"): 지정된 요소의 상위 요소 (부모방향, 루트 최상위까지)중
//      CSS 선택자가 일치하는 요소를 찾을때까지 검색, 일치하는 요소 반환, 없으면 null


        if(confirm(`${deptId} 부서를 정말 삭제 하시겠습니까?`)){ // 확인 클릭시

            /* 삭제 요청 보내기 1) location.href 이용, 권장 X */

            // location.href = "/department/delete?deptId=" + deptId;
//              ㄴ> http://localhost/department/delete?deptId=A2            
//                                      요청주소   <~ ? ~ <- 쿼리스트링(데이터) <= GET방식 요청
//           => 임의의 사용자가 삭제요청을 마음대로 보내는 문제 발생 가능

            /* 삭제 요청 보내기 2) form태그를 만들어서 POST 방식 요청 보내기 */
            const form = document.createElement("form");
            form.action = "/department/delete";
            form.method = "POST";

            // input type="hidden" 생성
            const input = document.createElement("input");
            input.type = "hidden";
            input.value = deptId; // 값으로 부서 코드 대입
            input.name = "deptId"; // 파라미터 키 값 지정

            // form 자식으로 input 추가
            form.append(input);

            // body 태그 최하단에 form 추가
            document.querySelector("body").append(form);

            // 화면에 추가된 form 제출
            form.submit();

        } else   alert("삭제 취소"); // 취소 클릭
    });
}

// -----------------------------------------------------------------------------

// .update-btn 요소 모두 얻어오기
const updateBtnList = document.querySelectorAll(".update-btn");

// updateBtnList 배열의 모든 요소에 순차 접근 + 이벤트 리스너 추가
// updateBtnList.forEach( (요소, 인덱스) => {}); <-  updateBtnList에서 하나씩 접근
updateBtnList.forEach( (btn, index) => {

    btn.addEventListener('click', e => {

        // 부모 요소중 가장 가까운 tr 태그 찾기
        const tr = e.target.closest("tr");

        // 부서 코드 얻어오기
        const deptId = tr.children[1].innerText;

        // js에서 요청하기(GET)
        location.href = "/department/update?deptId=" + deptId;



    })

});







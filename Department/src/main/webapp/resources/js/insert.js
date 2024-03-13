// id가 addBtn인 버튼 요소 얻어오기
const addBtn = document.querySelector("#addBtn");

// id tbody인 요소 얻어오기
const tbody = document.querySelector("#tbody");

// addBtn이 클릭시 (이벤트 리스너)추가
addBtn.addEventListener("click", () => {

    // 한행을 나타내는 tr 요소 생성
    const tr = document.createElement("tr");
    
    // name 속성 값이 저장된 배열 생성
    const names = ['deptId', 'deptTitle', 'locationId'];

    // 배열 요소 순차 접근 (향상된 for 문)
    for(let name of names){
        
        const td = document.createElement("td"); // td 요소 생성
        const input = document.createElement("input"); // input 요소 생성
        
        input.type = "text"; // type="text" 설정
        input.name = name; // 배열 요소를 name 속성 값으로 설정

        td.append(input);
        tr.append(td);
    }

    // 규칙성이 어긋난 th > button 따로 만들기
    const th = document.createElement("th");
    const button = document.createElement("button");

    button.type = "button"; // type="button" 설정
    button.classList.add("remove-btn"); // 클래스 추가
    button.innerText = "삭제";

    // 삭제 버튼 클릭시 동작
    button.addEventListener("click", removeFn);
//  ㄴ> 함수명: 함수 코드(정의한 내용)을 현재 위치로 가져오기 / 함수명(): 함수 호출

    th.append(button);
    tr.append(th);
    tbody.append(tr); // #tbody에 한줄 추가
});

/* 삭제 버튼 클릭시 해당행 제거하는 함수 정의 */
function removeFn(e) {

    // e: 이벤트 객체
    // e.target : 이벤트가 발생한 요소 (== 삭제버튼)
    const tr = e.target.parentElement.parentElement;
    tr.remove(); // tr 요소 제거
}

// 원래 존재하던 삭제 버튼에 remove 동작 추가 
document.querySelector(".remove-btn").addEventListener("click", removeFn);
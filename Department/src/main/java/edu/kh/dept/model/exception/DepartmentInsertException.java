package edu.kh.dept.model.exception;

// 사용자 정의 예외 생성 방버! -> 아무 Exception 클래스를 상속 + RuntimeException 또는 그 자식 예외를 상속 => unchecked Exception 으로 설정
public class DepartmentInsertException extends RuntimeException {

	// 기본 생성자
	public DepartmentInsertException() {
		
		super("부서 추가(INSERT) 중 예외 발생 (제약 조건 위배)");
	}
	
	// 매개 변수 생성자
	public DepartmentInsertException(String message) {
		super(message);
	}
}

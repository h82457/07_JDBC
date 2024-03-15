package edu.kh.todoList.model.dto;

public class Todo {

	private int todoNo; // 할 일 번호
	private String todoTitle; // 할 일 제목
	private String todoContent; // 할 일 내용
	private String Complete; // 할 일 완료 여부 ("Y", "N")
	private String regDate; // 할 일 등록일(String으로 변환)
	
	// 기본 생성자
	public Todo() {}

	
	// 매개 변수 생성자 (할 일 목록 조회, todoContent 제외)
	public Todo(int todoNo, String todoTitle, String complete, String regDate) {
		super();
		this.todoNo = todoNo;
		this.todoTitle = todoTitle;
		Complete = complete;
		this.regDate = regDate;
	}


	public Todo(int todoNo, String todoTitle, String todoContent) {
		super();
		this.todoNo = todoNo;
		this.todoTitle = todoTitle;
		this.todoContent = todoContent;
	}


	// getter / setter
	public int getTodoNo() {
		return todoNo;
	}

	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}

	public String getTodoTitle() {
		return todoTitle;
	}

	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}

	public String getTodoContent() {
		return todoContent;
	}

	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}

	public String getComplete() {
		return Complete;
	}

	public void setComplete(String complete) {
		Complete = complete;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	// toString
	@Override
	public String toString() {
		return "Todo [todoNo=" + todoNo + ", todoTitle=" + todoTitle + ", todoContent=" + todoContent + ", Complete="
				+ Complete + ", regDate=" + regDate + "]";
	}
	
	
}

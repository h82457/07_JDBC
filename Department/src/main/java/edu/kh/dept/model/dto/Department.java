package edu.kh.dept.model.dto;

// DTO 객체 하나가 DEPARTMENT4 테이블의 한행 데이터를 저장하는 용도 (한 행을 insert, select 시 사용)
public class Department {
	
	private String deptId;
	private String deptTitle;
	private String locationId;
	
	public Department() {}

	// 매개변수 생성자
	public Department(String deptId, String deptTitle, String locationId) {
		super();
		this.deptId = deptId;
		this.deptTitle = deptTitle;
		this.locationId = locationId;
	}
	
	// getter/setter
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptTitle() {
		return deptTitle;
	}

	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	// toString
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptTitle=" + deptTitle + ", locationId=" + locationId + "]";
	}
	
}

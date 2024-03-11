package edu.kh.jdbc.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.model.dao.DepartmentDAO;
import edu.kh.jdbc.model.dto.Department;

public class JDBCRun2 {
	public static void main(String[] args) {
		
		DepartmentDAO dao = new DepartmentDAO();
		
		try {
			
			// 1. 부서 추가
			Scanner sc = new Scanner(System.in);
			
			System.out.print("부서 코드 입력 : ");
			String deptId = sc.next();
			
			System.out.print("부서명 입력 : ");
			String deptTitle = sc.next();
			
			System.out.print("지역 코드 입력 : ");
			String locationId = sc.next();
			
			
			// 2. 부서 전체 조회
			
			// *** DAO 메서드 호출후 결과 반환 받기 ***
			Department dept = new Department(deptId, deptTitle, locationId);
				
			// DB 삽입 후 결과 반환 받기
			int result = dao.insertDepartment(dept);
			
			if(result > 0) System.out.println("[삽입 성공]");
			else System.out.println("[삽입 실패]");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	



}



package goodee.gdj58.online.vo;

import lombok.Data;

@Data // lombok 애노테이션이 get/set, toString 등 메서드를 자동으로 만든다. @Getter, @Setter, @ToString 등이 있음
public class Employee {
	private int empNo;	
	private String empId;
	private String empPw;
	private String empName;
}

package goodee.gdj58.online.vo;

import lombok.Data;

@Data
public class Paper {
	private int paperNo;
	private int studentNo;
	private int questionNo;
	private String answer; // 객관식, 단답형
}
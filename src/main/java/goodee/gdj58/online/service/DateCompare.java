package goodee.gdj58.online.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
public class DateCompare {
	
	
	// 날짜 구하는 메서드
	public String getDate(int dateGap) {
		// LocalDate 기본 포맷 : yyyy-MM-dd
		LocalDate todayDate = LocalDate.now();
		LocalDate resultDate = todayDate.plusDays(dateGap);
		return resultDate.toString();
	}

	// 오늘날짜와 비교하는 메서드
	public int todayCompare(String date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 날짜 포맷 설정
		
		// 오늘날짜
		LocalDate todayDate = LocalDate.now();
		
		// 비교 날짜 
		LocalDate paramDate = LocalDate.parse(date, format);

		int result = todayDate.compareTo(paramDate);
		
		return result; // 오늘 == 매개변수 날짜 : 0, 오늘<매개변수날짜 : 1, 오늘>매개변수날짜 : -1
	}
}

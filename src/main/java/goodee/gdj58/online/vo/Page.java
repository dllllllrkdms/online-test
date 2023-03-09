package goodee.gdj58.online.vo;


import java.util.Map;

import lombok.Data;

@Data
public class Page {
	private int startPage; // 시작페이지
	private int endPage; // 끝 페이지
	private boolean prev; // 이전 페이지 존재 유무
	private boolean next; // 다음 페이지 존재 유무
	private int totalCount; // 전체 게시물 수 
	private int lastPage; // 마지막 페이지
	private int rowPerPage; // 한 페이지에 보여질 게시물 수
	private int pageSize; // 한번에 보여질 페이지 개수
	private int currentPage; // 현재 페이지
	private int beginRow; // 첫 행 
	private Map<String, Object> map; // 검색어와 조건

	/* 생성자 */
	public Page(int totalCount, int currentPage, int rowPerPage, int pageSize, Map<String, Object> map) {
		this.map = map;
		this.totalCount = totalCount;
		this.rowPerPage = rowPerPage;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		
		// 마지막 페이지
		this.lastPage = totalCount/rowPerPage; 
		if(totalCount%rowPerPage!=0) {
			this.lastPage+=1;
		}
		
		if(currentPage>lastPage) {
			currentPage = lastPage;
		}
		
		this.beginRow = (currentPage-1)*rowPerPage;
		// 시작 페이지 
		this.startPage = (currentPage-1)/pageSize*pageSize + 1;
		// 끝 페이지
		this.endPage = this.startPage + pageSize - 1;
	
		if(this.startPage<1) {
			this.startPage = 1;
		} 
		if(this.endPage>this.lastPage) {
			this.endPage = this.lastPage;
		}
	}
}
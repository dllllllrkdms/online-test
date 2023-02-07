package goodee.gdj58.online.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j // static log객체 주입
@WebFilter("/employee/*")
public class EmpLoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		log.debug("\u001B[31m"+"EmpLoginFilter");
		
		// emp 로그인 유효성 검사
		if(request instanceof HttpServletRequest) { // instanceof : 형변환 가능여부 (boolean)
			// request가 웹요청(HttpServletRequest) -> response도 웹요청
		
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
			if(session.getAttribute("loginEmp")==null) { 
				((HttpServletResponse)response).sendRedirect(req.getContextPath()+"/index");
				return;
			} 
		} else { // 디버깅코드 
			log.debug("웹브라우저 요청만 허용합니다.");
			return;
		}
		
		// controller 실행 전 
		chain.doFilter(request, response);
		// controller 후
		
	}
}
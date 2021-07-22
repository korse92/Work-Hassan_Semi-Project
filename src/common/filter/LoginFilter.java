package common.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
//@WebFilter("/*")
public class LoginFilter implements Filter {
	
	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>
		(
			//로그인 필터적용 제외할 url 경로
			Arrays.asList(
					"", "/member/memberLogin", "/member/memberEnroll", "/member/checkIdDuplicate",
					"/member/memberIdFind", "/member/memberPwdFind", "/member/successPwdFind", 
					"/css", "/front", "/img", "/js", "/upload", "/vendor", "/websocket"
					)
		)
	);

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpResp = (HttpServletResponse)response;
		
		String path = httpReq.getRequestURI().substring(httpReq.getContextPath().length()).replaceAll("[/]+$", "");
		
		HttpSession session = httpReq.getSession();
		
		boolean loggedIn = (session != null && session.getAttribute("memberLoggedIn") != null);
		boolean allowedPath = ALLOWED_PATHS.contains(path);
				
		//로그인했을 경우 요청대로 이동
		if(loggedIn || allowedPath) {
			chain.doFilter(request, response);
		} else {			
			session.setAttribute("msg", "로그인 후 이용하실 수 있습니다.");
			httpResp.sendRedirect(httpReq.getContextPath());
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

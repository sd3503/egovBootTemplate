package egovframework.servicename.utils.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {
    /**
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @param filterChain filterChain
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            log.info("User" + authentication.getName() + "is successfully authentication and" +
                    "has the authorities" + authentication.getAuthorities().toString());
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}

package egovframework.servicename.utils.filter;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsrfCookieFilter extends OncePerRequestFilter {
    /**
     * @param request request
     * @param response response
     * @param filterChain filterChain
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        //Render the token value to a cookie by causing the default token to be loaded
        csrfToken.getToken();
        filterChain.doFilter(request,response);
    }

/*    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        return false;
    }*/
}

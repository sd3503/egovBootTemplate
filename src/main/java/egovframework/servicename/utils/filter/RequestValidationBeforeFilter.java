package egovframework.servicename.utils.filter;



import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestValidationBeforeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if(null != header){
            header = header.trim();
            if(StringUtils.startsWithIgnoreCase(header, "Basic")){
                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decoded;
                try {
                    decoded = Base64.getDecoder().decode(base64Token);
                    String token = new String(decoded,StandardCharsets.UTF_8);//Username:pwd
                    int delim = token.indexOf(":");

                    if(delim == -1){
                        throw new BadCredentialsException("Invalid basic authentication token");
                    }
                    String email = token.substring(0,delim);
                    /*if(email.toLowerCase().contains("test")){
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }*/
                }catch (IllegalArgumentException exception){
                    throw new BadCredentialsException("Failed to decode basic authentication token");
                }

            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }
}

package ssj.security.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author harveylo
 * @description AuthenticationEntryPoint 解決匿名用戶訪問需要權限才能訪問資源時的異常
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 當用戶常是訪問需要權限的資源而不提供Token或是Token過期或錯誤 發送401 錯誤訊息
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}

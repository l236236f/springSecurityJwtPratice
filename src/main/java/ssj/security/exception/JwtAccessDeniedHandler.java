package ssj.security.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author harveylo
 * @description AccessDeineHandler  處理 認證過的用戶但沒有權限訪問資源的異常
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 認證過的用戶但沒有權限訪問資源的異常會發送403forbidden
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        accessDeniedException = new AccessDeniedException("Sorry you don not enough permissions to access it!");
        response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
    }
}

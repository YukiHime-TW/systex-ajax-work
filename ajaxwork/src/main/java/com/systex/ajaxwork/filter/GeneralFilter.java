package com.systex.ajaxwork.filter;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.systex.ajaxwork.model.MemberModel;
import com.systex.ajaxwork.service.MemberService;
import com.systex.ajaxwork.util.PasswordUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class GeneralFilter extends OncePerRequestFilter {

    private final MemberService memberService;

    public GeneralFilter(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // 設置編碼
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // get session
        HttpSession session = request.getSession(false); // 不要創建新的 session
        Object user = (session != null) ? session.getAttribute("loggedInUser") : null;

        String uri = request.getRequestURI();

        // 如果是登錄請求，處理登入邏輯
        if (uri.endsWith("/login") && request.getMethod().equalsIgnoreCase("POST")) {
            handleLogin(request, response, session);
        }

        // 如果是註冊請求，處理註冊邏輯
        if (uri.endsWith("/register") && request.getMethod().equalsIgnoreCase("POST")) {
            handleRegister(request, response, session);
        }

        // 如果是錯誤頁面，繼續處理請求
        if (uri.endsWith("/error")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 如果是 h2-console，繼續處理請求
        if (uri.contains("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 如果用戶未登入且請求不是登入或註冊，則重定向到登入頁面
        if (user == null && !uri.endsWith("/login") && !uri.endsWith("/register")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 如果用戶已登入
        if (user != null) {

            // 若請求是登入或註冊或沒有請求，則重定向到首頁
            if (uri.endsWith("/login") || uri.endsWith("/register") || uri.endsWith("/")) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }
            
        }

        // 繼續處理請求
        filterChain.doFilter(request, response);

    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {

        MemberModel member;

        // 獲取用戶名和密碼
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 嘗試查詢用戶
        try {
            member = memberService.findByUsername(username);
        } catch (Exception e) {
            // 找不到用戶，返回錯誤信息並轉發回登入頁
            sendErrorResponse(request, response, "找不到用戶");
            return;
        }

        // 驗證密碼
        if(member != null && PasswordUtil.verifyPassword(password, member.getPassword())) {
            // 登錄成功，將用戶存入 session
            if (session == null) {
                session = request.getSession(true); // 確保總是創建 session
            }
            session.setAttribute("loggedInUser", member);

            // AJAX 請求返回成功
            if (isAjaxRequest(request)) {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            sendErrorResponse(request, response, "用戶名或密碼錯誤");
        }

    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {

        MemberModel member;

        // 獲取用戶名和密碼
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // 檢查密碼是否一致
        if (!password.equals(confirmPassword)) {
            // 密碼不一致，返回錯誤信息
            sendErrorResponse(request, response, "密碼不一致");
            return;
        }

        // 檢查用戶名是否已存在
        try {
            memberService.checkIfUserExists(username);
        } catch (Exception e) {
            // 用戶名已存在，返回錯誤信息
            sendErrorResponse(request, response, "用戶名已存在");
            return;
        }

        // 創建新用戶
        member = new MemberModel();
        member.setUsername(username);
        member.setPassword(PasswordUtil.hashPassword(password));

        try {
            memberService.save(member);

            if (isAjaxRequest(request)) {
                response.setStatus(HttpServletResponse.SC_OK);
            }

        } catch (Exception e) {
            // 保存失敗，返回錯誤信息
            sendErrorResponse(request, response, "註冊失敗，請重試");
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    private void sendErrorResponse(HttpServletRequest request, HttpServletResponse response,String message)
            throws IOException {
        // 如果是 AJAX 請求，返回錯誤消息
        if (isAjaxRequest(request)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            request.removeAttribute("error"); // 對於 AJAX 請求，不需要設置錯誤屬性
        } else {
            request.setAttribute("error", message); // 對於非 AJAX 請求，設置錯誤屬性
        }
    }

}

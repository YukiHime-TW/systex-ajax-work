package com.systex.ajaxwork.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.systex.ajaxwork.model.MemberModel;
import com.systex.ajaxwork.service.MemberService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class GeneralFilter extends OncePerRequestFilter {

    @Autowired
    private MemberService memberService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // get session
        HttpSession session = request.getSession(false); // 不要創建新的 session
        Object user = (session != null) ? session.getAttribute("loggedInUser") : null;

        String uri = request.getRequestURI();

        System.out.println("request.getRequestURI(): " + uri);
        System.out.println("request.getMethod(): " + request.getMethod());

        // 如果是登錄請求，處理登入邏輯
        if (uri.endsWith("/login") && request.getMethod().equalsIgnoreCase("POST")) {
            handleLogin(request, response, session);
        }

        // 如果是錯誤頁面，繼續處理請求
        if (uri.equals("/error")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 如果是 h2-console，繼續處理請求
        if (uri.contains("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 如果用戶未登入且請求不是登入或註冊，則重定向到登入頁面
        if (user == null && !uri.endsWith("/login")
                && !uri.endsWith("/register")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
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
            request.setAttribute("error", "找不到用戶");
            return;
        }

        // 驗證密碼
        if (member != null && member.getPassword().equals(password)) {
            // 登錄成功，將用戶存入 session
            if (session == null) {
                session = request.getSession(); // 如果 session 不存在，創建一個新的
            }
            session.setAttribute("loggedInUser", member);
            return;
        } else {
            request.setAttribute("error", "用戶名或密碼錯誤");
            return;
        }
    }
}

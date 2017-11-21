package com.livgo.core.filter;

import com.livgo.common.Const;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:字符编码过滤器
 * Author:     Livgo
 * Date:       2017/11/15
 * Verion:     V1.0.0
 * Update:     更新说明
 */
public class CharacterFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        request.setCharacterEncoding(Const.ENCODE);
        response.setCharacterEncoding(Const.ENCODE);
        filterChain.doFilter(request, response);
    }
}

package jp.co.cosmoroot.bpmp.core.share;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.cosmoroot.bpmp.core.service.CustomUserDetailsService;

/**
 * @author cosmoroot
 *
 * トークン認証フィルタ
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // トークン認証
    private final JwtTokenProvider jwtTokenProvider;
    // ユーザ情報
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
            CustomUserDetailsService customUserDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * トークン認証
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // AuthorizationヘッダーからJWTトークンを取得
        String header = request.getHeader("Authorization");

        String token = null;
        String username = null;

        // トークンが存在する場合ユーザ情報を取得
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            username = jwtTokenProvider.getUsernameFromJWT(token);
        }

        // トークンが存在し、ユーザが認証されていない場合に認証を行う
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (jwtTokenProvider.validateToken(token, userDetails)) {
                // トークンが有効なら、ユーザーを認証状態にする
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // /auth/loginへのリクエストの場合はフィルタを適用しない
        return path.startsWith("/auth/login");
    }
}

package jp.co.cosmoroot.bpmp.core.share;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    private final long jwtExpirationInMs = 3600000; // トークンの有効期限（1時間）
    private final String jwtSecret = "yourSecretKey";


    // トークン生成メソッド
    public String createToken(String userID) {

        // トークンの有効期限設定
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);


        return Jwts.builder()
                .setSubject(userID)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret) // シークレットキーで署名
                .compact();
    }

    /**
     * トークンからユーザー名を取得
     * 
     * @param token リクエストされたトークン
     * @return ユーザ名
     */
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // トークンの有効性を確認
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromJWT(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // トークンが期限切れかどうかを確認
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}


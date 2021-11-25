package com.newland.mes.system.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expriation}")
    private long expriation=604800;

    /**
     * 根据用户信息生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 从token中获取荷载
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token)
    {
        Claims claims=null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 获取失效时间
     * @param token
     * @return
     */
    public Date getExpiredDateFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断是否可以刷新token
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String Refreshtoken(String token){
        Claims claims=getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    /**
     * 判断token是否有效
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token){
        Date expirDate =getExpiredDateFromToken(token);
        return expirDate.before(new Date());
    }
    /**
     * 校验token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username=getUserNameFromToken(token);
        return userDetails.getUsername().equals(username);
    }
    /**
     * 从token中获取登陆用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username=claims.getSubject();
        } catch (Exception e) {
           username=null;
        }
        return username;
    }
    /**
     * 根据荷载生成JWT TOKEN
     * @param claims
     * @return
     */
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.ES256,secret)//设置签名
                .compact();
    }

    /**
     * 生成token失效时间
     * @return
     */
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+expriation);
    }
}

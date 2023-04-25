package com.allo.common.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author FuYiCheng
 * @date 2023-02-14 22:11
 * @description:生成JSON Web令牌的工具类
 * @version:
 */

public class JwtHelper {

    //过期时间，单位是ms
    private static long tokenExpiration = 365 * 24 * 60 * 60 * 1000;
    //加密秘钥
    private static String tokenSignKey = "123456";

    public static String createToken(String userId, String username) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    public static String getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) return null;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            String userId =(String) claims.get("userId");
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) return "";

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

    public static void main(String[] args) {
        String token = JwtHelper.createToken("1", "admin");
        System.out.println("token = " + token);

        String userId = JwtHelper.getUserId(token);
        System.out.println("userId = " + userId);

        String username = JwtHelper.getUsername(token);
        System.out.println("username = " + username);
    }
}

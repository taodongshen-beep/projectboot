package com.example.projectboot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.projectboot.pojo.User;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import java.util.*;
@Log4j2

public class TokenUtil {
    // token 密钥
    private final static String secret = "KJHUhjjJYgYUllVbXhKDHXhkSyHjlNiVkYzWTBac1Yxkjhuad";

    //生成token
    // 传入用户信息，用户名，密码，id等信息
    public  String createToken(User user) {

        Date nowDate = new Date();
        Date expireDate = getAfterDate(nowDate, 0, 0, 0, 2, 0, 0);// 2小过期

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

       /* JSON json = new JSONObject();
        String userJson = json.toJSONString(user);*/
        // String encode = URLEncoder.encode(userJson, "UTF-8");
        Gson gson = new Gson();
        String userJson = gson.toJson(user);

        String userJsonBase64 = BaseEncoding.base64().encode(userJson.getBytes());

        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create().withHeader(map)
                .withClaim("loginName", user.getUserName())  //挂载用户信息1
                .withClaim("user", userJsonBase64)    //挂载用户信息2
                .withIssuer("SERVICE")// 签名是有谁生成
                .withSubject("this is test token")// 签名的主题
                // .withNotBefore(new Date())//该jwt都是不可用的时间
                .withAudience("APP")// 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) // 生成签名的时间
                .withExpiresAt(expireDate)// 签名过期的时间
                /* 签名 Signature */
                .sign(algorithm);

        return token;
    }


    //解析token
    public  boolean verifyToken(String token) throws Exception {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("SERVICE").build(); // Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            String subject = jwt.getSubject();
            List<String> audience = jwt.getAudience();
            Map<String, Claim> claims = jwt.getClaims();
            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                String key = entry.getKey();
                Claim claim = entry.getValue();
                log.info("key:" + key + " value:" + claim.asString());
            }
            Claim claim = claims.get("user");
            byte[] bt = BaseEncoding.base64().decode(claim.asString());
            String str = new String(bt);
            Gson gson = new Gson();
            User  user = new Gson().fromJson(str, User.class);
            log.info(user);
            log.info(subject);
            log.info(audience.get(0));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


    public   Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second){
        if(date == null){
            date = new Date();
        }

        Calendar cal = new GregorianCalendar ();

        cal.setTime(date);
        if(year != 0){
            cal.add(Calendar.YEAR, year);
        }
        if(month != 0){
            cal.add(Calendar.MONTH, month);
        }
        if(day != 0){
            cal.add(Calendar.DATE, day);
        }
        if(hour != 0){
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute != 0){
            cal.add(Calendar.MINUTE, minute);
        }
        if(second != 0){
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }
}

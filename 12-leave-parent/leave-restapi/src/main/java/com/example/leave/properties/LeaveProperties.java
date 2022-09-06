package com.example.leave.properties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LeaveProperties {

    /**
     * JWT密钥
     * 需大于32位的字符串
     */
    private String jwtSecretKey = "codingapi.security.jwt.secretkey";

    /**
     * JWT 有效时间(毫秒)
     * 15分钟有效期 1000*60*15=900000
     */
    private int jwtTime = 900000;

    /**
     * JWT 更换令牌时间(毫秒)
     * 10分钟后更换令牌 1000*60*10=600000
     */
    private int jwtRestTime = 600000;
}

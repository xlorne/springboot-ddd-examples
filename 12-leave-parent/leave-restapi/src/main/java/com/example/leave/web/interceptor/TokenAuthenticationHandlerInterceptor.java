package com.example.leave.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.springboot.framework.dto.response.Response;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import com.example.leave.jwt.Jwt;
import com.example.leave.jwt.Token;
import com.example.leave.jwt.TokenContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@AllArgsConstructor
public class TokenAuthenticationHandlerInterceptor implements HandlerInterceptor {

    private final static String TOKEN_KEY = "Authorization";

    private final Jwt jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        TokenContext.getInstance().clear();
        String sign = request.getHeader(TOKEN_KEY);

        if(!StringUtils.hasLength(sign)){
            writeResponse(response, Response.buildFailure("token.null","token was null"));
            return false;
        }
        Token token = jwt.parser(sign);
        if(token.canRestToken()){
            Token newSign = jwt.create(token.getUsername());
            log.info("reset token ");
            response.setHeader(TOKEN_KEY,newSign.getToken());
        }
        try {
            token.verify();
        } catch (LocaleMessageException e) {
            writeResponse(response,Response.buildFailure(e.getErrCode(),e.getErrMessage()));
            return false;
        }
        TokenContext.getInstance().push(token);
        return true;
    }
    private void writeResponse(HttpServletResponse servletResponse,Response returnResponse) throws IOException {
        String content = JSONObject.toJSONString(returnResponse);
        IOUtils.write(content,servletResponse.getOutputStream(), StandardCharsets.UTF_8);
    }

}

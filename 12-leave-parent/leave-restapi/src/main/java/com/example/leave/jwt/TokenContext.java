package com.example.leave.jwt;

public class TokenContext {

    private ThreadLocal<Token> threadLocal = new ThreadLocal<>();

    private TokenContext(){

    }

    private static TokenContext context = new TokenContext();

    public static TokenContext getInstance() {
        return context;
    }

    public void clear(){
        threadLocal.set(null);
    }

    public void push(Token token){
        threadLocal.set(token);
    }

    public Token currentToken(){
        return threadLocal.get();
    }

    public String currentUserName(){
        return currentToken().getUsername();
    }

    public long currentUserId() {
        return currentToken().getUserId();
    }
}

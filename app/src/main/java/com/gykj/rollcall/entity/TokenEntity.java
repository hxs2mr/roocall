package com.gykj.rollcall.entity;

/**
 * desc   : Token返回实体类
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2018/12/2414:28
 * version: 1.0
 */
public class TokenEntity {


    /**
     * access_token : Z5LRwMY7uIusYSePy9J51QkcfEK5My
     * expires_in : 259200
     * token_type : Bearer
     * scope : read write
     * refresh_token : Ho06VQXwQh8VO4rp7VAyIf1A7H6T07
     */

    private String access_token;
    private int expires_in;
    private String token_type;
    private String scope;
    private String refresh_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}

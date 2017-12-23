package com.roye.shrio.Realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;

/**
 * 查询数数据库， 拿到数据
 */
public class ShrioRealm extends AuthenticatingRealm {

    /**
     * 获取认证消息，如果数据库中没有数据， 返回null,得到正确的用户名和密码的话， 呢么就返回指定类型
     * 的对象；
     * <p>
     * <p>
     * AuthenticationInfo represents a Subject's (aka user's) stored account information relevant to
     * the authentication/log-in process only.
     *
     * @param token 参数就是需要认证的token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //1. 将token 转成UserNamePasswordToken;

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 2. 获取用户名和密码：
        String userName = upToken.getUsername();

        // 3.查询数据库， 是否存在指定用户名和密码的用户;

        // 4. 查到了的话，封装查询结果， 返回给调用；

        // 5. 没有的话， 那么就抛出一个异常；
        return null;
    }
}

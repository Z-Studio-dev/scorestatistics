package cn.scorestatistics.demo.shiro;

import cn.scorestatistics.demo.model.entity.TbUser;
import cn.scorestatistics.demo.service.intf.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 返回权限信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 获取用户名
        String username = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获得授权角色
        authorizationInfo.setRoles(userService.getRoles(username));
        // 获得授权权限
        authorizationInfo.setStringPermissions(userService.getPermissions(username));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 获取用户名密码
        String username = authenticationToken.getPrincipal().toString();
        TbUser tbUser = userService.getUserByUsername(username);
        if(tbUser != null) {
            // 得到用户账号和密码存放到authenticationInfo中用于Controller层的权限判断
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(tbUser.getUsername(), tbUser.getPassword(), tbUser.getUsername());
            return authenticationInfo;
        }
        return null;
    }
}

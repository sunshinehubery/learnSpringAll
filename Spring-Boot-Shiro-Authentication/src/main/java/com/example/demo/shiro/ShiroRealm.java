package com.example.demo.shiro;

import com.example.demo.bean.Permission;
import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.dao.UserMapper;
import com.example.demo.dao.UserPermissionMapper;
import com.example.demo.dao.UserRoleMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/1/18 21:13
 * @Version: 1.0
 **/
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserPermissionMapper userPermissionMapper;
    // 获取用户角色和权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUserName();
        System.out.println("用户" + userName + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取用户角色集合
        List<Role> roleList = userRoleMapper.findRolesByUserName(userName);
        HashSet<String> roleSet = new HashSet<>();
        for (Role r : roleList) {
            roleSet.add(r.getName());
        }
        simpleAuthorizationInfo.setRoles(roleSet);
        //获取用户权限集合
        List<Permission> permissionsList = userPermissionMapper.findPermissionsByUserName(userName);
        HashSet<String> permissionSet = new HashSet<>();
        for (Permission p : permissionsList) {
            permissionSet.add(p.getName());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }
    // 用户登录校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户输入的账号和密码
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());
        System.out.println("用户" + username + "认证-----ShiroRealm.doGetAuthenticationInfo");
        //通过用户名到数据库查询用户信息
        User user = userMapper.findByUserName(username);
        if (user == null){
            throw new UnknownAccountException("用户或密码错误！");
        }
        if (!password.equals(user.getPassword())){
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (user.getStatus().equals("0")) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}

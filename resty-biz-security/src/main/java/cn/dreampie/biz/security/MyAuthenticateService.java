package cn.dreampie.biz.security;

import cn.dreampie.biz.security.model.Permission;
import cn.dreampie.biz.security.model.User;
import cn.dreampie.security.AuthenticateService;
import cn.dreampie.security.Principal;
import cn.dreampie.security.credential.Credential;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cengruilin on 2017/5/8.
 */

public class MyAuthenticateService extends AuthenticateService {
    public Principal getPrincipal(String username) {
        User user = User.dao.findFirstBy("username=? AND deleted_at IS NULL", username);
        if (user != null) {
            String password = user.<String>get("password");
            user.remove("password");
            return new Principal<User>(username, password, user.getPermissionValues(), user);
        } else
            return null;
    }

    public Set<Credential> getAllCredentials() {
        List<Permission> permissions = Permission.dao.findBy("deleted_at IS NULL");
        Set<Credential> credentials = new HashSet<Credential>();

        for (Permission permission : permissions) {
            credentials.add(new Credential(permission.<String>get("method"), permission.<String>get("url"), permission.<String>get("value")));
        }

        return credentials;
    }
}


package cn.dreampie.biz.security;

import cn.dreampie.biz.security.model.User;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.DELETE;
import cn.dreampie.route.annotation.GET;
import cn.dreampie.route.annotation.POST;
import cn.dreampie.security.Principal;
import cn.dreampie.security.Subject;

/**
 * Created by cengruilin on 2017/5/8.
 */
@API("/sessions")
public class SessionResource extends SecurityBaseResource {
    @GET
    public User get() {
//    测试 通过主键生成器生成主键 如 uuid
//    Token token = new Token();
//    token.set("username", "a").set("created_at", new Date()).set("expiration_at", new Date(new Date().getTime() + 1000)).set("used_to", 0);
//    if (token.save()) {
//      System.out.println(Jsoner.toJSON(token));
//    }
        Principal<User> principal = Subject.getPrincipal();
        if (principal != null)
            return principal.getModel();
        else
            return null;
    }


    @POST
    public User login(String username, String password) {
        Subject.login(username, password, true);
        return (User) Subject.getPrincipal().getModel();
    }


    @DELETE
    public boolean logout() {
        Subject.logout();
        return true;
    }
}


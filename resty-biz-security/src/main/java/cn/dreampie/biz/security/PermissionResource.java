package cn.dreampie.biz.security;

import cn.dreampie.biz.security.model.Permission;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.GET;

import java.util.List;

/**
 * Created by cengruilin on 2017/5/8.
 */
@API("/permissions")
public class PermissionResource extends SecurityBaseResource {
    @GET
    public List<Permission> permissions() {
        return Permission.dao.findBy("deleted_at IS NULL");
    }
}
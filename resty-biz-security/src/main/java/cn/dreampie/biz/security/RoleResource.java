package cn.dreampie.biz.security;

import cn.dreampie.biz.security.model.Permission;
import cn.dreampie.biz.security.model.Role;
import cn.dreampie.common.http.HttpRequest;
import cn.dreampie.common.http.exception.HttpException;
import cn.dreampie.common.http.result.HttpStatus;
import cn.dreampie.orm.transaction.Transaction;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.DELETE;
import cn.dreampie.route.annotation.GET;
import cn.dreampie.route.annotation.POST;

import java.util.Date;
import java.util.List;

/**
 * Created by cengruilin on 2017/5/8.
 */
@API("/roles")
public class RoleResource extends SecurityBaseResource {
    @GET
    public List<Role> roles(HttpRequest request) {
        return Role.dao.findBy(Role.FIELD_DELETED_AT + " IS NULL order by " + Role.FIELD_ID);
    }

    @GET("/:id")
    public Role get(Long id) {
        return Role.dao.findFirstBy(Role.FIELD_ID + "=?", id);
    }

    @POST
    public Role create(String name, String value) {
        if (null == name || name.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "name", "请提供名称");
        }
        if (null == value || value.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "value", "请提供值");
        }
        Role role = new Role();
        role.set(Role.FIELD_NAME, name).set(Role.FIELD_VALUE, value);
        role.save();
        return role;
    }

    @POST("/:id")
    public void update(Long id, String name, String value) {
        if (null == id) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "id", "请提供数据id");
        }
        if (null == name || name.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "name", "请提供名称");
        }
        if (null == value || value.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "value", "请提供值");
        }
        Role.dao.updateColsBy(Role.FIELD_NAME + "," + Role.FIELD_VALUE, Role.FIELD_ID + "=?", name, value, id);
    }

    @DELETE("/:id")
    public void delete(Long id) {
        if (null == id) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "id", "请提供数据id");
        }

        Role role = Role.dao.findById(id);
        if (null == role) {
            throw new HttpException(HttpStatus.NOT_FOUND, "not_found", "角色数据未找到");
        }

        if (role.<Integer>get(Role.FIELD_DELETE_ABLE) == 0) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "system_role_can_not_delete", "系统默认角色不能被删除");
        }

        role.set(Role.FIELD_DELETED_AT, new Date());
        role.update();
    }

    @Transaction
    @POST("/:id/permissions")
    public void updatePermissions(Long id, List<Permission> permissions) {
        if (null == id) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "id", "请提供数据id");
        }
        Role role = new Role();
        role.put(Role.FIELD_ID, id);
        role.put(Role.FIELD_PERMISSIONS, permissions);
        role.updatePermissions();
    }
}

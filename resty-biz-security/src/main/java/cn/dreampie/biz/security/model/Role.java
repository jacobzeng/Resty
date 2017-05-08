package cn.dreampie.biz.security.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cengruilin on 2017/5/8.
 */
@Table(name = "sec_role", cached = true)
public class Role extends Model<Role> {
    public static final Role dao = new Role();
    public static final String FIELD_NAME = "name";
    public static final String FIELD_VALUE = "value";
    public static final String FIELD_ID = "id";
    public static final String FIELD_DELETED_AT = "deleted_at";
    public static final String FIELD_DELETE_ABLE = "delete_able";
    public static final String FIELD_PERMISSIONS = "permissions";

    public List<Permission> getPermissions() {
        List<Permission> permissions = null;
        if (this.get(FIELD_PERMISSIONS) == null && this.get(FIELD_ID) != null) {
            permissions = Permission.dao.findByRole(this.<Long>get(FIELD_ID));
            this.put(FIELD_PERMISSIONS, permissions);
        } else {
            permissions = this.get(FIELD_PERMISSIONS);
        }
        return permissions;
    }

    @JSONField(serialize = false)
    public Set<Long> getPermissionIds() {
        List<Permission> permissions = getPermissions();
        Set<Long> permissionIds = null;
        if (permissions != null) {
            permissionIds = new HashSet<Long>();
            for (Permission permission : permissions) {
                permissionIds.add(permission.<Long>get(Permission.FIELD_ID));
            }
        }
        return permissionIds;
    }

    public void updatePermissions() {
        Long roleId = this.get("id");
        if (roleId != null) {
            Role role = Role.dao.findFirstBy("id=?", roleId);
            Set<Long> oldPermissionIds = role.getPermissionIds();
            Set<Long> newPermissionIds = this.getPermissionIds();

            newPermissionIds.removeAll(oldPermissionIds);
            oldPermissionIds.removeAll(this.getPermissionIds());
            for (Long id : oldPermissionIds) {
                RolePermission.dao.deleteBy("role_id=? AND permission_id=?", roleId, id);
            }

            for (Long id : newPermissionIds) {
                new RolePermission().set("role_id", roleId).set("permission_id", id).save();
            }
            Permission.dao.purgeCache();
            Role.dao.purgeCache();
        }
    }
}

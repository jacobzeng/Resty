package cn.dreampie.biz.security.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;

import java.util.List;

/**
 * Created by cengruilin on 2017/5/8.
 */
@Table(name = "sec_permission", cached = true)
public class Permission extends Model<Permission> {
    public static final String FIELD_ID = "id";
    public static final Permission dao = new Permission();

    public List<Permission> findByRole(Long roleId) {
        String sql = "SELECT permission.id,permission.name,permission.value FROM sec_role_permission role_permission,sec_permission permission WHERE role_permission.permission_id=permission.id AND role_permission.role_id=?";
        return find(sql, roleId);
    }
}

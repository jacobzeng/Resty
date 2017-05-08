package cn.dreampie.biz.security.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;

/**
 * Created by cengruilin on 2017/5/8.
 */
@Table(name = "sec_role_permission", cached = true)
public class RolePermission extends Model<RolePermission> {
    public static final RolePermission dao = new RolePermission();
}

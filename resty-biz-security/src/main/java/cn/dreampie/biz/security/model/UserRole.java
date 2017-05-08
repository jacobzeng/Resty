package cn.dreampie.biz.security.model;

import cn.dreampie.orm.Model;
import cn.dreampie.orm.annotation.Table;

/**
 * Created by cengruilin on 2017/5/8.
 */
@Table(name = "sec_user_role", cached = true)
public class UserRole extends Model<UserRole> {
    public static final UserRole dao = new UserRole();
}

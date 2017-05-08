package cn.dreampie.biz.security;

import cn.dreampie.biz.security.model.Role;
import cn.dreampie.biz.security.model.User;
import cn.dreampie.biz.security.util.QueryUtil;
import cn.dreampie.common.util.Maper;
import cn.dreampie.orm.page.Page;
import cn.dreampie.orm.transaction.Transaction;
import cn.dreampie.route.annotation.*;
import cn.dreampie.security.DefaultPasswordService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cengruilin on 2017/5/8.
 */
@API("/users")
public class UserResource extends SecurityBaseResource {
    @GET
    public Map users(Integer pageNum, Integer pageSize, String username) {
        pageNum = QueryUtil.fixPageNum(pageNum);
        pageSize = QueryUtil.fixPageSize(pageSize);

        String where = User.FIELD_DELETED_AT + " IS NULL ";
        List<Object> params = new ArrayList<>();
        if (null != username && !username.isEmpty()) {
            where += " AND " + User.FIELD_USERNAME + " LIKE ? ";
            params.add("%" + username + "%");
        }
        where += " ORDER BY " + User.FIELD_CREATED_AT;
        long count = User.dao.countBy(where, params.toArray());

        Page<User> data = User.dao.paginateColsBy(pageNum, pageSize, User.FIELD_ID + ",username,email,mobile," + User.FIELD_CREATED_AT + ",updated_at", where, params.toArray());
        return Maper.of("totalCount", count, "data", data.getList(), "pageNum", pageNum, "pageSize", pageSize);
    }

    @GET("/:id")
    public User get(Long id) {
        User user = User.dao.findFirstBy("id=?", id);
        if (user != null) {
            user.remove("password");
        }
        return user;
    }

    @POST
    public boolean save(User user) {
        String password = user.get("password");
        user.set("password", DefaultPasswordService.instance().crypto(password));
//    Role role = user.<Role>get("role");
//    if (role.get("id") == null) {
//      role.save();
//    }
//    role.updatePermissions();
        return user.save();
    }

    @PUT
    @Transaction
    public boolean update(User user) {
        Role role = user.<Role>get("role");
        role.updatePermissions();
        return user.update();
    }


    @DELETE("/:id")
    public Map delete(int id) {
        return Maper.of("success", User.dao.updateColsBy("deleted_at", "id=?", new Date(), id));
    }
}

package cn.dreampie.biz.security.util;

/**
 * Created by cengruilin on 2017/5/8.
 */
public class QueryUtil {
    private final static Integer queryPageMaxSize = 100;

    public static Integer fixPageNum(Integer pageNum) {
        if (null == pageNum || pageNum < 1) {
            return 1;
        } else {
            return pageNum;
        }
    }

    public static Integer fixPageSize(Integer pageSize) {
        if (null == pageSize || pageSize < 1) {
            return 20;
        } else if (pageSize > queryPageMaxSize) {
            return queryPageMaxSize;
        } else {
            return pageSize;
        }
    }
}

package cn.dreampie.event;

import java.io.Serializable;

/**
 * Created by cengruilin on 2017/5/4.
 */
public abstract class Event<T> implements Serializable {
    private T obj;

    public Event(T obj) {
        this.obj = obj;
    }

    public Event() {
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}

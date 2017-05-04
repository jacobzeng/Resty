package cn.dreampie.event;

import com.google.common.eventbus.AsyncEventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cengruilin on 2017/5/4.
 */
public class EventBus {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    private static final com.google.common.eventbus.EventBus eventBus = new AsyncEventBus(executorService);

    public static void register(Object listener) {
        eventBus.register(listener);
    }

    public static void unregister(Object listener) {
        eventBus.unregister(listener);
    }

    public static void dispatch(Event event) {
        eventBus.post(event);
    }
}

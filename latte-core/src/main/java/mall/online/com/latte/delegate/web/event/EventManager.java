package mall.online.com.latte.delegate.web.event;

import java.util.HashMap;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {

    }

    private static class Holder{
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(String name, Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(String action) {
        final Event event = EVENTS.get(action);
//        if (event == null) {
//            return new UndefinedEvent();
//        }
        return new TestEvent();
    }
}

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class TrafficLight {
    protected final int id;
    protected String state;
    protected final Queue<Event> eventQueue;
    protected final ScheduledExecutorService executor;

    public TrafficLight(int id) {
        this.id = id;
        this.eventQueue = new ConcurrentLinkedQueue<>();
        this.executor = Executors.newScheduledThreadPool(1);
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public abstract void processEvent(Event event);

    public void sendEvent(TrafficLight target, String type, Object data) {
        target.receiveEvent(new Event(this.id, type, data));
    }

    public void receiveEvent(Event event) {
        eventQueue.offer(event);
    }

    public void startProcessing() {
        executor.scheduleAtFixedRate(this::processNextEvent, 0, 1, TimeUnit.SECONDS);
    }

    private void processNextEvent() {
        Event event = eventQueue.poll();
        if (event != null) {
            processEvent(event);
            logState(event);
        }
    }

    protected void logState(Event event) {
        System.out.printf("Traffic Light %d | State: %s | Event: %s | Data: %s%n",
                id, state, event.getType(), event.getData());
    }
}

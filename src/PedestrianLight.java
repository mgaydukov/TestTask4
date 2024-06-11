import java.util.concurrent.TimeUnit;

public class PedestrianLight extends TrafficLight {
    private int pedestrianQueueSize;

    public PedestrianLight(int id) {
        super(id);
        this.state = "RED";
        this.pedestrianQueueSize = 0;
    }

    @Override
    public void processEvent(Event event) {
        switch (event.getType()) {
            case "UPDATE_QUEUE":
                this.pedestrianQueueSize = (int) event.getData();
                break;
            case "CHANGE_STATE":
                this.state = (String) event.getData();
                break;
            case "CHECK_QUEUE":
                if (this.pedestrianQueueSize > 5 && this.state.equals("RED")) {
                    this.state = "GREEN";
                    System.out.printf("Pedestrian Light %d turns GREEN%n", id);
                    executor.schedule(() -> {
                        this.state = "RED";
                        System.out.printf("Pedestrian Light %d turns RED%n", id);
                    }, 15, TimeUnit.SECONDS);
                }
                break;
        }
        logQueueSize();
    }

    private void logQueueSize() {
        System.out.printf("Pedestrian Light %d | Queue Size: %d%n", id, pedestrianQueueSize);
    }

    public int getPedestrianQueueSize() {
        return pedestrianQueueSize;
    }
}

import java.util.concurrent.TimeUnit;

public class CarLight extends TrafficLight {
    private int carQueueSize;

    public CarLight(int id) {
        super(id);
        this.state = "RED";
        this.carQueueSize = 0;
    }

    @Override
    public void processEvent(Event event) {
        switch (event.getType()) {
            case "UPDATE_QUEUE":
                this.carQueueSize = (int) event.getData();
                break;
            case "CHANGE_STATE":
                this.state = (String) event.getData();
                break;
            case "CHECK_QUEUE":
                if (this.carQueueSize > 10 && this.state.equals("RED")) {
                    this.state = "GREEN";
                    System.out.printf("Car Light %d turns GREEN%n", id);
                    executor.schedule(() -> {
                        this.state = "YELLOW";
                        System.out.printf("Car Light %d turns YELLOW%n", id);
                        executor.schedule(() -> {
                            this.state = "RED";
                            System.out.printf("Car Light %d turns RED%n", id);
                        }, 5, TimeUnit.SECONDS);
                    }, 20, TimeUnit.SECONDS);
                }
                break;
        }
        logQueueSize();
    }

    private void logQueueSize() {
        System.out.printf("Car Light %d | Queue Size: %d%n", id, carQueueSize);
    }

    public int getCarQueueSize() {
        return carQueueSize;
    }
}

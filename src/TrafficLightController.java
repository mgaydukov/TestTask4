import java.util.ArrayList;
import java.util.List;

public class TrafficLightController {
    private final List<TrafficLight> lights;

    public TrafficLightController() {
        this.lights = new ArrayList<>();
    }

    public void addTrafficLight(TrafficLight light) {
        lights.add(light);
        light.startProcessing();
    }

    public void updateQueues() {
        System.out.println("Updating queues...");
        for (TrafficLight light : lights) {
            // Симуляция обновления очередей
            if (light instanceof PedestrianLight) {
                int newSize = (int) (Math.random() * 10);
                light.sendEvent(light, "UPDATE_QUEUE", newSize);
                light.sendEvent(light, "CHECK_QUEUE", null);
            } else if (light instanceof CarLight) {
                int newSize = (int) (Math.random() * 20);
                light.sendEvent(light, "UPDATE_QUEUE", newSize);
                light.sendEvent(light, "CHECK_QUEUE", null);
            }
        }
    }

    public void synchronizeLights() {
        System.out.println("Synchronizing lights...");
        // Логика синхронизации светофоров
        for (TrafficLight light : lights) {
            if (light instanceof CarLight) {
                for (TrafficLight other : lights) {
                    if (other instanceof PedestrianLight) {
                        if (light.getState().equals("GREEN")) {
                            other.sendEvent(other, "CHANGE_STATE", "RED");
                        }
                    }
                }
            }
        }
    }

    public void run() {
        while (true) {
            updateQueues();
            synchronizeLights();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

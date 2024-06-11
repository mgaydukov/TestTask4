public class Main {
    public static void main(String[] args) {
        TrafficLightController controller = new TrafficLightController();

        // Создание и добавление светофоров
        PedestrianLight pedestrianLight1 = new PedestrianLight(1);
        PedestrianLight pedestrianLight2 = new PedestrianLight(2);
        PedestrianLight pedestrianLight3 = new PedestrianLight(3);
        PedestrianLight pedestrianLight4 = new PedestrianLight(4);
        CarLight carLight1 = new CarLight(1);
        CarLight carLight2 = new CarLight(2);
        CarLight carLight3 = new CarLight(3);
        CarLight carLight4 = new CarLight(4);
        CarLight carLight5 = new CarLight(5);
        CarLight carLight6 = new CarLight(6);
        CarLight carLight7 = new CarLight(7);
        CarLight carLight8 = new CarLight(8);

        controller.addTrafficLight(pedestrianLight1);
        controller.addTrafficLight(pedestrianLight2);
        controller.addTrafficLight(pedestrianLight3);
        controller.addTrafficLight(pedestrianLight4);
        controller.addTrafficLight(carLight1);
        controller.addTrafficLight(carLight2);
        controller.addTrafficLight(carLight3);
        controller.addTrafficLight(carLight4);
        controller.addTrafficLight(carLight5);
        controller.addTrafficLight(carLight6);
        controller.addTrafficLight(carLight7);
        controller.addTrafficLight(carLight8);

        // Запуск контроллера
        controller.run();
    }
}

package store;


import store.controller.ConvenienceController;

public class Application {
    public static void main(String[] args) {
        ConvenienceController controller = new ConvenienceController();
        controller.run();
    }
}

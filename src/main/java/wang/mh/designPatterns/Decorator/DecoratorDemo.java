package wang.mh.designPatterns.Decorator;

public class DecoratorDemo {

    public static void main(String[] args) {

        Service breakfastService = new DoubleRoom();
        breakfastService = new BreakfastService(breakfastService);
        System.out.println("description : " + breakfastService.getDescription());//description : Double Room Breakfast
        System.out.println("price : " + breakfastService.getPrice()); //price : 210

        Service breakfastAndWifiService = new DoubleRoom();
        breakfastAndWifiService = new BreakfastService(breakfastAndWifiService);
        breakfastAndWifiService = new WifiService(breakfastAndWifiService);
        System.out.println("description : " + breakfastAndWifiService.getDescription());//description : Double Room Breakfast Wifi
        System.out.println("price : " + breakfastAndWifiService.getPrice()); //price : 215

    }
}

public class HotDrink extends Drink {

    int temperature;
    int price;

    public HotDrink(String drink, double volume, Boolean sugar, int temperature, int price) {
        super(drink, volume, sugar);
        this.temperature = temperature;
        this.price = price;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "\n\ndrink = " + super.getDrink() + "\nvolume = " + super.getVolume() + "\nsugar = " + super.getSugar() + "\ntemperature = " + temperature + "\nprice = " + price ;
    }
    
}
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shop {

    private final List<Bike> bikes = new ArrayList<>();

    public void addBike(Bike bike) {
        Objects.requireNonNull(bike);
        bikes.add(bike);
    }
    
}

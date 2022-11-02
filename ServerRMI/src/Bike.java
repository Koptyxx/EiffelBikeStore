import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bike {
    private final Integer id;
    private final PersonUGE owner;
    private Optional<PersonUGE> tenant;
    private final List<Integer> marks;

    public Bike(Integer id, PersonUGE owner){
        this.id = id;
        this.owner = owner;
        this.tenant = Optional.empty();
        this.marks = new ArrayList<>();
    }

    public void addMark(Integer mark){
        if(mark < 0 || mark > 5){
            System.out.println("Your mark has to be between 0 and 5 !");
            return;
        }
        marks.add(mark);
    }

    public Double getGlobalMark() {
        return marks.stream().mapToDouble(a -> a).average().orElse(-1.0);
    }

    public Optional<PersonUGE> getTenant() {
        return tenant;
    }

    public void setTenant(PersonUGE tenant) {
        this.tenant = Optional.ofNullable(tenant);
    }

}

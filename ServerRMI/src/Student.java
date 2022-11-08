import java.util.Objects;

public class Student implements PersonUGE{

    private String firstName;

    private String lastName;
    private long id;


    @Override
    public String toString() {
        return "Student " + firstName + " " + lastName;
    }

    @Override
    public void setId(long id) throws RuntimeException {
        this.id = id;
    }

    @Override
    public long getId() throws RuntimeException {
        return id;
    }

    @Override
    public void setFirstName(String firstName) throws RuntimeException {
        Objects.requireNonNull(firstName);
        this.firstName = firstName;
    }

    @Override
    public String getFirstName() throws RuntimeException {
        return firstName;
    }

    @Override
    public void setLastName(String firstName) throws RuntimeException {
        Objects.requireNonNull(lastName);
        this.lastName = lastName;
    }

    @Override
    public String getLastName() throws RuntimeException {
        return lastName;
    }
}

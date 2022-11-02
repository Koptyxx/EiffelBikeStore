public class Employee implements PersonUGE{
    private String firstName;

    private String lastName;

    @Override
    public String toString() {
        return "Employee " + firstName + " " + lastName;
    }
}

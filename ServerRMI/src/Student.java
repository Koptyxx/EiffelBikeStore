public class Student implements PersonUGE{

    private String firstName;

    private String lastName;

    @Override
    public String toString() {
        return "Student " + firstName + " " + lastName;
    }
}

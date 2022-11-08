import java.rmi.Remote;

public interface PersonUGE extends Remote {
    public void setId(long id) throws RuntimeException;
    public long getId() throws RuntimeException;

    public void setFirstName(String firstName) throws RuntimeException;
    public String getFirstName() throws RuntimeException;

    public void setLastName(String firstName) throws RuntimeException;
    public String getLastName() throws RuntimeException;


}

package jewellerystore.com.example.jewellerystore.model;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class Employee {
    private Long id;
    private Name name;
    private String username;
    private String password;

    public Employee() {
    }

    public Employee(Name name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Employee(Long id, Name name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

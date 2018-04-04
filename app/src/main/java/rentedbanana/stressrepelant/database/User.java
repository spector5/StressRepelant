package rentedbanana.stressrepelant.database;

/**
 * Created by Austin on 3/28/2018.
 */

public class User {
    private String username;
    private String password;
    private String name;

    public User (String name, String username, String pass)
    {
        this.username = username;
        this.password = pass;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package rentedbanana.stressrepelant.database;

/**
 * Created by Austin on 3/28/2018.
 */

public class User {
    private String username;
    private String password;
    private String name;
    private String age;
    private String race;
    private String gender;

    public User (String name, String username, String pass, String age, String race, String gender)
    {
        this.username = username;
        this.password = pass;
        this.name = name;
        this.age = age;
        this.race = race;
        this.gender = gender;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}

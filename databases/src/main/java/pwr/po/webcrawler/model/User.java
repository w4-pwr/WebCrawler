package pwr.po.webcrawler.model;

public class User {

    private String username;

    private String name;

    private String surname;

    public User(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

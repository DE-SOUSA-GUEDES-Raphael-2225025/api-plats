package api;
public class User {
    private String login;
    private String name;
    private String surname;
    private String password;
    public User(String id, String name, String password, String surname){
        this.login = id;
        this.name = name;
        this.password = password;
        this.surname = surname;
    }


    // Getters
    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

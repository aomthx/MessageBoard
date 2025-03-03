package entity;

public class Administration {
    private final String username = "root";
    private final String password = "12306";

    public Administration() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean login(String username, String password){
        return (username.equals(this.username) && password.equals(this.password));
    }
}

package org.example;

public class Account {

    private String username;

    private String password;

    private boolean authorization;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.authorization = false;
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

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    public String getInstance(){
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------- ACCOUNT DETAILS ---------------\n");
        sb.append("Username: ").append(username).append("\n");
        sb.append("Password: ").append(password).append("\n");
        sb.append("-------------------------------------------------\n");
        return sb.toString();
    }

}

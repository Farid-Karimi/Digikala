package org.example;


public class Admin extends Account{
    private String email;

    public void getProfileScreen(User user){
        System.out.println("-------------------------");
        System.out.println("username:");
        System.out.println(user.getUsername());
        System.out.println("email:");
        System.out.println(user.getEmail());
        System.out.println("address:");
        System.out.println(user.getAddress());
        System.out.println("phone number:");
        System.out.println(user.getPhoneNumber());
        System.out.println("-------------------------");
    }

    public Admin(String username, String password, String email) {
        super(username, password);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

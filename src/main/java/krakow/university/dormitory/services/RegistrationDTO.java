package krakow.university.dormitory.services;

public class RegistrationDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String name;


    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}


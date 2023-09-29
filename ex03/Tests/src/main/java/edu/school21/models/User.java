package edu.school21.models;

import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private Boolean status;

    public User() {}

    public User(Long id, String login, String password, Boolean status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        User u = (User) obj;
        return Objects.equals(this.id, u.id) && Objects.equals(this.login, u.login)
            && Objects.equals(this.password, u.password) && Objects.equals(this.status, u.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, status);
    }

    @Override
    public String toString() {
        return "User : {" +
                "\nid = " + this.id +
                "\nlogin = " + this.login +
                "\npassword = " + this.password +
                "\nstatus = " + this.status +
                "\n}";
    }
}

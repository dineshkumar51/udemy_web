package com.model;



public class User
{
    private int id;
    private String userName;
    private String password;
    private String fullName;
    private String user_type;

    public User(int id,String userName,String password,String fullName,String user_type) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.user_type = user_type;
    }

    public User(String userName, String password, String fullName,String user_type) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.user_type = user_type;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id;
    }

    public int hashCode() {
        return id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}

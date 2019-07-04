package com.ma.moon.moonapp.Database;

public class ProfileModule {
    private String email,fullname,uid;

    public ProfileModule(String email, String fullname, String uid) {
        this.email = email;
        this.fullname = fullname;
        this.uid = uid;
    }

    public ProfileModule() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

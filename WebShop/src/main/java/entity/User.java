package entity;

import util.IdGenerator;

public class User {
    private String name;
    private String password;
    private String email;
    private int id;

    private User(UserBuilder builder) {
        this.id = IdGenerator.getUserId();
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static class UserBuilder {
        private String name;
        private String password;
        private String email;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }


        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

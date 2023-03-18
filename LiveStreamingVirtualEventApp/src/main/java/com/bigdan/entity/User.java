package com.bigdan.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
    public class User {

        @Id
         @Email(message = "Should be a valid email, please check !")
        private String userEmail;

         @Size(min = 2, max = 20, message = "Cannot be null, minimum length should be 2 and maximum length should be 20." )
        private String userFirstName;

         @Size(min = 2, max = 20, message = "Cannot be null, minimum length should be 2 and maximum length should be 20." )
        private String userLastName;

         @Size(min = 3, max = 20, message = "Cannot be null, minimum length should be 3 and maximum length should be 20." )
        private String company;

         @Size(min = 3, max = 20, message = "Cannot be null, minimum length should be 3 and maximum length should be 20." )
        private String jobposition;

         @Size(min = 5, max = 70, message = "Cannot be null, minimum length should be 5 and maximum length should be 70." )
        private String userPassword;

        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(name = "USER_ROLE",
                joinColumns = {
                        @JoinColumn(name = "USER_ID")
                },
                inverseJoinColumns = {
                        @JoinColumn(name = "ROLE_ID")
                }
        )
        private Set<Role> role;



        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userName) {
            this.userEmail = userName;
        }

        public String getUserFirstName() {
            return userFirstName;
        }

        public void setUserFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
        }

        public String getUserLastName() {
            return userLastName;
        }

        public void setUserLastName(String userLastName) {
            this.userLastName = userLastName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public Set<Role> getRole() {
            return role;
        }

        public void setRole(Set<Role> role) {
            this.role = role;
        }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobposition() {
        return jobposition;
    }

    public void setJobposition(String jobposition) {
        this.jobposition = jobposition;
    }
}



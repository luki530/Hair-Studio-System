package pl.edu.agh.databaseModel;

import pl.edu.agh.persistence.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Persistable {
    private String login;
    private String role;
    private String firstName;
    private String lastName;
    private String eMail;
    private String phoneNumber;
    private String password;

    @Id
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(role, user.role) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(eMail, user.eMail) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(password, user.password);
    }

    @Override
    public String toString() {
        return "\'" + this.login + "\'" + this.firstName + "\'" + this.lastName + "\'" + this.phoneNumber + "\'" + this.eMail + "\'" + this.password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, role, firstName, lastName, eMail, phoneNumber, password);
    }

    @Transient
    private List<String> permissions;

    @Transient
    public List<String> getPermissions() {
        this.refreshPermissions();
        return this.permissions;
    }

    public void refreshPermissions() {
        if (this != null) {
            List<String> permissions1 = new ArrayList<>();
            switch (this.role) {
                case "ADMIN":
                    permissions1.add("removeUser");
                    permissions1.add("addEmployee");
                case "EMPLOYEE":
                    permissions1.add("confirmVisit");
                    permissions1.add("listUsers");
                    permissions1.add("addFreeVisit");
                    permissions1.add("confirmVisit");
                case "CLIENT":
                    permissions1.add("showCalendar");
                    permissions1.add("addVisit");
                    permissions1.add("showMyVisits");
                    permissions1.add("viewVisit");
                    permissions1.add("deleteVisit");
                    break;
            }
            this.permissions = permissions1;
        }
    }
}

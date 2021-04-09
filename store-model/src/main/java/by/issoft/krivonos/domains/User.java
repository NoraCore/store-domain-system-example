package by.issoft.krivonos.domains;

import java.util.Objects;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String mail;
    private final String phoneNumber;

    private String firstName;
    private String lastName;


    public User(UUID id, String mail, String phoneNumber) {
        this.id = id;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public User(UUID id, String mail, String phoneNumber, String firstName, String lastName) {
        this.id = id;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(mail, user.mail);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail);
    }
}

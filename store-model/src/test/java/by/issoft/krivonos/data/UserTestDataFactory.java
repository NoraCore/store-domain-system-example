package by.issoft.krivonos.data;

import by.issoft.krivonos.domains.User;

import java.util.UUID;

public class UserTestDataFactory {
    public static User anyValidUser() {
        return createTestUser("example@mail.ru","375298401636", "Vasya", "Pupkin");
    }

    public static User anyUserByMail(String mail) {
        return createTestUser(mail,"375298401636", "Vasya", "Pupkin");
    }

    public static User createTestUser(String mail, String phoneNumber, String firstName, String lastName) {
        User user = new User(UUID.randomUUID(), mail, phoneNumber, firstName, lastName);
        return user;
    }

    public static User anyUser() {
        return anyValidUser();
    }
}

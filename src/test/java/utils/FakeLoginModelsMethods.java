package utils;

import com.github.javafaker.Faker;

public class FakeLoginModelsMethods {
    Faker faker = new Faker();

    public String getUsername() {
        return faker.name().firstName();
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getPassword() {
        return faker.internet().password();
    }

    public String getPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public int getStatus() {
        return faker.number().numberBetween(1, 100);
    }

    public int getId() {
        return faker.number().numberBetween(1, 100);
    }
}


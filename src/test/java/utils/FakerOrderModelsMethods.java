package utils;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FakerOrderModelsMethods {
    Faker faker = new Faker();
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public int getId() {
        return faker.number().numberBetween(1, 100);
    }

    public int getPetId() {
        return faker.number().numberBetween(1, 100);
    }

    public int getQuantity() {
        return faker.number().numberBetween(1, 10);
    }

    public String getShipDate() {
        return formatter.format(LocalDateTime.now().minusDays(300));
    }

    public String getStatus() {
        return faker.options().option("placed", "approved", "delivered");
    }

    public boolean isComplete() {
        return faker.random().nextBoolean();
    }
}

package awswer.nomap.product;

import java.time.LocalDate;
import java.util.Random;

public class TestProduct {
    public static void main(String[] args) {
        //public Product(String productCode, String productName, double price, int quantity, LocalDate manufactureDate, LocalDate expirationDate)
        //Câu 1
        Product product1 = new Product(
                "P123",
                "Milk",
                100,
                100,
                LocalDate.of(2024,3,4),
                LocalDate.of(2026,3,4)
        );
        System.out.println(product1);

        //Câu 2
        Product product2 = new Product();
        System.out.println(product2);

        //Câu 3
        System.out.println(product1.getProductCode());

        //Câu 4
        System.out.println(product1.getProductName());

        //Câu 5
        System.out.println(product1.getPrice());

        //Câu 6
        System.out.println(product1.getQuantity());

        //Câu 7
        System.out.println(product1.getManufactureDate());

        //Câu 8
        System.out.println(product1.getExpirationDate());

        //Câu 9
        System.out.println(product1.getTotalValue());

        //Câu 10
        Product product10 = new Product(
                "L123",
                "Milk",
                100,
                100,
                LocalDate.of(2024,3,4),
                LocalDate.of(2026,3,4)
        );
        System.out.println(product10.getProductCode());

        //Câu 11
        Random random = new Random();
        Product product11 = new Product(
                "L123",
                "Milk",
                random.nextDouble() * - 1,
                100,
                LocalDate.of(2024,3,4),
                LocalDate.of(2026,3,4)
        );
        System.out.println(product11.getPrice());

        //Câu 12
        Product product12 = new Product(
                "L123",
                "Milk",
                100,
                random.nextInt(1),
                LocalDate.of(2024,3,4),
                LocalDate.of(2026,3,4)
        );
        System.out.println(product12.getQuantity());

        //Câu 13
        LocalDate expirationDate = LocalDate.of(2024, 3, 4);
        LocalDate randomDate = random.nextBoolean() ? expirationDate : expirationDate.minusDays(random.nextInt(1, 5));

        Product product13 = new Product(
                "L123",
                "Milk",
                100,
                random.nextInt(1),
                randomDate,
                LocalDate.of(2024, 3, 4)
        );
        System.out.println(product13.getExpirationDate());
    }
}

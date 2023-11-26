import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {
    Product item1 = new Product(1, "Огурец", 5);
    Product item2 = new Product(2, "Томат", 10);
    Product item3 = new Product(3, "Лук", 15);
    Product item4 = new Product(4, "Баклажан", 20);
    Product item5 = new Product(5, "Перец", 25);

    @Test
    public void testRemove() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);

        repo.remove(2);

        Product[] expected = {item1, item3, item4, item5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testAddAlreadyExists() {
        ShopRepository repo = new ShopRepository();

        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item2);
        });
    }

    @Test
    public void testRemoveNotFound() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(-10);
        });
    }

    @Test
    public void testAdd() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);

        Product[] expected = {item1, item2, item3};
        Product[] actual = repo.add(item3);
        Assertions.assertArrayEquals(expected, actual);
    }
}
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ManagerTest {
    static Repository repo = new Repository();
    static Manager manager = new Manager(repo);
    private static final String LED = "Пулково";
    private static final String KZN = "Казань";
    private static final String SVO = "Шереметьево";
    private static final String VKO = "Внуково";
    private static final String LA = "LA";

    static Ticket first = new Ticket(1, 6287, LED, KZN, 133);
    static Ticket second = new Ticket(2, 1299, SVO, KZN, 95);
    static Ticket third = new Ticket(3, 3899, SVO, KZN, 95);
    static Ticket fourth = new Ticket(4, 110, LA, KZN, 1200);


    @BeforeAll
    static void setUp() {
        repo.addProduct(first);
        repo.addProduct(second);
        repo.addProduct(third);
        repo.addProduct(fourth);
    }

    @Test
    public void shouldFindAll() {
        Ticket[] expected = {fourth};
        assertArrayEquals(expected, manager.searchBy(LA, KZN));
    }

    @Test
    public void searchBy() {
        Repository repo = new Repository();
        Manager manager = new Manager(repo);

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Ticket[] expected = { first };
        Ticket[] actual = manager.searchBy(LED, KZN);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNoTicket() {
        Repository repo = new Repository();
        Manager manager = new Manager(repo);

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy(LED, LA);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchTwoTicket() {
        Repository repo = new Repository();
        Manager manager = new Manager(repo);

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Ticket[] expected = { second, third };
        Ticket[] actual = manager.searchBy(SVO, KZN);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldShowAllOffers() {
        Ticket[] expected = {fourth, second, third, first};
        assertArrayEquals(expected, manager.findAllOffers());
    }

    @Test
    public void shouldSortByPrice() {
        Ticket[] expected = {fourth, second, third, first};
        Ticket[] actual = {fourth, second, third, first};
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }
}

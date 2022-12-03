import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RepositoryTest {
        static Repository repo = new Repository();
        static Manager manager = new Manager(repo);

        private static final String LED = "Пулково";
        private static final String KZN = "Казань";
        private static final String SVO = "Шереметьево";
        private static final String VKO = "Внуково";
        private static final String KGD = "Калининград";

        static Ticket first = new Ticket(1, 2287, LED, KZN, 133);
        static Ticket second = new Ticket(2, 1299, SVO, KZN, 95);
        static Ticket third = new Ticket(3, 2199, VKO, KZN, 95);
        static Ticket fourth = new Ticket(4, 7099, KGD, KZN, 149);


        @BeforeAll
        static void setUp() {
            repo.addProduct(first);
            repo.addProduct(second);
            repo.addProduct(third);
        }

        @Test
        public void shouldFindAll() {
            Ticket[] expected = { first, second, third };
            Ticket[] actual = repo.findAll();
            Assertions.assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldSave() {
            repo.addProduct(fourth);
            Ticket[] expected = { first, second, third, fourth };
            Ticket[] actual = repo.getAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldRemoveById() {
            repo.addProduct(fourth);
            repo.removeById(4);
            Ticket[] expected = { first, second, third };
            Ticket[] actual = repo.getAll();
            assertArrayEquals(expected, actual);
        }
}

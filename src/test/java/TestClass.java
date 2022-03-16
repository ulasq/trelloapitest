import Base.BeforeTests;
import org.junit.Test;

import java.util.Random;

public class TestClass extends BeforeTests {
    pages pages = new pages();
    Random rnd = new Random();

    @Test
    public void Tests1() {
        pages.boardCreate("FirstBoardCreate");
        pages.cardCreate("First");
        pages.cardCreate("Second");
        pages.cardUpdate("CardUpdate", rnd.nextInt(1));
        pages.cardDelete(0);
        pages.cardDelete(1);
        pages.boardDelete();

    }
}

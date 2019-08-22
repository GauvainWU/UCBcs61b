package hw2;
import org.junit.Test;
import static org.junit.Assert.*;

public class PercolationStatsTest {
    @Test
    public void statsTest() {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20, 10, pf);
        assertNotEquals(0, ps.mean());
    }
}

import org.junit.Test;
import static org.junit.Assert.*;

public class ULLMapTest {

    @Test
    public void removeTest() {
        ULLMap<String, Integer> test = new ULLMap<>();
        test.put("aaaa", 1);
        test.put("bbbb", 2);
        test.put("cccc", 3);
        assertEquals((int) test.remove("bbbb"), 2);
        test.remove("aaaa");
        test.remove("dddd");
        test.remove("cccc");
    }
}

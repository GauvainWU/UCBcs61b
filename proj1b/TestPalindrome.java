import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testNullForToDeque() {
        assertEquals(null, palindrome.wordToDeque(null));
    }

    @Test
    public void testIsPalindrome() {
        CharacterComparator cc = new OffByOne();
        OffByN obn = new OffByN(0);
        assertFalse(palindrome.isPalindrome("asa", cc));
        assertTrue(palindrome.isPalindrome("acdb", cc));
        assertTrue(palindrome.isPalindrome("asa", obn));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("asoeijf"));
        assertTrue(palindrome.isPalindrome("asa"));
        assertTrue(palindrome.isPalindrome("dccd"));
    }
}
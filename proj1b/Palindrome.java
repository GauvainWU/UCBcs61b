public class Palindrome {

    /**
     * Method that conveys a word to a deque of characters.
     */
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque<Character> result = new ArrayDeque<>();
        for (Character c : word.toCharArray()) {
            result.addLast(c);
        }
        return result;
    }

    /**
     * Method used to determine if a given word is palindrome
     * by comparing itself and its reversed version
     *
     * @param word the word to be checked
     * @return True if word is palindrome, false otherwise
     */
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        ArrayDeque<Character> a = (ArrayDeque<Character>) wordToDeque(word);
        ArrayDeque<Character> b = new ArrayDeque<>();
        for (Character c : word.toCharArray()) {
            b.addFirst(c);
        }
        return isEqual(a, b);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        ArrayDeque<Character> a = (ArrayDeque<Character>) wordToDeque(word);
        ArrayDeque<Character> b = new ArrayDeque<>();
        for (Character c : word.toCharArray()) {
            b.addFirst(c);
        }
        return isEqual(a, b, cc);
    }

    private boolean isEqual(Deque<Character> a, Deque<Character> b) {
        if (a.isEmpty()) {
            return true;
        } else if (!a.removeFirst().equals(b.removeFirst()) ) {
            return false;
        } else {
            return isEqual(a, b);
        }
    }

    private boolean isEqual(Deque<Character> a, Deque<Character> b, CharacterComparator cc) {
        if (a.size() % 2 == 1){
            for (int i = 0; i < a.size() && i != (a.size() - 1) / 2; ++i) {
                if (!cc.equalChars(a.removeFirst(), b.removeFirst())) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < a.size(); ++i) {
                if (!cc.equalChars(a.removeFirst(), b.removeFirst())) {
                    return false;
                }
            }
        }
        return true;
    }
}

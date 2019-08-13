public class OffByN implements CharacterComparator{
    private int offset;
    OffByN(int N){
        offset = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        return Math.abs(x-y) == offset;
    }
}

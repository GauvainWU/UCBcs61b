public class OddFilter implements Predicate<Integer> {

    @Override
    public boolean test(Integer x){
        return x % 2 == 1;
    }
}

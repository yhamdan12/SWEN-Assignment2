package pasur;

public class TenDiamondsRule implements ScoreRule{
    private static final int TEN_DIAMONDS_SCORE = 3;
    public int getScore() {
        return TEN_DIAMONDS_SCORE;
    }
}

package pasur;

import java.util.ArrayList;

public class CompositeRule implements ScoreRule{
    private ArrayList<ScoreRule> rules = new ArrayList<>();

    public void add(ScoreRule rule){
        rules.add(rule);
    }

    public int getScore(){
        int totalScore = 0;
        for (ScoreRule rule : rules) {
            totalScore += rule.getScore();
        }
        return totalScore;
    }
}

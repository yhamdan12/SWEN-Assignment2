package pasur;

import java.util.ArrayList;

public class CompositeRule implements ScoreRule{
    /** ArrayList of all relevant rules */
    private ArrayList<ScoreRule> rules = new ArrayList<>();

    public void add(ScoreRule rule){
        rules.add(rule);
    }

    /** Computes the cumulative score based on all relevant rules */
    public int getScore(){
        int totalScore = 0;
        for (ScoreRule rule : rules) {
            totalScore += rule.getScore();
        }
        return totalScore;
    }
}

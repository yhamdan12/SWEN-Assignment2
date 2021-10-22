package pasur;
import ch.aplu.jcardgame.*; // Added

import java.util.ArrayList;

public class RuleFactory {
    private static RuleFactory instance;
    private static final int MAJORITY_CLUBS = 7;

    public static synchronized RuleFactory getInstance(){
        if (instance == null){
            instance = new RuleFactory();
        }
        return instance;
    }

    /** Create the relevant composite rule, based on the players cards*/
    public ScoreRule getRule(Hand pickedCards, Hand surs){
        CompositeRule compositeRule = new CompositeRule();

        // Combine all the player's cards (picked cards and surs)
        // into one list for easy iteration
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(pickedCards.getCardList());
        allCards.addAll(surs.getCardList());

        int totalClubs = 0;

        // Check each card against the rules and add relevant rules
        // to the composite rule
        for(Card card : allCards){
            if(card.getRank() == Rank.ACE){
                compositeRule.add(new AceRule());
            }
            if(card.getRank() == Rank.JACK){
                compositeRule.add(new JackRule());
            }
            if(card.getRank() == Rank.TEN && card.getSuit() == Suit.DIAMONDS){
                compositeRule.add(new TenDiamondsRule());
            }
            if(card.getSuit() == Suit.CLUBS){
                totalClubs += 1;
                if(card.getRank() == Rank.TWO){
                    compositeRule.add(new TwoClubsRule());
                }
            }
        }
        if(surs.getNumberOfCards() > 0){
            for(int i = 0; i<surs.getNumberOfCards(); i++){
                compositeRule.add(new SurRule());
            }
        }
        if (totalClubs >= MAJORITY_CLUBS){
            compositeRule.add(new MostClubsRule());
        }

        return compositeRule;
    }
}

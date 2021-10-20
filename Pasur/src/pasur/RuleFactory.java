package pasur;
import ch.aplu.jcardgame.*; // Added

import java.util.ArrayList;

public class RuleFactory {
    private static RuleFactory instance; //= null?

    //Lazy Initialisation ?
    public static synchronized RuleFactory getInstance(){
        if (instance == null){
            instance = new RuleFactory();
        }
        return instance;
    }

    public ScoreRule getRule(Hand pickedCards, Hand surs){
        CompositeRule compositeRule = new CompositeRule();
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(pickedCards.getCardList());
        allCards.addAll(surs.getCardList());
        int totalClubs = 0;
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
        // MAGIC NO.
        if (totalClubs >= 7){
            compositeRule.add(new MostClubsRule());
        }
        return compositeRule;
    }
}

package pasur;
import ch.aplu.jcardgame.Card;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LogController {
    private static String log;
    private static LogController logController = new LogController();
    private LogController(){};

    public static LogController getInstance() {
        return logController;
    }
    //This method instantiates the logger using FileHandler and Logger
    public static void logAll(){
        try (PrintWriter pw =
                     new PrintWriter(new FileWriter("pasur.log"))) {

            pw.format(log);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This logs the initial settings of the game
    public static void gameSettings(int seed, boolean animate, ArrayList<String> players ){
        log += String.format("#Seed %d\n", seed);
        log += String.format("#Animate %s\n", Boolean.toString(animate));
        for(int i = 0; i<players.size();i++){
            log += String.format("#Player %d: %s\n", i, players.get(i));
        }
    }

    //logString inputs any string into the logger and is designed for
    //general statements such as "game starts..." and "round 1 of the game starts"
    public static void logString(String statement){
        log += String.format("%s\n", statement);
    }
    public static String logScores(Player[] players){
        String scoreString = "";
        for (int i = 0; i < players.length; i++)
        {
            if(i != 0)
                scoreString += "        ";

            Player player = players[i];
            scoreString += player.toString() + " = " + player.getScore() + " (" + player.getSurs().getNumberOfCards() + " Surs)";
        }
        log += String.format("Total Running Scores: %s\n", scoreString);
        return scoreString;
    }

    public static void logHand(Player player){
        log += String.format("%s hand %s\n", player.toString(), player.getHand().getCardList().toString());
    }
    public static void logCards(Player player, String cards){
        log += String.format("%s picks %s\n", player.toString(), cards);
    }

}


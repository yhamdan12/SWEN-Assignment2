package pasur;
import ch.aplu.jcardgame.Card;

import java.io.IOException;
import java.util.logging.*;
import java.util.*;

public class LogController {
    private static LogController logController = new LogController();
    private static Logger centralLogger;
    private LogController(){};

    public static LogController getInstance() {
        return logController;
    }
    //This method instantiates the logger using FileHandler and Logger
    public static void setupLogger() throws IOException {
        Logger logger = Logger.getLogger(LogController.class.getName());
        FileHandler handler = new FileHandler("pasur.log", true);
        logger.addHandler(handler);
        centralLogger = logger;
    }

    //This logs the initial settings of the game
    public static void gameSettings(int seed, boolean animate, ArrayList<String> players ){
        centralLogger.info("#Seed " + Integer.toString(seed));
        centralLogger.info("#Animate: " + Boolean.toString(animate));
        for(int i = 0; i<players.size();i++){
            centralLogger.info("#Player" + Integer.toString(i)+": " + players.get(i));
        }
    }

    //logString inputs any string into the logger and is designed for
    //general statements such as "game starts..." and "round 1 of the game starts"
    public static void logString(String statement){
        centralLogger.info(statement);
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
        centralLogger.info("Total Running Scores: " + scoreString);
        return scoreString;
    }

    public static void logHand(Player player){
        centralLogger.info(player.toString() + " hand " + player.getHand().getCardList().toString());
    }
    public static void logCards(Player player, String cards){
        centralLogger.info(player.toString() + " picks " + cards);
    }

}


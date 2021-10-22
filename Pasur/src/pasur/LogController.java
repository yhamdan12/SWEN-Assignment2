package pasur;
import ch.aplu.jcardgame.Card;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LogController {
    //Set up the string that will be appended to with logs before being written onto file
    private static String log = "";
    private static LogController logController = new LogController();
    private LogController(){};

    //Method instantiates the singleton
    public static LogController getInstance() {
        return logController;
    }

    //This method writes the log string to the pasur.log file
    public static void logAll(){
        try (PrintWriter pw =
                     new PrintWriter(new FileWriter("pasur.log"))) {

            pw.format(log);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This logs the initial settings of the game, allowing for a variable number of players
    public static void gameSettings(int seed, boolean animate, ArrayList<String> players ){
        log += String.format("#Seed: %d\n", seed);
        log += String.format("#Animate: %s\n", Boolean.toString(animate));
        for(int i = 0; i<players.size();i++){
            log += String.format("#Player%d: %s\n", i, players.get(i));
        }
    }

    //logScores iterates through all the players and prints out their score in that round
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

    //Log the start of the game
    public static void logGameStart(){
        log += String.format("Game starts...\n");
    }
    //Log the end of the game
    public static void logGameEnd(){
        log += String.format("Game ends...\n");
    }
    //Log a round starting
    public static void logRoundStart(int round){
        log += String.format("Round %d of the game starts...\n",round);
    }
    //Log a round ending
    public static void logRoundEnd(int round){
        log += String.format("Round %d of the game ends...\n",round);
    }
    //Log the winning text
    public static void logWinningText(String winningText){
        log += winningText;
    }

    //Log a player playing a card
    public static void logPlayersPlay(String player, String playedCard){
        log += String.format("%s plays %s\n",player, playedCard);
    }

    //Log dealing out to players
    public static void logDealingToPlayers(){
        log += String.format("Dealing out to players...\n");
    }

    //Log dealing out to pool
    public static void logDealingToPool(){
        log += String.format("Dealing out to pool...\n");
    }

    //Log a sur being scored
    public static void logSurScore(Player player){
        log += String.format("%s scores a sur\n", player.toString());
    }
    //Log a player picking a card
    public static void logPlayerPickCard(Player lastPlayerWhoPickedAcard, String poolCards){
        log += String.format("%s picks %s at the end of this round of game\n", lastPlayerWhoPickedAcard, poolCards);
    }

    //Log the current hand of a player
    public static void logHand(Player player, String hand){
        log += String.format("%s hand: %s\n", player.toString(), hand);
    }

    //Log the current cards played by a player
    public static void logCards(Player player, String cards){
        log += String.format("%s picks %s\n", player.toString(), cards);
    }

    //Log the current pool
    public static void logPool(String pool){
        log += String.format("Pool: %s\n", pool);
    }


}


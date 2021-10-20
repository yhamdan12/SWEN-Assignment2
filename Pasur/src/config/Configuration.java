package config;

import pasur.LogController;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.*;

public class Configuration
{
    private static final String SEED_KEY = "Seed";
    private static final String ANIMATE_KEY = "Animate";
    private static final String PLAYER0_KEY = "Player0";
    private static final String PLAYER1_KEY = "Player1";

    private static Configuration configuration = null;

    private int seed;
    private boolean animate;
    private String player0class;
    private String player1class;

    public static Configuration getInstance()
    {
        if(configuration == null)
        {
            configuration = new Configuration();

            try {
                configuration.setUp();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return configuration;
    }

    private void setUp() throws IOException 
    {
        // Default properties

        // Read properties
        Properties properties = new Properties();
        FileReader inStream = null;
        try {
            inStream = new FileReader("pasur.properties");
            properties.load(inStream);
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }
        /*
        Setting up an array list to store the player classes to allow
        for future extension for more players
         */

        ArrayList<String> playerClasses = new ArrayList<String>();
        // Seed
        seed = Integer.parseInt(properties.getProperty(SEED_KEY));
        // Animate
        animate = Boolean.parseBoolean(properties.getProperty(ANIMATE_KEY));

        // Player0
        player0class = properties.getProperty(PLAYER0_KEY);
        // Player1
        player1class = properties.getProperty(PLAYER1_KEY);
        // Adding player classes to the arraylist
        playerClasses.add(player0class);
        playerClasses.add(player1class);

        LogController.getInstance().gameSettings(seed, animate,playerClasses);

    }

    public int getSeed()
    {
        return seed;
    }

    public boolean isAnimate()
    {
        return animate;
    }

    public String getPlayer0class()
    {
        return player0class;
    }

    public String getPlayer1class()
    {
        return player1class;
    }
}

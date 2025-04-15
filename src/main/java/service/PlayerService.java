package service;

import exception.DuplicateSymbolException;
import model.Bot;
import model.Player;
import model.constant.BotDifficultyLevel;
import model.constant.PlayerType;

import java.util.HashSet;

public class PlayerService {
    private static int counter = 1; // To Generate id for each player (both bot and human)
    private HashSet<Character> symbolSet; // To make sure that each player have a unique symbol

    public PlayerService() {
        this.symbolSet = new HashSet<>();
    }

    public Player createPlayer(String name, char symbol) {
        if(symbolSet.contains(symbol)) {
            throw new DuplicateSymbolException("The symbol chosen (" + symbol +") already exists.");
        } else symbolSet.add(symbol);

        return new Player(
                counter++,
                name,
                symbol,
                PlayerType.HUMAN
        );
    }

    public Bot createBot(String name, char symbol) {
        if(symbolSet.contains(symbol)) {
            throw new DuplicateSymbolException("The symbol chosen (" + symbol +") already exists.");
        } else symbolSet.add(symbol);

        return new Bot(
                counter++,
                name,
                symbol,
                PlayerType.BOT,
                BotDifficultyLevel.EASY
        );
    }


    /*
    Idea: Instead of having two separate methods we can use a parameter for player type which we are using for creating player from to reduce redundant code
    Solution : Bot creation strategy can vary over time hence separate methods would be ideal
               (Like user no longer chooses bot's name and symbol etc .....)
               Also using same method will also violate SRP.
     */
}

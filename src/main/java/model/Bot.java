package model;

import model.constant.BotDifficultyLevel;
import model.constant.PlayerType;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    // Constructors

    public Bot(int id, String name, char symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }
}

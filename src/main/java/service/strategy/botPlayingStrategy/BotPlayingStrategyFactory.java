package service.strategy.botPlayingStrategy;

import model.constant.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        // TODO: Update this with switch case
        return switch (botDifficultyLevel) {
            case EASY -> new EasyBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
            default ->  throw new IllegalArgumentException("Unexpected value: " + botDifficultyLevel);
        };

        /*
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)) return new EasyBotPlayingStrategy();
        else if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)) return new MediumBotPlayingStrategy();
        else if(botDifficultyLevel.equals(BotDifficultyLevel.HARD)) return new HardBotPlayingStrategy();
        else return null;
        */

    }
}

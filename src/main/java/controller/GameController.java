package controller;

import model.*;
import model.constant.PlayerType;
import service.GameService;
import service.PlayerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameController {

    private Scanner sc;
    private PlayerService playerService;
    private GameService gameService;
    // Constructor

    public GameController(PlayerService playerService, GameService gameService) {
        this.sc = new Scanner(System.in);
        this.playerService = playerService;
        this.gameService = gameService;
    }

    // Methods

    public List<Player> generatePlayersList(int playerCount) {
        System.out.println("Please Enter 1 for Bot and 0 for Human pLayer");
        int botCheck = sc.nextInt();
        List<Player> players = new ArrayList<>();

        if(botCheck == 1) {
            // TODO: Take User input for bot Difficulty level and create a bot accordingly
            // TODO: Take User input for bot Name and Symbol

            Bot bot = playerService.createBot("Bot", '$'); // Bot creation
            players.add(bot);
            playerCount--; // Decrease the player count since bot contributes to one of the player.
        }

        for(int i = 0; i < playerCount; i++) {
            System.out.println("Please Enter name for player: ");
            String playerName = sc.nextLine();
            System.out.println("Please Enter symbol for player: ");
            char playerSymbol = sc.nextLine().charAt(0);
            Player player = playerService.createPlayer(playerName, playerSymbol);
            players.add(player);
        }

        Collections.shuffle(players); // To randomize the order of players turn.
        return players;
    }

    public Move createMove(Player player, Game game) {
        if(player.getPlayerType() == PlayerType.HUMAN) {
            System.out.println("Enter row to make a move");
            int row = sc.nextInt();
            System.out.println("Enter column to make a move");
            int column = sc.nextInt();
            // TODO: Validate the move before proceeding
            // i.e. whether the user's input is with in the range of board (row: 0 -> size-1, column: 0 -> size-1)
            return gameService.executeMove(player, game, row, column);

        } else {
            // Bot Playing strategy method will be called here.
            return gameService.executeMove(player, game);
        }
    }

    public Player checkWinner(Board board, Move move) {return null;}

    public Game undo(int noOfMoves, Game game) {return null;}

    public Game startGame(Game game) {return null;}

    public void replayGame(Game game) {}


}

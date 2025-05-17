import controller.GameController;
import exception.DuplicateSymbolException;
import exception.EmptyMovesException;
import exception.GameDrawnException;
import exception.InvalidCellException;
import model.Game;
import model.Move;
import model.Player;
import model.constant.GameState;
import model.constant.PlayerType;
import service.BoardService;
import service.GameService;
import service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to TIC-TAC-TOE Game");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the dimension of the board to begin the game");
        int dimension = sc.nextInt();
        System.out.println("You have chosen to play game on 3X3 board");
        GameService gameService = new GameService(dimension);
        PlayerService playerService = new PlayerService();
        BoardService boardService = new BoardService();
        GameController gameController = new GameController(playerService, gameService, boardService);
        List<Player> players = gameController.generatePlayersList(dimension - 1);

        Game game = gameService.createGame(players, dimension);
        game = gameService.startGame(game);
        int moveIndex = 0;

        while(true) {
            try {
                Player currentPlayer = game.getPlayers().get(moveIndex);
                System.out.println("Player to make a move: " +currentPlayer.getName());
                Move currentMove = gameController.createMove(currentPlayer, game);
                gameController.displayBoard(game);
                // Check winner after every move
                GameState gameState = gameController.checkWinner(game, currentMove);
                if(gameState.equals(GameState.WINNER)) {
                    game.setWinner(currentPlayer);
                    System.out.println("Game Won");
                    System.out.println("Congratulations to the Winner "+currentPlayer.getName());
                    System.out.println("Game Ends Here");
                    break;
                }
                // Check and Ask for UNDO
                if(currentPlayer.getPlayerType().equals(PlayerType.HUMAN) && !currentPlayer.isHasUsedUndo()) {
                    System.out.println("Please enter number of steps to UNDO if you want to UNDO else enter 0 ");
                    int undoCount = sc.nextInt();
                    if(undoCount > 0) {
                        gameController.undo(undoCount, game);
                        currentPlayer.setHasUsedUndo(true);
                        System.out.println("UNDO chosen, Updated Board");
                        gameController.displayBoard(game);
                        // Adjust moveIndex correctly
                        moveIndex = (moveIndex - undoCount) % (dimension - 1);
                        if (moveIndex < 0) {
                            moveIndex += (dimension - 1);
                        }
                    } else System.out.println("UNDO not chosen, Moving forward");
                }
            } catch (GameDrawnException ex) {
                System.out.println("Game has drawn, No more Winners");
                System.out.println("Thanks for playing");
                System.out.println("Game Ends Here");
                break;
            } catch (InvalidCellException ex) {
                System.out.println("Player has chosen a wrong cell, Please choose another cell");
                continue;
            } catch (EmptyMovesException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
            moveIndex = (moveIndex + 1) % (dimension - 1); // Resets moveIndex to O after all the players in the game played their turn
        }
    }
}

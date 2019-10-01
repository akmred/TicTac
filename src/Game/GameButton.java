package Game;

import javax.swing.*;

public class GameButton extends JButton {
    private int buttonIndex;
    private GameBoard board;
    private ClassCheckWIn winGame;

    public GameButton(int gameButtonIndex, GameBoard currentGameBoard){

        buttonIndex = gameButtonIndex;
        board = currentGameBoard;
        winGame = new ClassCheckWIn(board.getDimension(), board);

        int rowNum = buttonIndex / GameBoard.dimension;
        int cellNum = buttonIndex % GameBoard.dimension;

        setSize(GameBoard.cellSize - 5, GameBoard.cellSize - 5);
        addActionListener(new GameActionListener(rowNum, cellNum, this, winGame));
    }

    public GameBoard getBoard()
    { return board; }
}

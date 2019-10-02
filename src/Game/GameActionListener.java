package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;
    private ClassCheckWIn winGame;
    private ClassAI ai;

    public GameActionListener(int row, int cell, GameButton gButton, ClassCheckWIn winGame, ClassAI ai){
        this.row = row;
        this.cell = cell;
        this.button = gButton;
        this.winGame = winGame;
        this.ai = ai;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        GameBoard board = button.getBoard();

        if (board.isTurnable(row, cell)){

            updateByPlayersData(board);

            if (board.isFull()){

                board.getGame().showMessage("Ничья!");
                board.emptyField();

            }
            else {
                ai.updateByAiData(board);
            }

        }
        else {
            board.getGame().showMessage("Некорректный ход!");
        }
    }

    /**
     * Ход человека
     * @param board GameBoard - ссвлка на игровое поле
     * */
    private void updateByPlayersData(GameBoard board){

        // Обновить матрицу игры
        board.updateGameField(row, cell);

        // Обновить содержимое кнопки
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSing()));

        if(winGame.checkWin()){
            button.getBoard().getGame().showMessage("Вы выиграли");
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        }

    }


}

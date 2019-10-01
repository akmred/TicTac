package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton gButton){
        this.row = row;
        this.cell = cell;
        this.button = gButton;
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
                updateByAiData(board);
            }

        }
        else {
            board.getGame().showMessage("Некорректный ход!");
        }
    }

    /**
     * Ход компьютера
     * @param board GameBoard - ссылка на игровое поле
     * */
    private void updateByAiData(GameBoard board){
        // Генерация координат хода компьютера
        int x, y;
        Random rnd = new Random();

        do {
            x = rnd.nextInt(GameBoard.dimension);
            y = rnd.nextInt(GameBoard.dimension);
        }
        while (!board.isTurnable(x,y));

        // Обновить матрицу игры
        board.updateGameField(x, y);

        // Обновить  содержимое кнопки
        int cellIndex = GameBoard.dimension*x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSing()));

        // Проверить победу
        if (board.checkWin()){
            button.getBoard().getGame().showMessage("Компьютер выиграл!");
            board.emptyField();
        }
        else {
            // Передать ход
            board.getGame().passTurn();
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

        if(board.checkWin()){
            button.getBoard().getGame().showMessage("Вы выиграли");
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        }

    }


}

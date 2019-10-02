package Game;

import java.util.Random;

public class ClassAI {
    private ClassCheckWIn winGame;
    private GameButton button;

    public ClassAI(ClassCheckWIn winGame, GameButton button){
        this.winGame = winGame;
        this.button = button;

    }

    /**
     * Ход компьютера
     * @param board GameBoard - ссылка на игровое поле
     * */
    public void updateByAiData(GameBoard board){
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
        if (winGame.checkWin()){
            button.getBoard().getGame().showMessage("Компьютер выиграл!");
            board.emptyField();
        }
        else {
            // Передать ход
            board.getGame().passTurn();
        }

    }


}

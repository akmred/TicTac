package Game;

import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLMessage;

public class ClassCheckWIn {
    private int dimension;
    private GameBoard board;

    public ClassCheckWIn(int dimension, GameBoard board){
        this.dimension = dimension;
        this.board = board;
    }

    /**
     * Проверка победы по столбцам и линиям
     * @return флаг победы
     * */
    boolean checkWin(){
        boolean result = false;
        char playerSymbol = board.getGame().getCurrentPlayer().getPlayerSing();

        if(checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol)){
            result = true;
        }

        return result;
    }

    /**
     * Проверка победы по столбцам и линиям
     * @return флаг победы
     * */
    private boolean checkWinLines(char playerSymbol){
        boolean cols, rows, result;

        result = false;

        for (int col=0; col < dimension; col ++){

            cols = true;
            rows = true;
            for (int row = 0; row < dimension; row++){

                cols &= (board.getGameField()[col][row] == playerSymbol);
                rows &= (board.getGameField()[row][col] == playerSymbol);

            }

            // Это условие после каждой проверки колонки и столбца
            // позволяет остановить дальнейшее выполнение, без проверки
            // всех остальных столбцов и строк
            if (cols || rows){
                result = true;
                break;
            }

            if(result){
                break;
            }

        }

        return result;
    }

    /**
     * Проверка выигрышных комбинаций на диагоналях
     * @return флаг победы
     * */
    private boolean checkWinDiagonals(char playerSymbol){

        boolean leftRight, rightLeft, result;
        leftRight = true;
        rightLeft = true;
        result = false;

        for (int i = 0; i < dimension; i ++){
            leftRight &= (board.getGameField()[i][i] == playerSymbol); // Оптимизация кода, чтобы не было через if
            rightLeft &= (board.getGameField()[dimension - i - 1][i] == playerSymbol);
        }

        if(leftRight || rightLeft){
            result = true;
        }

        return result;
    }

}

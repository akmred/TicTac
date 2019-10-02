package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameBoard extends JFrame {
    static int dimension = 3;           // Размерность
    static int cellSize = 150;          // Размер одной ячейки
    private char[][] gameField;         // Матрица игры
    private GameButton[] gameButtons;   // Массив кнопок

    private Game game;                  // Ссылка на игру

    static char nullSymbol = '\u0000';

    public GameBoard(Game currentGame){
        this.game = currentGame;
        initField();
    }

    /**
     * Получаем метод dimension
     * */
    public int getDimension(){

        return this.dimension;
    }

    /**
     * Метод возвращает матрицу игры
     * @return  gameField
     * */
    public char[][] getGameField(){
        return gameField;
    }

    /**
     * Метод инициализации и отрисовки игрового поля
     * */
    private void initField(){
        // Задаем основные настройки окна игры
        setBounds(cellSize*dimension, cellSize*dimension, 400,300);
        setTitle("Крестики - нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(); // Панель управления игрой
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize*dimension, 150);

        JPanel gameFieldPanel = new JPanel(); // Панель самой игры
        gameFieldPanel.setLayout(new GridLayout(dimension, dimension));
        gameFieldPanel.setSize(cellSize*dimension, cellSize*dimension);

        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension*dimension];

        // Инициализируем игровое поле
        for(int i = 0; i < (dimension*dimension); i ++){
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
        }

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Метод очистки поля и матрицы игры
     * */
    void emptyField(){
        for(int i = 0; i < (dimension*dimension); i ++){
            gameButtons[i].setText("");

            int x = i / GameBoard.dimension;
            int y = i % GameBoard.dimension;

            gameField[x][y] = nullSymbol;
        }

        }
        Game getGame() {
            return game;
    }

    /**
     *  Метод проверки доступности клетки для хода
     * @param x  - по горизонтали
     * @param y - по вертикали
     * @return boolean
     */
    boolean isTurnable(int x, int y){
        boolean result = false;

        if (gameField[y][x] == nullSymbol)
            result = true;

        return  result;
    }

   /**
    * Обновление матрицы игры после хода
    * @param  x - по горизонтали
    * @param y - по вертикали
    * */
   void updateGameField(int x, int y){
       gameField[y][x] = game.getCurrentPlayer().getPlayerSing();
   }

   /**
    * Метод проверки заполнености поля
    * @return boolean
    * */
   boolean isFull(){
       boolean result = true;

       for (int i = 0; i < dimension; i ++){
           for (int j = 0; j < dimension; j++){
               if(gameField[i][j] == nullSymbol)
                   result = false;
           }
       }
       return result;
   }

   /**
    * Функция возвращает кнопку по индексу
    * */
    public GameButton getButton(int buttonIndex){
        return gameButtons[buttonIndex];
    }
}


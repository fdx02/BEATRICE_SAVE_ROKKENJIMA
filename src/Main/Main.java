package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Rondro");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //Hace que la ventana (JFrame) agarre el tamanio preferido de su subcomponente(en este caso el gamePanel) --> (window.add(gamepanel)
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();



    }
}

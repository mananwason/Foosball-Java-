package MainClass;
import java.awt.EventQueue;

import javax.swing.JFrame;

import GameComponents.StartGamePanel;


public class StartGame extends JFrame {

    public StartGame() {

        initUI();
    }

    private void initUI() {

        add(new StartGamePanel(this));
        pack();

        setTitle("FoosBall");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                StartGame ex = new StartGame();
                ex.setVisible(true);
                
                
            }
        });
    }
   }


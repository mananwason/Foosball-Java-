package GameComponents;



import java.awt.EventQueue;
import javax.swing.JFrame;



public class Image extends JFrame {

	    public Image(Formation formation, int errorLevel) {
        initUI(formation, errorLevel);
    }

    private void initUI(Formation formation, int errorLevel) {

    	GameSession game=new GameSession(formation, errorLevel);
    	
        add(game.getBoard());
        pack();

        setTitle("FoosBall");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        super.toFront();
        repaint();
    }

   /* public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Image ex = new Image();
                ex.setVisible(true);
            }
        });
    }*/
}
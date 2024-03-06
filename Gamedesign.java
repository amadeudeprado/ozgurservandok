import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main class that sets up the game window (JFrame)
public class Gamedesign extends JFrame {

    private GamePanel panel;

    public Gamedesign() {
        panel = new GamePanel();
        this.setContentPane(panel);
        this.setTitle("Simple Fighting Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Gamedesign();
        });
    }
}

// Game panel where all the drawing happens
class GamePanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH = 500;
    private final int PANEL_HEIGHT = 500;
    private final int ENEMY_SIZE = 50;
    private Rectangle enemy1;
    private Rectangle enemy2;
    private Timer timer;

    public GamePanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);

        // Initialize enemies
        enemy1 = new Rectangle(100, 200, ENEMY_SIZE, ENEMY_SIZE);
        enemy2 = new Rectangle(300, 200, ENEMY_SIZE, ENEMY_SIZE);

        // Set up a timer that calls actionPerformed method every 10 ms
        timer = new Timer(10, this);
        timer.start();
    }

    // This method is called every time the timer fires (every 10 ms here)
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the enemies (you can add more sophisticated logic)
        enemy1.x += 1;
        enemy2.x -= 1;

        // Check for collisions
        if (enemy1.intersects(enemy2)) {
            // Stop the timer - this could be replaced with more sophisticated game over logic
            timer.stop();
        }

        // Ask the JPanel to repaint itself
        repaint();
    }

    // This method is called whenever the JPanel needs to be redrawn
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(enemy1.x, enemy1.y, enemy1.width, enemy1.height);
        g.setColor(Color.BLUE);
        g.fillRect(enemy2.x, enemy2.y, enemy2.width, enemy2.height);
    }
}

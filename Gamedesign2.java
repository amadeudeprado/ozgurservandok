import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Gamedesign2 extends JPanel implements Runnable {
    // Player controlled by mouse movement
    private int playerX = 100, playerY = 100;

    // List of enemies
    private final ArrayList<int[]> enemies;
    private final static int ENEMY_SIZE = 40;
    private final static int PLAYER_SIZE = 50;

    // Dancer coordinates
    private int dancerX = 100, dancerY = 100;

    // Frame dimensions
    private final int WIDTH = 800, HEIGHT = 600;

    // Game constructor
    public Gamedesign2() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);

        // Initialize enemies
        enemies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            enemies.add(new int[]{50 + i * 100, 50 + i * 100});
        }

        // Handle mouse movement
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                playerX = e.getX();
                playerY = e.getY();
                repaint();
            }
        });

        // Start the game loop
        new Thread(this).start();
    }

    // Game loop
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(16); // Approx 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Update enemies to chase the player
            for (int[] enemy : enemies) {
                if (enemy[0] < playerX) enemy[0]++;
                else if (enemy[0] > playerX) enemy[0]--;
                if (enemy[1] < playerY) enemy[1]++;
                else if (enemy[1] > playerY) enemy[1]--;
            }

            // Update dancer to move randomly
            Random rand = new Random();
            dancerX = rand.nextInt(WIDTH - ENEMY_SIZE);
            dancerY = rand.nextInt(HEIGHT - ENEMY_SIZE);

            // Repaint the frame
            repaint();
        }
    }

    // Paint components
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw player
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);

        // Draw enemies
        g.setColor(Color.RED);
        for (int[] enemy : enemies) {
            g.fillRect(enemy[0], enemy[1], ENEMY_SIZE, ENEMY_SIZE);
        }

        // Draw dancer
        g.setColor(Color.GREEN);
        g.fillOval(dancerX, dancerY, ENEMY_SIZE, ENEMY_SIZE);
    }

    // Main method to start the game
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Game");
        Gamedesign2 game = new Gamedesign2();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class AdvancedGame extends JPanel implements Runnable {
    // Player attributes
    private int playerX = 400, playerY = 300;
    private final int playerSize = 50;
    private boolean gameOver;

    // Enemy attributes
    private final ArrayList<int[]> enemies;
    private final int enemySize = 40;
    private final int enemyShootChance = 2; // Higher means less chance

    // Bullet attributes
    private final ArrayList<Bullet> bullets;
    private static final int BULLET_SIZE = 10;
    private final int bulletSpeed = 5;

    // Game window attributes
    private final int width = 800, height = 600;

    // Constructor
    public AdvancedGame() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        gameOver = false;

        // Initialize player, enemies, and bullets
        enemies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            enemies.add(new int[]{100 + i * 200, 50});
        }

        bullets = new ArrayList<>();

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                playerX = e.getX() - playerSize / 2;
                playerY = e.getY() - playerSize / 2;
            }
        });

        new Thread(this).start();
    }

    // Main game loop
    @Override
    public void run() {
        while (!gameOver) {
            try {
                Thread.sleep(10); // 100 FPS theoretically
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Stop the game loop if the thread is interrupted
            }

            // Enemy logic
            Random random = new Random();
            for (int[] enemy : enemies) {
                // Enemies shoot randomly based on a chance
                if (random.nextInt(100) < enemyShootChance) {
                    bullets.add(new Bullet(enemy[0] + enemySize / 2, enemy[1], playerX + playerSize / 2, playerY + playerSize / 2, bulletSpeed));
                }
            }

            // Update bullets positions
            Iterator<Bullet> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                Bullet bullet = iterator.next();
                bullet.move();
                // Remove bullets out of bounds
                if (bullet.isOutOfBounds(width, height)) {
                    iterator.remove();
                } else if (bullet.hitPlayer(playerX, playerY, playerSize)) {
                    gameOver = true;
                    break;
                }
            }

            // Repaint the game window
            repaint();
        }
    }

    // Draw the game objects
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameOver) {
            // Draw player
            g.setColor(Color.BLUE);
            g.fillRect(playerX, playerY, playerSize, playerSize);

            // Draw enemies
            g.setColor(Color.RED);
            for (int[] enemy : enemies) {
                g.fillRect(enemy[0], enemy[1], enemySize, enemySize);
            }

            // Draw bullets
            g.setColor(Color.YELLOW);
            for (Bullet bullet : bullets) {
                g.fillOval(bullet.getX(), bullet.getY(), BULLET_SIZE, BULLET_SIZE);
            }
        } else {
            // Display game over screen
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", width / 3, height / 2);
        }
    }

    // Bullet class
    private static class Bullet {
        private double x, y;
        private final double deltaX, deltaY;

        public Bullet(int startX, int startY, int targetX, int targetY, int speed) {
            this.x = startX;
            this.y = startY;
            double angle = Math.atan2(targetY - startY, targetX - startX);
            this.deltaX = Math.cos(angle) * speed;
            this.deltaY = Math.sin(angle) * speed;
        }

        public void move() {
            x += deltaX;
            y += deltaY;
        }

        public int getX() { return (int) x; }
        public int getY() { return (int) y; }

        public boolean isOutOfBounds(int width, int height) {
            return x < -AdvancedGame.BULLET_SIZE || x > width || y < -AdvancedGame.BULLET_SIZE || y > height;
        }

        public boolean hitPlayer(int playerX, int playerY, int playerSize) {
            return x >= playerX && x <= (playerX + playerSize) &&
                   y >= playerY && y <= (playerY + playerSize);
        }
    }

    // Main method to start the game
    public static void main(String[] args) {
        JFrame frame = new JFrame("Advanced Game");
        AdvancedGame game = new AdvancedGame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}

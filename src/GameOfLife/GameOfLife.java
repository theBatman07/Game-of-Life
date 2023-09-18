package GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class GameOfLife extends JPanel implements ActionListener {

    private int cellSize;
    private Map<Point, Boolean> liveCells;
    private Timer timer;
    private JButton playButton;
    private JButton pauseButton;
    private JButton resetButton;
    private boolean isPlaying;
    private int margin; // the number of cells around the visible area to include in the update

    public GameOfLife(int cellSize) {
        this.cellSize = cellSize;
        this.liveCells = new HashMap<>();
        this.timer = new Timer(100, this);
        this.isPlaying = false;
        this.margin = 50; // initialize margin to 50 cells
        setPreferredSize(new Dimension(1200, 800)); // set initial size
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        buttonPanel.add(playButton);
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(this);
        buttonPanel.add(pauseButton);
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);
        // Add a "Step" button
        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGrid(); // Update the grid by one step
                timer.stop(); // Stop the timer to pause the animation
            }
        });
        buttonPanel.add(stepButton);

        add(buttonPanel, BorderLayout.SOUTH);
        addMouseListener(new MouseHandler());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int minX = getWidth() / 2 / cellSize - margin;
        int minY = getHeight() / 2 / cellSize - margin;
        int maxX = (getWidth() + 2 * cellSize - 1) / cellSize + margin;
        int maxY = (getHeight() + 2 * cellSize - 1) / cellSize + margin;
        for (int row = minY; row < maxY; row++) {
            for (int col = minX; col < maxX; col++) {
                Point p = new Point(row, col);
                if (liveCells.containsKey(p) && liveCells.get(p)) {
                    g.setColor(Color.white);
                    g.fillRect((col - minX) * cellSize, (row - minY) * cellSize, cellSize, cellSize);
                } else {
                    g.setColor(Color.black);
                    g.fillRect((col - minX) * cellSize, (row - minY) * cellSize, cellSize, cellSize);
                }
                g.setColor(Color.black);
                g.drawRect((col - minX) * cellSize, (row - minY) * cellSize, cellSize, cellSize);
            }
        }
    }

    private void updateGrid() {
        Map<Point, Boolean> newLiveCells = new HashMap<>();
        int minX = getWidth() / 2 / cellSize - margin;
        int minY = getHeight() / 2 / cellSize - margin;
        int maxX = (getWidth() + 2 * cellSize - 1) / cellSize + margin;
        int maxY = (getHeight() + 2 * cellSize - 1) / cellSize + margin;
        for (int row = minY; row < maxY; row++) {
            for (int col = minX; col < maxX; col++) {
                Point p = new Point(row, col);
                int liveNeighbors = countLiveNeighbors(p);
                boolean isAlive = liveCells.containsKey(p) && liveCells.get(p);
                if (isAlive && (liveNeighbors == 2 || liveNeighbors == 3)) {
                    newLiveCells.put(p, true);
                } else if (!isAlive && liveNeighbors == 3) {
                    newLiveCells.put(p, true);
                } else {
                    newLiveCells.put(p, false);
                }
            }
        }
        liveCells = newLiveCells;
        repaint();
    }

    private int countLiveNeighbors(Point p) {
        int count = 0;
        for (int row = p.x - 1; row <= p.x + 1; row++) {
            for (int col = p.y - 1; col <= p.y + 1; col++) {
                Point neighbor = new Point(row, col);
                if (!neighbor.equals(p) && liveCells.containsKey(neighbor) && liveCells.get(neighbor)) {
                    count++;
                }
            }
        }
        return count;
    }

    public class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int row = (e.getY() / cellSize) + getHeight() / (2 * cellSize) - margin;
            int col = (e.getX() / cellSize) + getWidth() / (2 * cellSize) - margin;
            Point p = new Point(row, col);
            if (liveCells.containsKey(p)) {
                liveCells.put(p, !liveCells.get(p));
            } else {
                liveCells.put(p, true);
            }
            repaint();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            isPlaying = true;
            timer.start();
        } else if (e.getSource() == pauseButton) {
            isPlaying = false;
            timer.stop();
        } else if (e.getSource() == resetButton) {
            isPlaying = false;
            timer.stop();
            liveCells.clear();
            repaint();
        } else if (isPlaying) {
            updateGrid();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game of Life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GameOfLife gameOfLife = new GameOfLife(10);
            frame.add(gameOfLife);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Screen extends JPanel {

    private Grid myGrid;
    private Thread myThread;
    private boolean running = true;

    public Screen(Grid aGrid){
        myGrid = aGrid;
        myGrid.randomizeWorld();
        myThread = new Worker();
        myThread.start();
        this.setPreferredSize(new Dimension(500,500));
        this.setSize(new Dimension(500,500));
        this.setBackground(Color.cyan);
        this.setVisible(true);
    }

    private class Worker extends Thread{
        public void run(){
            while(true){
                if(running) {
                    repaint();
                }
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        myGrid.advanceSimulation();
        int size = myGrid.size();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                int currentCell = myGrid.getCell(i, j);
                int scalingFactor = 1000 / size;
                Ellipse2D.Double newCell = new Ellipse2D.Double(i * scalingFactor, j * scalingFactor, scalingFactor, scalingFactor);
                switch(currentCell){
                    case Grid.ALIVE:
                        g2.setColor(Color.BLACK);
                        break;
                    case Grid.DEAD:
                        g2.setColor(Color.LIGHT_GRAY);
                        break;
                }
                g2.fill(newCell);
            }
        }
    }

    public void pause(){
        running = false;
    }

    public void resume(){
        running = true;
    }

    public static void main(String[] args){
        JFrame conwayGOL = new JFrame("Conway's Game of Life");
        conwayGOL.setPreferredSize(new Dimension(1000,1000));
        conwayGOL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conwayGOL.setLayout(new BorderLayout());
        Screen simScreen = new Screen(new Grid(50));
        conwayGOL.add(simScreen, BorderLayout.CENTER);
        conwayGOL.add(new OptionsPanel(simScreen), BorderLayout.SOUTH);
        conwayGOL.pack();
        conwayGOL.setVisible(true);
    }
}



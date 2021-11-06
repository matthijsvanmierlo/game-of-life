import java.util.Arrays;

public class Grid {
    private int[][] numGrid;
    private Move[] moves;
    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    public Grid(int size){
        numGrid = new int[size][size];
        Move move2 = new Move(0, 1);
        Move move3 = new Move(1, 0);
        Move move4 = new Move(1, 1);
        Move move5 = new Move(0, -1);
        Move move6 = new Move(-1, 0);
        Move move7 = new Move(-1, -1);
        Move move8 = new Move(1, -1);
        Move move9 = new Move(-1, 1);
        moves = new Move[]{move2, move3, move4, move5, move6, move7, move8, move9};

    }

    public void randomizeWorld(){
        for(int i = 0; i < numGrid.length; i++){
            for(int j = 0; j < numGrid[0].length; j++){
                numGrid[i][j] = 0;
            }
        }

        for(int i = 0; i < numGrid.length; i++){
            for(int j = 0; j < numGrid[0].length; j++){
                double prob = Math.random();
                if(prob > 0.5){
                    numGrid[i][j] = 1;
                }
            }
        }
    }

    public void printGrid(){
        for(int i = 0; i < numGrid.length; i++){
            for(int j = 0; j < numGrid[0].length; j++){
                System.out.print(numGrid[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int getCell(int x, int y){
        return numGrid[x][y];
    }

    public int size(){
        return numGrid.length;
    }

    public void advanceSimulation(){
        int[][] tempGrid = new int[numGrid.length][numGrid[0].length];
        for(int i = 0; i < numGrid.length; i++){
            for(int j = 0; j < numGrid[0].length; j++){
                // Get current grid item
                Coord currentCoord = new Coord(i, j);
                int liveNeighbors = 0;
                for(Move aMove : moves){
                    Coord updatedCoord = currentCoord.updatedCoordinate(aMove);
                    int tempX = updatedCoord.getX();
                    int tempY = updatedCoord.getY();
                    if(tempX < numGrid.length && tempX >= 0 && tempY < numGrid[0].length && tempY >= 0){
                        if(numGrid[tempX][tempY] == ALIVE){
                            liveNeighbors += 1;
                        }
                    }
                }
                if(liveNeighbors < 2 || liveNeighbors > 3){
                    tempGrid[i][j] = DEAD;
                }else if((liveNeighbors == 2 || liveNeighbors == 3) && numGrid[i][j] == ALIVE){
                    tempGrid[i][j] = ALIVE;
                }else if(liveNeighbors == 3 && numGrid[i][j] == DEAD){
                    tempGrid[i][j] = ALIVE;
                }
            }
        }
        for(int i = 0; i < numGrid.length; i++){
            for(int j = 0; j < numGrid[0].length; j++){
                numGrid[i][j] = tempGrid[i][j];
            }
        }
    }

    private class Coord{
        private int x;
        private int y;

        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Coord updatedCoordinate(Move m){
            return new Coord(this.x + m.getDx(), this.y + m.getDy());
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

    }

    private class Move {

        private int dx;
        private int dy;

        public Move(int dx, int dy){
            this.dx = dx;
            this.dy = dy;
        }

        public int getDx(){
            return dx;
        }

        public int getDy(){
            return dy;
        }
    }

    public static void main(String[] args){
        Grid test = new Grid(10);
        test.randomizeWorld();
        test.printGrid();
        System.out.println("-------------------------------");
        test.advanceSimulation();
        test.printGrid();
    }
}

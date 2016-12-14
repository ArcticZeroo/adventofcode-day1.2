import java.util.ArrayList;
import java.util.List;

public class Mover {
    private Direction direction = Direction.UP;
    private int x = 0;
    private int y = 0;
    private List<String> visitedLocations = new ArrayList<>();

    private void turnRight(){
        int newDir = (direction.ordinal() + 1);

        if(newDir > Direction.values().length-1) newDir = 0;

        direction = Direction.values()[newDir];
    }

    private void turnLeft(){
        int newDir = (direction.ordinal() - 1);

        if(newDir < 0) newDir = Direction.values().length-1;

        direction = Direction.values()[newDir];
    }

    private boolean moveForward(int steps){
        for(int i = 0; i < steps; i++){
            if(direction == Direction.UP){
                y += 1;
            }else if(direction == Direction.DOWN){
                y -= 1;
            }else if(direction == Direction.LEFT){
                x -= 1;
            }else if(direction == Direction.RIGHT){
                x += 1;
            }

            if(visitedLocations.contains(x+","+y)){
                return true;
            }else{
                visitedLocations.add(x+","+y);
            }
        }
        return false;
    }

    public Mover(){
        String[] input = "L1, R3, R1, L5, L2, L5, R4, L2, R2, R2, L2, R1, L5, R3, L4, L1, L2, R3, R5, L2, R5, L1, R2, L5, R4, R2, R2, L1, L1, R1, L3, L1, R1, L3, R5, R3, R3, L4, R4, L2, L4, R1, R1, L193, R2, L1, R54, R1, L1, R71, L4, R3, R191, R3, R2, L4, R3, R2, L2, L4, L5, R4, R1, L2, L2, L3, L2, L1, R4, R1, R5, R3, L5, R3, R4, L2, R3, L1, L3, L3, L5, L1, L3, L3, L1, R3, L3, L2, R1, L3, L1, R5, R4, R3, R2, R3, L1, L2, R4, L3, R1, L1, L1, R5, R2, R4, R5, L1, L1, R1, L2, L4, R3, L1, L3, R5, R4, R3, R3, L2, R2, L1, R4, R2, L3, L4, L2, R2, R2, L4, R3, R5, L2, R2, R4, R5, L2, L3, L2, R5, L4, L2, R3, L5, R2, L1, R1, R3, R3, L5, L2, L2, R5".split(", ");

        for(String command : input){
            String turnDir = command.substring(0, 1);

            int stepCount = Integer.parseInt(command.substring(1));

            if(turnDir.equals("L")){
                turnLeft();
            }else if(turnDir.equals("R")){
                turnRight();
            }

            System.out.println("New Direction is " + direction.toString() + " with command " + command);

            if(moveForward(stepCount)){
                System.out.println(String.format("I just visited a location twice, (%d, %d), which is %d blocks away from start.", x, y, Math.abs(x) + Math.abs(y)));
                return;
            }
        }

        System.out.println(String.format("My final location is (%d, %d). I am %d blocks from start.", x, y, Math.abs(x) + Math.abs(y)));
    }
}

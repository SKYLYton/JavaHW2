package lesson1;

import lesson1.elements.Action;
import lesson1.elements.Cat;
import lesson1.elements.Human;
import lesson1.elements.Robot;
import lesson1.obstacles.Obstacles;
import lesson1.obstacles.Treadmill;
import lesson1.obstacles.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int maxObstacles = 10;
        int maxAction = 20;
        int maxDistanceRun = 100;
        int maxDistanceJump = 10;

        Random random = new Random();

        List<Obstacles> listObstacles = new ArrayList<>();
        List<Action> listAction = new ArrayList<>();

        for (int i = 0; i < Math.max(maxAction, maxObstacles); i++) {
            if(maxObstacles > i) {
                listObstacles.add(random.nextBoolean() ?
                        new Treadmill(random.nextInt(maxDistanceRun) + 1) : new Wall(random.nextInt(maxDistanceJump) + 1));
            }

            if(maxAction < i){
                continue;
            }

            int number = Math.max(maxAction, maxObstacles) - i;
            int distanceRun = random.nextInt(maxDistanceRun) + 1;
            int distanceJump = random.nextInt(maxDistanceJump) + 1;

            switch (random.nextInt(3)) {
                case 0:
                    listAction.add(new Cat("Cat " + number, distanceRun, distanceJump));
                    break;
                case 1:
                    listAction.add(new Human("Human " + number, distanceRun, distanceJump));
                    break;
                case 2:
                    listAction.add(new Robot("Robot " + number, distanceRun, distanceJump));
                    break;
            }
        }

        for (int i = 0; i < listObstacles.size(); i++) {
            for (int j = listAction.size() - 1; j >= 0; j--) {
                if(!listObstacles.get(i).competition(listAction.get(j))){
                    System.out.println(listAction.get(j).getName() + " выбывает из соревнований!\n");
                    listAction.remove(j);
                }
            }
        }
    }
}

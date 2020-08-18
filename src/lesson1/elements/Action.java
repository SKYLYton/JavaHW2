package lesson1.elements;

public interface Action {
    boolean run(int distance);

    boolean jump(int distance);

    String getName();
}

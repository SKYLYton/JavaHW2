package lesson2;

public class MyArraySizeException extends IllegalAccessException {
    private int number;

    public MyArraySizeException(String s, int number) {
        super(s);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

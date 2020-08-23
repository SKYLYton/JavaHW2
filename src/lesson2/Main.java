package lesson2;

public class Main {

    static int successSize = 4;

    public static void main(String[] args) {

        String[][] str = {
                {"12", "14", "14", "14"},
                {"12", "14", "5432", "14"},
                {"12", "14", "14", "14"},
                {"12", "14", "453579", "23"}
        };
        try {
            System.out.println(a(str));
        } catch (MyArraySizeException | MyArrayDataException e) {
            //e.printStackTrace();
            System.out.println("Неверные данные: " + e.getMessage());
        }
    }

    static int a(String[][] a) throws MyArraySizeException, MyArrayDataException {

        if (a.length != successSize) {
            throw new MyArraySizeException("Length: " + a.length, a.length);
        }

        int sum = 0;

        for (int i = 0; i < a.length; i++) {

            if (a[i] == null || a[i].length != successSize) {
                throw new MyArraySizeException("Index: " + i, i);
            }

            for (int j = 0; j < a[i].length; j++) {
                if (!checkString(a[i][j])) {
                    throw new MyArrayDataException(String.format("Indexes: %d,%d", i, j), i, j);
                }
                sum += Integer.parseInt(a[i][j]);

            }
        }

        return sum;
    }

    public static boolean checkString(String string) {
        if (string == null) return false;
        return string.matches("^-?\\d+$");
    }

}

package lesson1a;

public class DayOfWeekMain {
    public static void main(String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.Saturday));
    }

    static String getWorkingHours(DayOfWeek dayOfWeek){

        int hours = 0;

        if(dayOfWeek.ordinal() >= DayOfWeek.Saturday.ordinal()){
            return "Сегодня выходной";
        }

        hours = Math.abs(dayOfWeek.compareTo(DayOfWeek.Saturday)) * DayOfWeek.HOURS_OF_DAY;

        //если у каждого дня недели разное кол-во часов
        /*if(dayOfWeek.customHours == 0){
            return "Сегодня выходной";
        }

        for (int i = dayOfWeek.ordinal(); i < DayOfWeek.Saturday.ordinal(); i++) {
            hours = hours + DayOfWeek.values()[i].customHours;
        }*/

        return "Рабочих часов до выходных осталось: " + hours;
    }
}

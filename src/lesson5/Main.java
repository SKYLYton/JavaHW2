package lesson5;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    public static void main(String[] args) {

        Arrays.fill(arr,1);

        long a = System.currentTimeMillis();

        fillArrForm(arr, 0);

        System.out.println(System.currentTimeMillis() - a);

        Arrays.fill(arr,1);

        a = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread[] t = {fillThread(a1, 0),
                fillThread(a2, h)
        };

        t[0].start();
        t[1].start();

        try {
            t[0].join();
            t[1].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - a);

    }

    static Thread fillThread(float[] a, int j){
        return new Thread(()->{
            fillArrForm(a, j);
        });

    }

    static void fillArrForm(float[] a, int j){
        for (int i = 0; i < a.length; i++) {
            a[i] = (float)(a[i] * Math.sin(0.2f + (i+j) / 5) * Math.cos(0.2f + (i+j) / 5) * Math.cos(0.4f + (i+j) / 2));
        }
    }
}

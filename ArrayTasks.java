import java.util.*;
import java.util.stream.*;
 
public class ArrayTasks {
 
    static int[] generateArray(int n, int min, int max) {
        Random r = new Random();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = min + r.nextInt(max - min + 1);
        return a;
    }
 
    static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.println("[елемент_" + (i + 1) + "_значення_" + a[i] + "]" + (i < a.length - 1 ? "," : ""));
    }
 
    static void task1(int[] a, int lo, int hi) {
        int cnt = 0, sum = 0;
        for (int x : a) if (x % 2 == 0 && x >= lo && x <= hi) { cnt++; sum += x; }
        System.out.println("Кількість: " + cnt + ", Сума: " + sum);
    }
 
    static void task2(int[] a) {
        double avg = Arrays.stream(a).average().orElse(0);
        long cnt = Arrays.stream(a).filter(x -> x > avg).count();
        System.out.println("Середнє: " + avg + ", Більших за середнє: " + cnt);
    }
 
    static int[] task3(int[] a, int[] b) {
        int[] c = new int[a.length];
        for (int i = 0; i < a.length; i++) c[i] = a[i] + b[i];
        return c;
    }
 
    static int[] task4(int[] a, int[] b) {
        int[] c = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
 
    static void task5(int[] a) {
        int maxI = 0, minI = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[maxI]) maxI = i;
            if (a[i] < a[minI]) minI = i;
        }
        int t = a[maxI]; a[maxI] = a[minI]; a[minI] = t;
    }
 
    static int[][] task6(int[] a) {
        int[] pos = Arrays.stream(a).filter(x -> x > 0).toArray();
        int[] neg = Arrays.stream(a).filter(x -> x < 0).toArray();
        return new int[][]{pos, neg};
    }
 
    static int[] task7(int[] a) {
        int max = Arrays.stream(a).max().getAsInt();
        int min = Arrays.stream(a).min().getAsInt();
        List<Integer> res = new ArrayList<>();
        boolean maxUsed = false, minUsed = false;
        for (int x : a) {
            if (x == max) { if (!maxUsed) { res.add(x); maxUsed = true; } }
            else if (x == min) { if (!minUsed) { res.add(x); minUsed = true; } }
            else res.add(x);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
 
    static int[] task8(int[] a, int[] b) {
        double avg1 = Arrays.stream(a).average().orElse(0);
        double avg2 = Arrays.stream(b).average().orElse(0);
        double lo = Math.min(avg1, avg2), hi = Math.max(avg1, avg2);
        return IntStream.concat(Arrays.stream(a), Arrays.stream(b))
            .filter(x -> x >= lo && x <= hi).toArray();
    }
 
    public static void main(String[] args) {
        int[] arr = generateArray(10, -20, 20);
        printArray(arr);
 
        task1(arr, -10, 10);
        task2(arr);
 
        printArray(task3(generateArray(5, 0, 10), generateArray(5, 0, 10)));
        printArray(task4(generateArray(3, 0, 5), generateArray(7, 0, 5)));
 
        task5(arr);
        printArray(arr);
 
        int[][] split = task6(arr);
        printArray(split[0]);
        printArray(split[1]);
 
        printArray(task7(new int[]{5, 17, 350, 5, 350, 5, 2, 350}));
        printArray(task8(generateArray(6, 0, 20), generateArray(6, 0, 20)));
    }
}
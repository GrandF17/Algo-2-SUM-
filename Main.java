import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Hashtable;

public class Main<T> {
    private T[] array;

    @SuppressWarnings("unchecked")
    public Main(int capacity, T... dummy) {
        if (dummy.length > 0)
            throw new IllegalArgumentException("Do not provide values for dummy argument.");
        Class<?> c = dummy.getClass().getComponentType();
        array = (T[]) Array.newInstance(c, capacity);
    }

    @Override
    public String toString() {
        return "GenSet of " + array.getClass().getComponentType().getName() + "[" + array.length + "]";
    }

    public static void main(String[] args) {
        //filling our array
        int N = Integer.parseInt(args[0]), x = 7100;
        Main<Integer> Arr = new Main<>(N);
        for (int i = 0; i < N; i++)
            Arr.array[i] = (int) (Math.random() * 10000);

        System.out.println(Arrays.toString(Arr.array));

        //sort our array
        System.out.println("Sorting is now running...");
        EffectiveSort.quickSort(Arr.array, 0, N - 1);

        //searching for x
        //algorithm with optimisation
        System.out.println("Optimized searching is now running...");
        long start = System.currentTimeMillis();

        int left = 0;
        int right = N - 1;
        while((Arr.array[left] + Arr.array[right]) != x && right > left) {
            if((Arr.array[left] + Arr.array[right]) > x) {
                right--;
            } else {
                left++;
            }
        }
        if((Arr.array[left] + Arr.array[right]) == x) {
            System.out.println("Founded:");
            System.out.println(Arr.array[left] + " + " + Arr.array[right] + " = " + x);
        }

        System.out.println("Seconds:" + ((System.currentTimeMillis() - start) / 1000) +
                " Millis: " + ((System.currentTimeMillis() - start) - (System.currentTimeMillis() - start) / 1000));

        //HashTable search
        System.out.println("HashTable searching is now running...");
        start = System.currentTimeMillis();

        Hashtable<Integer, String> balance = new Hashtable<>();
        int i = 0;
        while(i < N) {
            int y = x - Arr.array[i];
            if(balance.containsKey(y)) {
                System.out.println("Founded:");
                System.out.println(y + " + " + Arr.array[i] + " = " + x);
                break;
            } else {
                balance.put(y, y + "");
            }
            i++;
        }

        System.out.println("Seconds:" + ((System.currentTimeMillis() - start) / 1000) +
                " Millis: " + ((System.currentTimeMillis() - start) - (System.currentTimeMillis() - start) / 1000));
    }
}

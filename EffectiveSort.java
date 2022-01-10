import java.util.Arrays;

public class EffectiveSort {
    public EffectiveSort() { }

    /**
     * @param beginIndex Начальный индекс для сортировки.
     * @param endIndex Конечный индекс для сортировки.
     */

    //-_Quick sort_-
    public static <T extends Comparable<T>> void quickSort(T[] arr, int beginIndex, int endIndex) {
        if (arr.length == 0)
            return;
        if (beginIndex >= endIndex)
            return;

        int middle = beginIndex + (endIndex - beginIndex) / 2;
        T basis = arr[middle];
        int i = beginIndex, j = endIndex;

        while (i <= j) {
            while (arr[i].compareTo(basis) < 0) i++;//arr[i] < basis
            while (arr[j].compareTo(basis) > 0)  j--;//arr[j] > basis
            if (i <= j) {
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (beginIndex < j)
            quickSort(arr, beginIndex, j);
        if (endIndex > i)
            quickSort(arr, i, endIndex);
    }

    //-_Heap sort_-
    /**
     * @param beginIndex Начальный индекс для сортировки.
     * @param endIndex Конечный индекс для сортировки.
     */
    public static <T extends Comparable<T>> void heapSort(T[] arr, int beginIndex, int endIndex)
    {
        if(beginIndex != endIndex && beginIndex + 1 != endIndex) {
            for (int i = (endIndex + beginIndex) / 2 - 1; i >= beginIndex; i--)
                heapify(arr, endIndex + 1, i);

            for (int i = endIndex; i >= beginIndex; i--) {
                T temp = arr[beginIndex];
                arr[beginIndex] = arr[i];
                arr[i] = temp;
                heapify(arr, i, beginIndex);
            }
        }
        if(arr[beginIndex].compareTo(arr[endIndex]) > 0) {//arr[beginIndex] > arr[endIndex]
            T temp = arr[beginIndex];
            arr[beginIndex] = arr[endIndex];
            arr[endIndex] = temp;
        }
    }

    public static <T extends Comparable<T>>  void heapify(T[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i;
        int right = 2 * i + 1;

        if (left < n && arr[left].compareTo(arr[largest]) > 0)//arr[left] > arr[largest]
            largest = left;
        if (right < n && arr[right].compareTo(arr[largest]) > 0)//arr[right] > arr[largest]
            largest = right;
        if (largest != i) {
            T swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    //-_Merge sort_-
    /**
     * @param beginIndex Начальный индекс для сортировки.
     * @param endIndex Конечный индекс для сортировки.
     */
    public static <T extends Comparable<T>> T[] mergeSort(T[] arr, int beginIndex, int endIndex) {
        if(beginIndex != endIndex && beginIndex + 1 != endIndex) {
            T[] buffer1 = Arrays.copyOf(arr, arr.length);
            T[] buffer2 = Arrays.copyOf(arr, arr.length);
            arr = mergesortInner(buffer1, buffer2, beginIndex, endIndex + 1);
            return arr;
        }
        //c[j].compareTo(c[j-1]) < 0 --> if (c[j] < c[j-1])
        //arr[beginIndex] > arr[endIndex]
        if(arr[beginIndex].compareTo(arr[endIndex]) > 0) {//-->arr[beginIndex] > arr[endIndex]
            T temp = arr[beginIndex];
            arr[beginIndex] = arr[endIndex];
            arr[endIndex] = temp;
            return arr;
        } else {
            return arr;
        }
    }

    public static <T extends Comparable<T>> T[] mergesortInner(T[] buffer1, T[] buffer2, int beginIndex, int endIndex) {
        if (beginIndex >= endIndex - 1)
            return buffer1;

        int middle = beginIndex + (endIndex - beginIndex) / 2;
        T[] sorted1 = mergesortInner(buffer1, buffer2, beginIndex, middle);
        T[] sorted2 = mergesortInner(buffer1, buffer2, middle, endIndex);

        int index1 = beginIndex;
        int index2 = middle;
        int destIndex = beginIndex;
        T[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex)
            result[destIndex++] = sorted1[index1].compareTo(sorted2[index2]) < 0 ? sorted1[index1++] : sorted2[index2++];/*sorted1[index1] < sorted2[index2]*/
        while (index1 < middle)
            result[destIndex++] = sorted1[index1++];
        while (index2 < endIndex)
            result[destIndex++] = sorted2[index2++];
        return result;
    }
}

package structure.project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap = new ArrayList<>();
    //int heapSize; //размер нашей кучи - то кол-во элементов, которое находится в данный момент в куче
    //List<Integer> list = new ArrayList<Integer>(); // список для временного хранения данных(для примеров работы с кучей)

    public Comparator<? super T> comparator() {
        return null;
    }

    public BinaryHeap() {
        heap.clear();
    }

    private static <T> int compare(T val1, T val2) {
        return ((Comparable<? super T>) val1).compareTo(val2);
    }

    public List<T> getHeap() {
        return heap;
    }

    public void adder(T x) {
        heap.add(x);
        bubleUp(heap.size() - 1);
    }

    private void bubleUp(int index) {
        int parentIndex = (index - 1) / 2;
        if (index > 0 && (heap.get(index).compareTo(heap.get(parentIndex)) < 0)) {
            swap(heap, parentIndex, index);
            bubleUp(parentIndex);
        }
    }

    public T deleteMin() {
        T deleteValue = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        bubleDown(0); // поскольку при удалении корня нужно менять кучу, то для её восстановления напишем метод
        return deleteValue;
    }

    private void bubleDown(int index) {
        int x = 2 * index + 1;
        if (x < heap.size()) {
            if ((x + 1 < heap.size()) && (heap.get(x + 1).compareTo(heap.get(x)) < 0)) { //если наш элемент больше элемента справа
                x++;
            }
            if (heap.get(index).compareTo(heap.get(x)) > 0) {
                swap(heap, index, x);
                bubleDown(x);
            }
        }
    }

    public static <T> void swap(List<T> list, int index, int x) {
        T temp = list.get(index);
        list.set(index, list.get(x));
        list.set(x, (T) temp);
    }

    public static <T> void sortOurHeap(List<T> list, int size, int i) {
        int indexLeft = 2 * i + 1;
        int indexRight = 2 * i + 2;
        int indexOfMinimum = i;
        if ((indexLeft < size) && (compare(list.get(indexLeft), list.get(indexOfMinimum)) < 0)) {
            indexOfMinimum = indexLeft;
        }
        /*else*/ /*!!!*/ if ((indexRight < size) && (compare(list.get(indexRight), list.get(indexOfMinimum)) < 0)) {
            indexOfMinimum = indexRight;
        }

        if (indexOfMinimum != i) {
            swap(list, indexOfMinimum, i);
            sortOurHeap(list, size, indexOfMinimum);
        }
    }

    private void heapfromArray(List<Integer> list, int size) { //преобразуем массив в min-heap
        //int startIndex = list.size() / 2 - 1;
        for (int i = list.size() - 1; i >= 0; i--) {
            sortOurHeap(list, size, i);
        }
    }

    public void heapSort(List<Integer> list, int size) {
        heapfromArray(list, size);

        for (int i = list.size() - 1; i >= 0; i--) {
            swap(list, 0, i);//!!!
            sortOurHeap(list, i, 0);
        }
    }

    public void clearHeap() {
        heap.clear();
    }

    public T search(int index) {
        if (index >= heap.size()) throw new IndexOutOfBoundsException();
        return heap.get(index);
    }

    public boolean contains(T element) {
        if (heap.size() == 0) throw new IllegalStateException("Куча не заполнена, там ничего нет");
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).compareTo(element) == 0) return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public String outInArray() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < heap.size(); i++) {
            stringBuilder.append(heap.get(i)).append(" ");
            //System.out.print(heap.get(i) + " ");
        }
        return stringBuilder.toString().trim();
    }

    public String outInHeap() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        int i = 0;
        while (i < heap.size()) {
            while (i < index && i < heap.size()) {
                stringBuilder.append(heap.get(i)).append(" ");
                //System.out.print(heap.get(i) + " ");
                i++;
            }
            index = 2 * index + 1;
            stringBuilder.append("\n");
            //System.out.println();
        }
        return stringBuilder.toString().trim();
    }
}
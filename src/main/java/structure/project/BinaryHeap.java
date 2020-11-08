package structure.project;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeap {

    private List<Integer> heap = new ArrayList<Integer>();
    //int heapSize; //размер нашей кучи - то кол-во элементов, которое находится в данный момент в куче
    //List<Integer> list = new ArrayList<Integer>(); // список для временного хранения данных(для примеров работы с кучей)

    public List<Integer> getHeap() {
        return heap;
    }

    public void adder(int x) {
        heap.add(x);
        bubleUp(heap.size() - 1);
    }

    private void bubleUp(int index) {
        int parentIndex = (index - 1) / 2;
        if (index > 0 && (heap.get(index) < heap.get(parentIndex))) {
            swap(parentIndex, index);
            bubleUp(parentIndex);
        }
    }

    public int deleteMin() {
        int deleteValue = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        bubleDown(0); // поскольку при удалении корня нужно менять кучу, то для её восстановления напишем метод
        return deleteValue;
    }

    private void bubleDown(int index) {
        int x = 2 * index + 1;
        if (x < heap.size()) {
            if (x + 1 < heap.size() && heap.get(x + 1) < heap.get(x)) {
                x++;
            }
            if (heap.get(index) > heap.get(x)) {
                swap(index, x);
                bubleDown(x);
            }
        }
    }

    private void swap(int index, int x) {
        int temp = heap.get(index);
        heap.set(index, heap.get(x));
        heap.set(x, temp);
    }

    public void clearHeap() {
        heap.clear();
    }

    public Integer search(int index) {
        if (index >= heap.size()) throw new IndexOutOfBoundsException();
        return heap.get(index);
    }

    public boolean contains(int element) {
        if (heap.size() == 0) throw new IllegalStateException("Куча не заполнена, там ничего нет");
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i) == element) return true;
        }
        return false;
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
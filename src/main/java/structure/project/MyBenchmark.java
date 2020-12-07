package structure.project;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.* ;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3 ,time = 300, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)

public class MyBenchmark {
    @Param({"1000"})
    private int N;

    private BinaryHeap binaryHeap = new BinaryHeap();
    private List<Integer> list = new ArrayList<>();
    private List<Integer> linkedList = new LinkedList<>();
    //private BinaryHeap searchBinary = new BinaryHeap();
    //private List<Integer> searchList = new ArrayList<>();
    //private List<Integer> searchLinkedList = new LinkedList<>();
    final Random random = new Random();
    int digit = random.nextInt(1000);

    public static void main(String[]args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        binaryHeap = createData();
        list = createList();
        /*linkedList = createLinkedList();*/
        //searchBinary = createSearchBinary();
        //searchList = createSearchList();
        //searchLinkedList = createSearchLinkedList();
    }

    @Benchmark
    public void add(Blackhole bh) {
        int size = binaryHeap.getHeap().size();
        for (int i = 0; i < size; i++) {
            Integer element = binaryHeap.getHeap().get(i);
            binaryHeap.adder(i);
            bh.consume(element);
        }
    }

    @Benchmark
    public void addInList(Blackhole bh) {
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            Integer element = list.get(i);
            list.add(i);
            bh.consume(element);
        }
    }

    /*@Benchmark
    public void addInLinkedList(Blackhole bh) {
        int listSize = linkedList.size();
        for (int i = 0; i < listSize; i++) {
            Integer element = linkedList.get(i);
            linkedList.add(i);
            bh.consume(element);
        }
    }*/

    @Benchmark
    public void searchInBinary(Blackhole bh) {
        int size = binaryHeap.getHeap().size();
        for (int i = 0; i < size; i++) {
            //int digit = random.nextInt(size);
            Integer element = binaryHeap.search(digit);
            //searchBinary.search(random.nextInt(size));
            bh.consume(element);
        }
    }

    @Benchmark
    public void searchInList(Blackhole bh) {
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            //int digit = random.nextInt(listSize);
            Integer element = list.get(digit);
            //searchList.get(i);
            bh.consume(element);
        }
    }

    /*@Benchmark
    public void searchInLinkedList(Blackhole bh) {
        int listSize = linkedList.size();
        for (int i = 0; i < listSize; i++) {
            //int digit = random.nextInt(listSize);
            Integer element = linkedList.get(digit);
            //searchList.get(i);
            bh.consume(element);
        }
    }*/

    private BinaryHeap createData() {
        BinaryHeap tempBinaryHeap = new BinaryHeap();
        for (int i = 0; i < N; i++) {
            tempBinaryHeap.adder(random.nextInt(1000 + 1));
        }
        return tempBinaryHeap;
    }

    private List<Integer> createList() {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tempList.add(random.nextInt(1000 + 1));
        }
        return tempList;
    }

    /*private List<Integer> createLinkedList() {
        List<Integer> tempList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            tempList.add(random.nextInt(1000 + 1));
        }
        return tempList;
    }*/

    /*private BinaryHeap createSearchBinary() {
        BinaryHeap tempBinaryHeap = new BinaryHeap();
        for (int i = 0; i < N; i++) {
            tempBinaryHeap.adder(i);
        }
        return tempBinaryHeap;
    }

    private List<Integer> createSearchList() {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tempList.add(i);
        }
        return tempList;
    }

    private List<Integer> createSearchLinkedList() {
        List<Integer> tempList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            tempList.add(i);
        }
        return tempList;
    }*/

}

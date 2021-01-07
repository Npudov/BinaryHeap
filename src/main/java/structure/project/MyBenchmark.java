package structure.project;

//import org.junit.Before;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.* ;
import org.openjdk.jmh.infra.Blackhole;
import static io.qala.datagen.RandomValue.between;

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
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3 ,time = 300, timeUnit = TimeUnit.MILLISECONDS)
//@Measurement(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)

public class MyBenchmark {
    @Param({"100000", "200000", "300000", "400000"})
    private int N;

    private static int size = 1000;
    private BinaryHeap binaryHeap = new BinaryHeap();
    private BinaryHeap expectedBinaryHeap = new BinaryHeap();
    private List<Integer> list = new ArrayList<>();
    private List<Integer> expectedList = new ArrayList<>();
    //private List<Integer> linkedList = new LinkedList<>();
    //private BinaryHeap searchBinary = new BinaryHeap();
    //private List<Integer> searchList = new ArrayList<>();
    //private List<Integer> searchLinkedList = new LinkedList<>();
    //final Random random = new Random();
    //int digit = random.nextInt(1000);

    public static void main(String[]args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        for (int i = 0; i < N; i++) {
            expectedBinaryHeap.adder(between(0, 1000).integer());
            list.add(between(0, 1000).integer());
        }
        //binaryHeap = createData();
        //list = createList();
    }

    @Benchmark
    public void add(Blackhole bh) {
        //int size = binaryHeap.getHeap().size();
        for (int i = 0; i < N; i++) {
            //Integer element = (Integer) binaryHeap.getHeap().get(i);
            binaryHeap.adder(list.get(i));
        }
        bh.consume(binaryHeap);
    }

    @Benchmark
    public void addInList(Blackhole bh) {
        //int listSize = list.size();
        for (int i = 0; i < N; i++) {
            //Integer element = list.get(i);
            expectedList.add(list.get(i));
        }
        bh.consume(expectedList);
    }

    @Benchmark
    public void searchInBinary(Blackhole bh) {
        //int size = binaryHeap.getHeap().size();
        for (int i = 0; i < N; i++) {
            //Integer element = (Integer) binaryHeap.search(digit);
            expectedBinaryHeap.contains(list.get(i));
        }
        bh.consume(binaryHeap);
    }

    @Benchmark
    public void searchInList(Blackhole bh) {
        //int listSize = list.size();
        for (int i = 0; i < N; i++) {
            //Integer element = list.get(digit);
            expectedList.contains(list.get(i));
        }
        bh.consume(expectedList);
    }

    /*private BinaryHeap createData() {
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
    }*/
}

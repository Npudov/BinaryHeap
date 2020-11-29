package structure.project;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.* ;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1, time = 300, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)

public class MyBenchmark {
    @Param({"1000"})
    private int N;

    private BinaryHeap binaryHeap = new BinaryHeap();

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
    }

    @Benchmark
    public void add(Blackhole bh) {
        int listSize = binaryHeap.getHeap().size();
        for (int i = 0; i < listSize; i++) {
            Integer element = binaryHeap.getHeap().get(i);
            binaryHeap.adder(i);
            bh.consume(element);
        }
    }

    private BinaryHeap createData() {
        BinaryHeap tempBinaryHeap = new BinaryHeap();
        for (int i = 0; i < N; i++) {
            tempBinaryHeap.adder(i);
        }
        return tempBinaryHeap;
    }

}

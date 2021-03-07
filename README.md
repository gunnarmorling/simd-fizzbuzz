# SIMD FizzBuzz

A simple demo for using SIMD instructions (single instruction, multiple data) with the Vector API of Java 16
([JEP 338](https://openjdk.java.net/jeps/338)) for implementing the "FizzBuzz" game.

## Build

This project requires OpenJDK 16 or later for its build.
Apache Maven is used for the build.
Run the following to build the project:

```shell
mvn clean verify
```

Then a JMH benchmark can be executed which compares sequential/scalar and vectorized FizzBuzz performance:

```shell
java --add-modules=jdk.incubator.vector -jar target/benchmarks.jar -f 1 -wi 5 -i 5
```

## Benchmark Results

Here are the benchmark results from my Macbook Pro 2019 (2.6 GHz 6-Core Intel Core i7):

```shell
Benchmark                                        (arrayLength)   Mode  Cnt        Score        Error  Units
FizzBuzzBenchmark.sequentialFizzBuzz                       256  thrpt    5  2204774,792 ±  76581,374  ops/s
FizzBuzzBenchmark.sequentialFizzBuzzMasked                 256  thrpt    5  4156751,424 ±  23668,949  ops/s
FizzBuzzBenchmark.simdFizzBuzz                             256  thrpt    5  6748723,261 ±  34725,507  ops/s
FizzBuzzBenchmark.simdFizzBuzzMasked                       256  thrpt    5  1204128,029 ±   5556,553  ops/s
FizzBuzzBenchmark.simdFizzBuzzMasksInArray                 256  thrpt    5  6717976,769 ± 127111,649  ops/s
FizzBuzzBenchmark.simdFizzBuzzSeparateMaskIndex            256  thrpt    5  8830433,250 ±  69955,161  ops/s
````

The fastest SIMD implementation is about 4x faster than the basic sequential one.

## License

This code base is available ander the Apache License, version 2.

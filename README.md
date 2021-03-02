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
Benchmark                                         Mode  Cnt        Score        Error  Units
FizzBuzzBenchmark.simdFizzBuzz                   thrpt    5  2198658,082 ±  32523,831  ops/s
FizzBuzzBenchmark.simdFizzBuzz                   thrpt    5  6746226,066 ±  98166,843  ops/s
FizzBuzzBenchmark.simdFizzBuzzMasked             thrpt    5  1426288,420 ±  24829,082  ops/s
FizzBuzzBenchmark.simdFizzBuzzMasksInArray       thrpt    5  6661179,945 ±  84589,369  ops/s
FizzBuzzBenchmark.simdFizzBuzzSeparateMaskIndex  thrpt    5  8765581,104 ± 196244,899  ops/s
````

The fastest SIMD implementation is about 4x faster than the serial one.

## License

This code base is available ander the Apache License, version 2.

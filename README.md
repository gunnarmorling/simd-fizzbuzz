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

All tests were run using OpenJDK 16 build 16+36.

Here are the benchmark results from running on a Macbook Pro 2019 (2.6 GHz 6-Core Intel Core i7), using the [upstream build](https://jdk.java.net/16/):

```shell
Benchmark                                        (arrayLength)   Mode  Cnt        Score        Error  Units
FizzBuzzBenchmark.sequentialFizzBuzz                       256  thrpt    5  2204774,792 ±  76581,374  ops/s
FizzBuzzBenchmark.sequentialFizzBuzzMasked                 256  thrpt    5  4156751,424 ±  23668,949  ops/s
FizzBuzzBenchmark.simdFizzBuzz                             256  thrpt    5  6748723,261 ±  34725,507  ops/s
FizzBuzzBenchmark.simdFizzBuzzMasked                       256  thrpt    5  1204128,029 ±   5556,553  ops/s
FizzBuzzBenchmark.simdFizzBuzzMasksInArray                 256  thrpt    5  6717976,769 ± 127111,649  ops/s
FizzBuzzBenchmark.simdFizzBuzzSeparateMaskIndex            256  thrpt    5  8830433,250 ±  69955,161  ops/s
```

The fastest SIMD implementation is about 4x faster than the basic sequential one.

And these are the numbers from running on a Mac Mini 2020 (M1, i.e. AArch64), using the [Azul Zulu build](https://www.azul.com/downloads/zulu-community/?version=java-16-ea&os=macos&architecture=arm-64-bit&package=jdk):

```shell
Benchmark                                        (arrayLength)   Mode  Cnt        Score       Error  Units
FizzBuzzBenchmark.sequentialFizzBuzz                       256  thrpt    5  2717990,097 ±  4203,628  ops/s
FizzBuzzBenchmark.sequentialFizzBuzzMasked                 256  thrpt    5  5750402,582 ±  2479,462  ops/s
FizzBuzzBenchmark.simdFizzBuzz                             256  thrpt    5  1297631,404 ± 15613,288  ops/s
FizzBuzzBenchmark.simdFizzBuzzMasked                       256  thrpt    5   374313,033 ±  2219,940  ops/s
FizzBuzzBenchmark.simdFizzBuzzMasksInArray                 256  thrpt    5  1316375,073 ±  1178,704  ops/s
FizzBuzzBenchmark.simdFizzBuzzSeparateMaskIndex            256  thrpt    5   998979,324 ± 69997,361  ops/s
```

The scalar implementation does a fair bit better; the SIMD implementation is falling back to software, as the AArch64 NEON instruction set only supports a maximum vector length of 128 Bit, while the benchmark uses 256 Bit explicitly.

Lastly, numbers from an AWS c6g.xlarge instance (AArch64, Linux), using the [upstream build](https://jdk.java.net/16/):

```shell
Benchmark                                        (arrayLength)   Mode  Cnt        Score      Error  Units
FizzBuzzBenchmark.scalarFizzBuzz                           256  thrpt    5  1083917.956 ± 1007.316  ops/s
FizzBuzzBenchmark.scalarFizzBuzzMasked                     256  thrpt    5  2201814.020 ± 2960.114  ops/s
FizzBuzzBenchmark.simdFizzBuzz                             256  thrpt    5   468282.908 ±  449.951  ops/s
FizzBuzzBenchmark.simdFizzBuzzMasked                       256  thrpt    5   147330.900 ±   48.854  ops/s
FizzBuzzBenchmark.simdFizzBuzzMasksInArray                 256  thrpt    5   476750.622 ±  223.152  ops/s
FizzBuzzBenchmark.simdFizzBuzzSeparateMaskIndex            256  thrpt    5   486352.294 ±   97.117  ops/s
```

Again, the poor "SIMD" numbers are due to this not being intrinsified to actual hardware instructions.

## License

This code base is available ander the Apache License, version 2.

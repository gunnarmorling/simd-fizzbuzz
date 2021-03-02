# SIMD FizzBuzz

A simple demo for using SIMD instructions (single instruction, multiple data) with the Vector API of Java 16
([JEP 338](https://openjdk.java.net/jeps/338)).

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

## License

This code base is available ander the Apache License, version 2.

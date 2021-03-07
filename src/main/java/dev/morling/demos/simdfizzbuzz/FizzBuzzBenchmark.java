/**
 *  Copyright 2021 Gunnar Morling
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package dev.morling.demos.simdfizzbuzz;

import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class FizzBuzzBenchmark {

    @State(Scope.Benchmark)
    public static class MyState {

        @Param({"256"}) //, "1020", "1024"})
        public int arrayLength;

        FizzBuzz fizzBuzz;
        int[] values;

        @Setup(Level.Trial)
        public void setUp() {
            fizzBuzz = new FizzBuzz();
            values = IntStream.range(1, arrayLength + 1).toArray();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void sequentialFizzBuzz(MyState state, Blackhole blackhole) {
        blackhole.consume(state.fizzBuzz.serialFizzBuzz(state.values));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void sequentialFizzBuzzMasked(MyState state, Blackhole blackhole) {
        blackhole.consume(state.fizzBuzz.serialFizzBuzzMasked(state.values));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void simdFizzBuzz(MyState state, Blackhole blackhole) {
        blackhole.consume(state.fizzBuzz.simdFizzBuzz(state.values));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void simdFizzBuzzMasksInArray(MyState state, Blackhole blackhole) {
        blackhole.consume(state.fizzBuzz.simdFizzBuzzMasksInArray(state.values));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void simdFizzBuzzSeparateMaskIndex(MyState state, Blackhole blackhole) {
        blackhole.consume(state.fizzBuzz.simdFizzBuzzSeparateMaskIndex(state.values));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void simdFizzBuzzMasked(MyState state, Blackhole blackhole) {
        blackhole.consume(state.fizzBuzz.simdFizzBuzzMasked(state.values));
    }
}

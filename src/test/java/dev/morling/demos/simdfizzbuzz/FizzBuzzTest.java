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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;

import org.junit.Test;

public class FizzBuzzTest {

    private static final int[] FIZZ_BUZZ_1_TO_100 = new int[] {
            1, 2, -1, 4, -2, -1, 7, 8, -1,
            -2, 11, -1, 13, 14, -3, 16, 17, -1, 19, -2,
            -1, 22, 23, -1, -2, 26, -1, 28, 29, -3,
            31, 32, -1, 34, -2, -1, 37, 38, -1, -2,
            41, -1, 43, 44, -3, 46, 47, -1, 49, -2,
            -1, 52, 53, -1, -2, 56, -1, 58, 59, -3,
            61, 62, -1, 64, -2, -1, 67, 68, -1, -2,
            71, -1, 73, 74, -3, 76, 77, -1, 79, -2,
            -1, 82, 83, -1, -2, 86, -1, 88, 89, -3,
            91, 92, -1, 94, -2, -1, 97, 98, -1, -2};

    @Test
    public void serialFizzBuzz() {
        var values = IntStream.range(1, 101).toArray();
        var result = new FizzBuzz().serialFizzBuzz(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_100);
    }

    @Test
    public void serialFizzBuzzMasked() {
        var values = IntStream.range(1, 101).toArray();
        var result = new FizzBuzz().serialFizzBuzzMasked(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_100);
    }

    @Test
    public void serialFizzBuzzGenerate() {
        var values = IntStream.range(1, 101).toArray();
        var result = new FizzBuzz().serialFizzBuzzGenerate(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_100);
    }

    @Test
    public void simdFizzBuzz() {
        var values = IntStream.range(1, 101).toArray();
        var result = new FizzBuzz().simdFizzBuzz(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_100);
    }

    @Test
    public void simdFizzBuzzMasksInArray() {
        var values = IntStream.range(1, 101).toArray();
        var result = new FizzBuzz().simdFizzBuzzMasksInArray(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_100);
    }

    @Test
    public void simdFizzBuzzSeparateMaskIndex() {
        var values = IntStream.range(1, 101).toArray();
        var result = new FizzBuzz().simdFizzBuzzSeparateMaskIndex(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_100);
    }

    @Test
    public void simdFizzBuzzMasked() {
        var values = IntStream.range(1, 101).toArray();
        var result = new FizzBuzz().simdFizzBuzzMasked(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_100);
    }
}

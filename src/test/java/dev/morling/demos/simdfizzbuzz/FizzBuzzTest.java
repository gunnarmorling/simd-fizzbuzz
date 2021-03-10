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

    private static final int[] FIZZ_BUZZ_1_TO_300 = new int[] {
            1, 2, -1, 4, -2, -1, 7, 8, -1,
            -2, 11, -1, 13, 14, -3, 16, 17, -1, 19, -2,
            -1, 22, 23, -1, -2, 26, -1, 28, 29, -3,
            31, 32, -1, 34, -2, -1, 37, 38, -1, -2,
            41, -1, 43, 44, -3, 46, 47, -1, 49, -2,
            -1, 52, 53, -1, -2, 56, -1, 58, 59, -3,
            61, 62, -1, 64, -2, -1, 67, 68, -1, -2,
            71, -1, 73, 74, -3, 76, 77, -1, 79, -2,
            -1, 82, 83, -1, -2, 86, -1, 88, 89, -3,
            91, 92, -1, 94, -2, -1, 97, 98, -1, -2,

            101, -1, 103, 104, -3, 106, 107, -1, 109, -2,
            -1, 112, 113, -1, -2, 116, -1, 118, 119, -3,
            121, 122, -1, 124, -2, -1, 127, 128, -1, -2,
            131, -1, 133, 134, -3, 136, 137, -1, 139, -2,
            -1, 142, 143, -1, -2, 146, -1, 148, 149, -3,
            151, 152, -1, 154, -2, -1, 157, 158, -1, -2,
            161, -1, 163, 164, -3, 166, 167, -1, 169, -2,
            -1, 172, 173, -1, -2, 176, -1, 178, 179, -3,
            181, 182, -1, 184, -2, -1, 187, 188, -1, -2,
            191, -1, 193, 194, -3, 196, 197, -1, 199, -2,

            -1, 202, 203, -1, -2, 206, -1, 208, 209, -3,
            211, 212, -1, 214, -2, -1, 217, 218, -1, -2,
            221, -1, 223, 224, -3, 226, 227, -1, 229, -2,
            -1, 232, 233, -1, -2, 236, -1, 238, 239, -3,
            241, 242, -1, 244, -2, -1, 247, 248, -1, -2,
            251, -1, 253, 254, -3, 256, 257, -1, 259, -2,
            -1, 262, 263, -1, -2, 266, -1, 268, 269, -3,
            271, 272, -1, 274, -2, -1, 277, 278, -1, -2,
            281, -1, 283, 284, -3, 286, 287, -1, 289, -2,
            -1, 292, 293, -1, -2, 296, -1, 298, 299, -3};

    @Test
    public void serialFizzBuzz() {
        var values = IntStream.range(1, 301).toArray();
        var result = new FizzBuzz().serialFizzBuzz(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_300);
    }

    @Test
    public void serialFizzBuzzMasked() {
        var values = IntStream.range(1, 301).toArray();
        var result = new FizzBuzz().serialFizzBuzzMasked(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_300);
    }

    @Test
    public void simdFizzBuzz() {
        var values = IntStream.range(1, 301).toArray();
        var result = new FizzBuzz().simdFizzBuzz(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_300);
    }

    @Test
    public void simdFizzBuzzMasksInArray() {
        var values = IntStream.range(1, 301).toArray();
        var result = new FizzBuzz().simdFizzBuzzMasksInArray(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_300);
    }

    @Test
    public void simdFizzBuzzSeparateMaskIndex() {
        var values = IntStream.range(1, 301).toArray();
        var result = new FizzBuzz().simdFizzBuzzSeparateMaskIndex(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_300);
    }

    @Test
    public void simdFizzBuzzSeparateMaskIndexMin() {
        var values = IntStream.range(1, 301).toArray();
        var result = new FizzBuzz().simdFizzBuzzSeparateMaskIndexMin(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_300);
    }

    @Test
    public void simdFizzBuzzMasked() {
        var values = IntStream.range(1, 301).toArray();
        var result = new FizzBuzz().simdFizzBuzzMasked(values);

        assertThat(result).isEqualTo(FIZZ_BUZZ_1_TO_300);
    }
}

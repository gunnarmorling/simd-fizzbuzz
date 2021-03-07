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

public class Main {

    public static int[] blackhole;

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();

        var values = IntStream.range(1, 257).toArray();

        for(int i = 0; i < 5_000_000; i++) {
            blackhole = fizzBuzz.simdFizzBuzz(values);
        }
    }
}

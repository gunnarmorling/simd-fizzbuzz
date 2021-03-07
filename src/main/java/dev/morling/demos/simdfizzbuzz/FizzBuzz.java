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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorSpecies;

public class FizzBuzz {

    private static final int FIZZ = -1;
    private static final int BUZZ = -2;
    private static final int FIZZ_BUZZ = -3;

    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_256;

    private final VectorMask[] resultMasksArray = new VectorMask[15];
    private final List<VectorMask<Integer>> resultMasks = new ArrayList<>(15);
    private final IntVector[] resultValues = new IntVector[15];

    private int[] serialMask = new int[] {0, 0, -1, 0, -2,
                                          -1, 0, 0, -1, -10,
                                          0, -1, 0, 0, -3};

    public FizzBuzz() {
        List<VectorMask<Integer>> threeMasks = Arrays.asList(
                VectorMask.<Integer>fromLong(SPECIES, 0b00100100),
                VectorMask.<Integer>fromLong(SPECIES, 0b01001001),
                VectorMask.<Integer>fromLong(SPECIES, 0b10010010)
                );

        List<VectorMask<Integer>> fiveMasks = Arrays.asList(
                VectorMask.<Integer>fromLong(SPECIES, 0b00010000),
                VectorMask.<Integer>fromLong(SPECIES, 0b01000010),
                VectorMask.<Integer>fromLong(SPECIES, 0b00001000),
                VectorMask.<Integer>fromLong(SPECIES, 0b00100001),
                VectorMask.<Integer>fromLong(SPECIES, 0b10000100)
                );

        for(int i = 0; i < 15; i++) {
            VectorMask<Integer> tm = threeMasks.get(i%3);
            VectorMask<Integer> fm = fiveMasks.get(i%5);

            resultMasksArray[i] = tm.or(fm);
            resultMasks.add(tm.or(fm));
            resultValues[i] = IntVector.zero(SPECIES).blend(FIZZ, tm).blend(BUZZ, fm).blend(FIZZ_BUZZ, tm.and(fm));
        }
    }

    public int[] serialFizzBuzz(int[] values) {
        int[] result = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            if (value % 3 == 0) {
                if (value % 5 == 0) {
                    result[i] = FIZZ_BUZZ;
                }
                else {
                    result[i] = FIZZ;
                }
            }
            else if (value % 5 == 0) {
                result[i] = BUZZ;
            }
            else {
                result[i] = value;
            }
        }

        return result;
    }

    public int[] serialFizzBuzzMasked(int[] values) {
        int[] result = new int[values.length];
        int j = 0;

        for (int i = 0; i < values.length; i++) {
            int res = serialMask[j];
            result[i] = res == 0 ? values[i] : res;

            j++;
            if (j == 15) {
                j = 0;
            }
        }

        return result;
    }

    public int[] simdFizzBuzz(int[] values) {
        int[] result = new int[values.length];
        int i = 0;
        int upperBound = SPECIES.loopBound(values.length);

        for (; i < upperBound; i += SPECIES.length()) {
            var va = IntVector.fromArray(SPECIES, values, i);
            int maskIdx = (i/8)%15;
            var fizzbuzz = va.blend(resultValues[maskIdx], resultMasks.get(maskIdx));
            fizzbuzz.intoArray(result, i);
        }

        for (; i < values.length; i++) {
            int value = values[i];
            if (value % 3 == 0) {
                if (value % 5 == 0) {
                    result[i] = FIZZ_BUZZ;
                }
                else {
                    result[i] = FIZZ;
                }
            }
            else if (value % 5 == 0) {
                result[i] = BUZZ;
            }
            else {
                result[i] = value;
            }
        }

        return result;
    }

    public int[] simdFizzBuzzMasksInArray(int[] values) {
        int[] result = new int[values.length];
        int i = 0;
        int upperBound = SPECIES.loopBound(values.length);

        for (; i < upperBound; i += SPECIES.length()) {
            var va = IntVector.fromArray(SPECIES, values, i);
            int maskIdx = (i/8)%15;
            var fizzbuzz = va.blend(resultValues[maskIdx], resultMasksArray[maskIdx]);
            fizzbuzz.intoArray(result, i);
        }

        for (; i < values.length; i++) {
            int value = values[i];
            if (value % 3 == 0) {
                if (value % 5 == 0) {
                    result[i] = FIZZ_BUZZ;
                }
                else {
                    result[i] = FIZZ;
                }
            }
            else if (value % 5 == 0) {
                result[i] = BUZZ;
            }
            else {
                result[i] = value;
            }
        }

        return result;
    }

    public int[] simdFizzBuzzSeparateMaskIndex(int[] values) {
        int[] result = new int[values.length];
        int i = 0;
        int j = 0;

        int upperBound = SPECIES.loopBound(values.length);
        for (; i < upperBound; i += SPECIES.length()) {
            var va = IntVector.fromArray(SPECIES, values, i);
            var fizzbuzz = va.blend(resultValues[j], resultMasksArray[j]);
            fizzbuzz.intoArray(result, i);
            j++;
            if (j == 15) {
                j = 0;
            }
        }

        for (; i < values.length; i++) {
            int value = values[i];
            if (value % 3 == 0) {
                if (value % 5 == 0) {
                    result[i] = FIZZ_BUZZ;
                }
                else {
                    result[i] = FIZZ;
                }
            }
            else if (value % 5 == 0) {
                result[i] = BUZZ;
            }
            else {
                result[i] = value;
            }
        }

        return result;
    }

    public int[] simdFizzBuzzMasked(int[] values) {
        int[] result = new int[values.length];
        int j = 0;

        for (int i = 0; i < values.length; i += SPECIES.length()) {
            var mask = SPECIES.indexInRange(i, values.length);
            var chunk = IntVector.fromArray(SPECIES, values, i, mask);
            var fizzBuzz = chunk.blend(resultValues[j], resultMasks.get(j));
            fizzBuzz.intoArray(result, i, mask);

            j++;
            if (j == 15) {
                j = 0;
            }
        }

        return result;
    }
}

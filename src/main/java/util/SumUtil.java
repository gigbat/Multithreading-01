package util;

import java.util.Random;
import java.util.stream.LongStream;

public class SumUtil {
    public Long randomSum(long number, int bound) {
        return LongStream.range(0, number)
                .map(x -> new Random().nextInt(bound))
                .sum();
    }
}

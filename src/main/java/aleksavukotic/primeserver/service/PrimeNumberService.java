package aleksavukotic.primeserver.service;

import java.util.List;

public interface PrimeNumberService {
    List<Long> primes(long maxValue);
}

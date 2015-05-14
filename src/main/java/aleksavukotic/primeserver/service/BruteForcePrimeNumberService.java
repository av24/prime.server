package aleksavukotic.primeserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BruteForcePrimeNumberService implements PrimeNumberService {

    private final PrimeNumberChecker primeNumberChecker;

    @Autowired
    public BruteForcePrimeNumberService(PrimeNumberChecker primeNumberChecker) {
        this.primeNumberChecker = primeNumberChecker;
    }

    @Cacheable(value = "brute_cache")
    public List<Long> primes(long maxValue) {
        List<Long> result = new ArrayList<Long>();
        for (long i = 2; i <= maxValue; i++) {
            if (primeNumberChecker.isPrime(i)) {
                result.add(i);
            }
        }
        return result;
    }
}

package aleksavukotic.primeserver.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimplePrimeNumberChecker implements PrimeNumberChecker {

    @Cacheable(value = "prime_cache")
    public boolean isPrime(long number) {

        if (number <= 1) {
            return false;//'A prime number (or a prime) is a natural number greater than 1 that has no positive divisors other than 1 and itself', wikipedia
        }
        if (number == 2) {//2 is by definition a prime, the only even one
            return true;
        }

        if (number % 2 == 0) {//even numbers other then two are divisible by 2
            return false;
        }

        //check the rest by brute force
        for (int i = 3; i <= Math.floor(Math.sqrt(number)); i += 2) {
            if (number % i == 0)
                return false;
        }

        return true;
    }
}

package aleksavukotic.primeserver.service;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BruteForcePrimeNumberServiceTest {

    private BruteForcePrimeNumberService underTest = new BruteForcePrimeNumberService(new SimplePrimeNumberChecker());

    @Test
    public void testSimple() {
        List<Long> primes = underTest.primes(2);
        assertThat(primes, equalTo(Arrays.asList(2L)));
    }

    @Test
    public void testSpec() {
        List<Long> primes = underTest.primes(10);
        assertThat(primes, equalTo(Arrays.asList(2L, 3L, 5L, 7L)));
    }

    @Test
    public void testNoResults() {
        List<Long> primes = underTest.primes(1);
        assertThat(primes.isEmpty(), equalTo(true));
    }

    @Test
    public void testNegativeInput() {
        List<Long> primes = underTest.primes(-1);
        assertThat(primes.isEmpty(), equalTo(true));
    }

}



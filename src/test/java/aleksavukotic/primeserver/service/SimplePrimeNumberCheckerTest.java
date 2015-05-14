package aleksavukotic.primeserver.service;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimplePrimeNumberCheckerTest {

    private SimplePrimeNumberChecker underTest = new SimplePrimeNumberChecker();

    //positive outcomes
    @Test
    public void twoIsAPrime() {
        assertThat(underTest.isPrime(2), is(true));
    }

    @Test
    public void number17IsAPrime() {
        assertThat(underTest.isPrime(17), is(true));
    }

    @Test
    public void testLargePrime() {
        assertThat(underTest.isPrime(87178291199L), is(true));
    }

    //negative outcomes
    @Test
    public void negativeNumberIsNotAPrime() {
        assertThat(underTest.isPrime(-1), is(false));
    }

    @Test
    public void zeroIsNotAPrime() {
        assertThat(underTest.isPrime(0), is(false));
    }

    @Test
    public void oneIsNotAPrime() {
        assertThat(underTest.isPrime(1), is(false));
    }


    @Test
    public void evenNumberIsnOtAPrime() {
        assertThat(underTest.isPrime(7648387674672L), is(false));
    }

    @Test
    public void testLargeNonPrime() {
        assertThat(underTest.isPrime(919191919191921L), is(false));
    }


}

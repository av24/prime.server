package aleksavukotic.primeserver.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SieveOfEratosthenesPrimeNumberService implements PrimeNumberService {


    @Cacheable(value = "eratosthenes_cache")
    public List<Long> primes(long maxValue) {
        Map<Long, EratosthenesEntry> entries = new HashMap();
        for (long i = 2; i <= maxValue; i++) {
            entries.put(i, new EratosthenesEntry(i));
        }

        Long nextPrime = doCheck(2, maxValue, entries);
        while (nextPrime != null) {
            nextPrime = doCheck(nextPrime, maxValue, entries);
        }
        List<Long> result = new ArrayList<Long>();
        for (EratosthenesEntry x : entries.values()) {
            if (!x.isMarked()) {
                result.add(x.getValue());
            }
        }
        return result;


    }

    private Long doCheck(long prime, long maxValue, Map<Long, EratosthenesEntry> entries) {
        long nextNonPrime = prime * 2;
        for (long i = nextNonPrime; i <= maxValue; i += prime) {
            EratosthenesEntry eratosthenesEntry = entries.get(i);
            eratosthenesEntry.setMarked(true);
        }
        if (prime >= maxValue) {
            return null;
        }
        for (long i = prime + 1; i <= maxValue; i++) {
            if (!entries.get(i).isMarked()) {
                return i;
            }
        }
        return null;

    }

    static class EratosthenesEntry {
        private long value;
        private boolean marked = false;

        public EratosthenesEntry(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public boolean isMarked() {
            return marked;
        }

        public void setMarked(boolean marked) {
            this.marked = marked;
        }
    }

}

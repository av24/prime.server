package aleksavukotic.primeserver.web;

import aleksavukotic.primeserver.service.BruteForcePrimeNumberService;
import aleksavukotic.primeserver.service.PrimeNumberService;
import aleksavukotic.primeserver.service.SieveOfEratosthenesPrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class RestController {

    public enum Algo {
        BruteForce,  SieveOfEratosthenes;
    }
    private final PrimeNumberService bruteForcePrimeNumberService;
    private final PrimeNumberService sieveOfEratosthenesPrimeNumberService;

    @Autowired
    public RestController(
            @Qualifier("bruteForcePrimeNumberService")PrimeNumberService bruteForcePrimeNumberService,
            @Qualifier("sieveOfEratosthenesPrimeNumberService")PrimeNumberService sieveOfEratosthenesPrimeNumberService) {
        this.bruteForcePrimeNumberService = bruteForcePrimeNumberService;
        this.sieveOfEratosthenesPrimeNumberService = sieveOfEratosthenesPrimeNumberService;
    }

    @RequestMapping(value = "/primes/{maxValue}")
    @ResponseBody
    public List<Long> numbers(@PathVariable Long maxValue, @RequestParam(required = false, defaultValue = "BruteForce") Algo algo){
        switch (algo){
            case BruteForce:
                return bruteForcePrimeNumberService.primes(maxValue);
            case SieveOfEratosthenes:
                return sieveOfEratosthenesPrimeNumberService.primes(maxValue);
            default:
                throw new IllegalArgumentException("Algo not supported:"+algo);
        }
    }

}

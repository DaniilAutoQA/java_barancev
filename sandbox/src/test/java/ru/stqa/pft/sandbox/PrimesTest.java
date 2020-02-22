package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimesTest {

    @Test
    public void testPrimes(){
        Assert.assertTrue(Primes.isPrimes(Integer.MAX_VALUE));
    }
}

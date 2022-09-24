package ru.netology.service;

import org.junit.Assert;
import org.junit.Test;

public class CashbackHackServiceTest extends Assert {
    CashbackHackService service = new CashbackHackService();

    @Test
    public void shouldRemainIfSumIsOneRuble() {
        int actual = 999;
        int expected = service.remain(1);
        assertEquals(actual, expected);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotRemainIfSumIsZero() throws RuntimeException {
        service.remain(0);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfSumLessZero() throws RuntimeException {
        service.remain(-1);
    }

    @Test
    public void shouldRemainIfSumIsLessFirstCashbackStep() {
        int actual = service.remain(541);
        int expected = 459;
        assertEquals(actual, expected);
    }

    @Test
    public void shouldRemainIfSumIfBoundaryOneRubleBeforeCashback() {
        int actual = service.remain(999);
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotRemainIfSumIsEqualCashbackSum() throws RuntimeException {
        service.remain(1000);
    }

    @Test
    public void shouldRemainIfSumIsBoundaryFirstCashbackPlusOne() {
        int actual = service.remain(1001);
        int expected = 999;
        assertEquals(actual, expected);
    }

    @Test
    public void shouldRemainIfSumIsMoreThanFirstCashback() {
        int actual = service.remain(2015);
        int expected = 985;
        assertEquals(actual, expected);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotRemainIfSumIsEqualOneOfCashbackStep() {
        service.remain(2000);
    }
}

package ru.netology.service;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class CashbackHackServiceTest extends Assertion {
    CashbackHackService service = new CashbackHackService();

    @Test(description = "Проверка, что при сумме трат 1 рубль сервис напоминает потратить 999р")
    public void shouldRemainIfSumIsOneRuble() {
        int actual = 999;
        int expected = service.remain(1);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = RuntimeException.class,
          description = "Если трат не было (сумма трат - 0), сервис ничего не рекомендует или выбрасывает ошибку")
    public void shouldNotRemainIfSumIsZero() throws RuntimeException {
        service.remain(0);
    }

    @Test(expectedExceptions = RuntimeException.class,
          description = "Сервис не должен принимать сумму меньше нуля")
    public void shouldThrowExceptionIfSumLessZero() throws RuntimeException {
        service.remain(-1);
    }

    @Test(description = "Должен выдавать правильноую рекомендованную сумму, если сумма покупки меньше первого кэшбека - 1000 руб")
    public void shouldRemainIfSumIsLessFirshCashback() {
        int actual = service.remain(541);
        int expected = 459;
        assertEquals(actual, expected);
    }

    @Test(description = "Должен рекомендовать потратить 1р, если не хватает рубля до суммы кэшбека")
    public void shouldRemainIfSumIfBoundaryOneRubleBeforeCashback() {
        int actual = service.remain(999);
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = RuntimeException.class,
          description = "Сервис не должен рекомендовать докупить на 1000р, если сумма равна кэшбеку")
    public void shouldNotRemainIfSumIsEqualCashbackSum() throws RuntimeException {
        service.remain(1000);
    }

    @Test(description = "Сервис должен рекомендовать правильную сумму, если купили на 1р больше первого порога кэшбека")
    public void shouldRemainIfSumIsBoundaryFirstCashbackPlusOne() {
        int actual = service.remain(1001);
        int expected = 999;
        assertEquals(actual, expected);
    }

    @Test(description = "Сервис должен при любой сумме после первого порога кэшбека предлагать округлить до следующего порога")
    public void shouldRemainIfSumIsMoreThanFirstCashback() {
        int actual = service.remain(2015);
        int expected = 985;
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = RuntimeException.class,
          description = "После первого порога кэшбека в 1000р на следующих порогах кэшбека (2000, 3000 и тп) сервис не" +
          "должен предлагать потратить еще")
    public void shouldNotRemainIfSumIsEqualOneOfCashbackStep() {
        service.remain(2000);
    }
}

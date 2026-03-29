import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class TransferTest {
    @Test
    fun shouldReturnZero_whenCardTypeIsNotSupported() {
        val amount = 100_000
        val previousMonthAmount = 0
        val cardType = "Маэстро"

        val result = transfer(amount, previousMonthAmount, cardType)

        Assert.assertEquals(0, result)
    }

    @Test
    fun shouldReturnZero_whenDailyLimitExceeded() {
        val amount = 200_000
        val previousMonthAmount = 0
        val cardType = "Visa"

        val result = transfer(amount, previousMonthAmount, cardType)

        Assert.assertEquals(0, result)
    }

    @Test
    fun shouldReturnZero_whenMonthlyLimitExceeded() {
        val amount = 100_000
        val previousMonthAmount = 550_000
        val cardType = "Visa"

        val result = transfer(amount, previousMonthAmount, cardType)

        Assert.assertEquals(0, result)
    }

    @Test
    fun shouldReturnZero_whenMastercardPreviousAmountsAndAmountLessMonthLimit() {
        val amount = 10_000
        val previousMonthAmount = 65_000
        val cardType = "Mastercard"

        val result = transfer(amount, previousMonthAmount, cardType)

        Assert.assertEquals(0, result)
    }

    @Test
    fun shouldReturnTax_whenMastercardPreviousAmountsMoreMonthLimit() {
        val amount = 10_000
        val previousMonthAmount = 80_000
        val cardType = "Mastercard"

        val result = transfer(amount, previousMonthAmount, cardType)

        Assert.assertEquals(80, result)
    }

    @Test
    fun shouldReturnTax_whenMastercardPreviousAmountsAndAmountMoreMonthLimit() {
        val amount = 10_000
        val previousMonthAmount = 70_000
        val cardType = "Mastercard"

        val result = transfer(amount, previousMonthAmount, cardType)

        Assert.assertEquals(50, result)
    }

    @Test
    fun shouldReturnMinimalTax_whenVisa() {
        val amount = 1_000
        val previousMonthAmount = 100_000
        val cardType = "Visa"

        val result = transfer(amount, previousMonthAmount, cardType)

        Assert.assertEquals(35, result)
    }

    @Test
    fun shouldReturnTax_whenVisa() {
        val amount = 10_000
        val previousMonthAmount = 100_000
        val cardType = "Visa"

        val result = transfer(amount, previousMonthAmount, cardType)

        Assert.assertEquals(75, result)
    }

    @Test
    fun shouldReturnZero_whenMir() {
        val amount = 100_000
        val previousMonthAmount = 200_000
        val cardType = "Мир"

        val result = transfer(amount, previousMonthAmount, cardType)

        Assert.assertEquals(0, result)
    }
}
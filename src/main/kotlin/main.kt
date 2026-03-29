import kotlin.math.ceil
import kotlin.math.max

fun transfer(amount: Int, previousMonthAmount: Int = 0, cardType: String = "Мир"): Int {
    val monthLimit = 600_000
    val dayLimit = 150_000
    if (amount > dayLimit || amount + previousMonthAmount > monthLimit) {
        println("Перевод отклонен, Превышен лимит по карте")
        return 0
    }

    val tax = when (cardType) {
        "Mastercard" -> {
            val mcLimit = 75_000
            val rate = 0.006
            when {
                previousMonthAmount >= mcLimit ->
                    ceil(amount * rate + 20).toInt()

                amount + previousMonthAmount > mcLimit ->
                    ceil((amount + previousMonthAmount - mcLimit) * rate + 20).toInt()

                else -> 0
            }
        }

        "Visa" -> {
            val rate = 0.0075
            val minTax = 35.0
            ceil(max(minTax, amount * rate)).toInt()
        }

        else -> 0
    }

    if (cardType == "Мир" || cardType == "Mastercard" || cardType == "Visa") {
        println("Вы перевели с карты $cardType $amount р. Комиссия составила $tax р.")
    } else {
        println("Данный тип карты не поддерживается")
    }
    return tax
}
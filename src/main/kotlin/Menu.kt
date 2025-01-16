import java.util.Scanner

open class Menu(private val title: String) {
    private val menuItems = mutableListOf<Pair<String, () -> Unit>>()
    private val scanner = Scanner(System.`in`)

    fun addMenuItem(text: String, action: () -> Unit) {
        menuItems.add(text to action)
    }

    open fun run() {
        while (true) {
            display()
            val input = getInput()
            if (!handleInput(input)) {
                break
            }
        }
    }

    private fun display() {
        println("\n$title:")
        menuItems.forEachIndexed { index, item -> println("${index + 1}. ${item.first}") }
        println("0. Выход")
    }

    private fun getInput(): Int {
        while (true) {
            print("Введите ваш выбор: ")
            try {
                return scanner.nextLine().toInt()
            } catch (e: NumberFormatException) {
                println("Ошибка: введите число")
            }
        }
    }

    private fun handleInput(input: Int): Boolean {
        if (input == 0) {
            return false;
        }
        if (input > menuItems.size || input < 1) {
            println("Ошибка: неверный ввод")
            return true
        }
        menuItems[input - 1].second()
        return true
    }
}

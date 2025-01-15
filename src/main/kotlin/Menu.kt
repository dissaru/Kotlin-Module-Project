import java.util.Scanner

class Menu(private val title: String) {
    private val options = mutableListOf<Pair<String, () -> Unit>>()
    private val scanner = Scanner(System.`in`)

    fun addOption(label: String, action: () -> Unit) {
        options.add(label to action)
    }

    fun show() {
        while (true) {
            println(title)
            options.forEachIndexed { index, pair ->
                println("${index + 1}: ${pair.first}")
            }
            println("0: Выход")

            val choice = readChoice(0, options.size)
            if (choice == 0) break

            options[choice - 1].second()
        }
    }

    private fun readChoice(min: Int, max: Int): Int {
        while (true) {
            println("Выберите пункт меню (от $min до $max):")
            val input = scanner.nextLine()
            val choice = input.toIntOrNull()
            if (choice != null && choice in min..max) {
                return choice
            } else {
                println("Неправильный ввод. Пожалуйста, введите цифру от $min до $max.")
            }
        }
    }
}

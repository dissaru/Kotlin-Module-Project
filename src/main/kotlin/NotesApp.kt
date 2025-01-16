import java.util.Scanner

class NotesApp {
    fun run() {
        val archiveMenu = ArchiveMenu(this)
        archiveMenu.run()
    }

    inner class ArchiveMenu(private val notesApp: NotesApp, title: String = "Список архивов") : Menu(title) {
        private val archives = mutableListOf<Archive>()

        init {
            addMenuItem("Создать архив") { createArchive() }
            addMenuItem("Выбрать архив") { selectArchive() }
        }

        private fun createArchive() {
            while (true) {
                print("Введите имя архива: ")
                val name = readLine()
                if (!name.isNullOrEmpty()) {
                    archives.add(Archive(name))
                    println("Архив '$name' создан")
                    return
                } else {
                    println("Имя архива не может быть пустым!")
                }
            }
        }

        private fun selectArchive() {
            if (archives.isEmpty()) {
                println("Нет доступных архивов. Пожалуйста, создайте архив.")
                return
            }

            val selectArchiveMenu = Menu("Выберите архив:")
            archives.forEachIndexed { index, archive ->
                selectArchiveMenu.addMenuItem("${index + 1}. ${archive.name}") {
                    println("Выбран архив '${archive.name}'")
                    val noteMenu = NoteMenu(notesApp,archive, this)
                    noteMenu.run()
                }
            }
            selectArchiveMenu.run()
        }
    }

    inner class NoteMenu(private val notesApp: NotesApp, private val archive: Archive, private val returnMenu: Menu, title: String = "Список заметок архива ") : Menu(title + archive.name) {
        private val notes = mutableListOf<Note>()
        init {
            addMenuItem("Создать заметку") { createNote() }
            addMenuItem("Выбрать заметку") { selectNote() }
        }

        private fun createNote() {
            while (true) {
                print("Введите имя заметки: ")
                val name = readLine()
                if (name.isNullOrEmpty()) {
                    println("Имя заметки не может быть пустым!")
                    continue // Продолжаем цикл, если имя пустое
                }
                while (true) {
                    print("Введите текст заметки: ")
                    val text = readLine()
                    if (text.isNullOrEmpty()) {
                        println("Текст заметки не может быть пустым!")
                        continue// Продолжаем цикл, если текст пустой
                    }
                    notes.add(Note(name, text))
                    println("Заметка '$name' создана")
                    return // Возвращаемся в меню заметок
                }
            }
        }


        private fun selectNote() {
            if (notes.isEmpty()) {
                println("Нет доступных заметок в этом архиве. Пожалуйста, создайте заметку.")
                return
            }
            val selectNoteMenu = Menu("Выберите заметку:")
            notes.forEachIndexed { index, note ->
                selectNoteMenu.addMenuItem("${index + 1}. ${note.name}") {
                    println("Выбранна заметка: '${note.name}'")
                    displayNote(note)
                }
            }
            selectNoteMenu.run()
        }

        private fun displayNote(note: Note) {
            println("-------------------")
            println("Заметка: ${note.name}")
            println("-------------------")
            println(note.text)
            println("-------------------")
        }
    }
}

class NotesApp {
    private val archives: MutableList<Archive> = mutableListOf()

    fun start() {
        val mainMenu = Menu("Меню архивов")
        mainMenu.addOption("Создать архив") { createArchive() }
        mainMenu.addOption("Посмотреть архивы") { showArchives() }
        mainMenu.show()
    }

    private fun createArchive() {
        println("Введите название архива:")
        val name = readLine()?.takeIf { it.isNotBlank() } ?: run {
            println("Название архива не должно быть пустым!")
            return
        }
        archives.add(Archive(name))
        println("Архив '$name' создан.")
    }

    private fun showArchives() {
        if (archives.isEmpty()) {
            println("Нет доступных архивов.")
            return
        }

        val archiveMenu = Menu("Список архивов")
        archives.forEach { archive ->
            archiveMenu.addOption(archive.name) { showNotes(archive) }
        }
        archiveMenu.show()
    }

    private fun showNotes(archive: Archive) {
        val notesMenu = Menu("Заметки для архива '${archive.name}'")
        notesMenu.addOption("Создать заметку") { createNote(archive) }

        if (archive.notes.isNotEmpty()) {
            archive.notes.forEach { note ->
                notesMenu.addOption(note.text) { showNoteText(note) }
            }
        }

        notesMenu.show()
    }

    private fun createNote(archive: Archive) {
        println("Введите текст заметки:")
        val text = readLine()?.takeIf { it.isNotBlank() } ?: run {
            println("Текст заметки не должен быть пустым!")
            return
        }
        archive.notes.add(Note(text))
        println("Заметка сохранена.")
    }

    private fun showNoteText(note: Note) {
        println("Заметка: ${note.text}")
        println("Нажмите Enter, чтобы вернуться.")
        readLine()
    }
}

package main.view.menus;

    /**
     * Перечисления кнопок меню
     */
enum MenuItemType{
    ADD_FILES("Добавить файлы"),
    ADD_FOLDER("Добавить папку"),
    SAVE_ALL_FILES("Сохранить все файлы"),
    SAVE_SELECTED_FILES("Сохранить выделенные файлы"),
    SAVE_ALL_FILES_TO_FOLDER("Сохранить все файлы в папку"),
    SAVE_SELECTED_FILES_TO_FOLDER("Сохранить выделенные файлы в папку"),

    EXIT("Выход"),
    SETUP_COLUMS("Настроить колонки"),
    INFO("О программе");


    private String name;
    MenuItemType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

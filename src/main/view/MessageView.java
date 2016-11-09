package main.view;

/**
 * Интерфейс отображения информационных сообщений и
 * индикатора прогресса в приложении
 */
public interface MessageView {
    /**
     * Отображает сообщение
     * @param message - сообщение для отображения
     */
    void showMessage(String message);

    /**
     * Отображает прогресс задачи.
     */
    void showProgress();//TODO Определить тип параметров и настроить
}

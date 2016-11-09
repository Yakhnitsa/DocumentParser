package main.view;

import main.bin.RDocEnum;
import main.bin.RailroadDocument;

import java.util.List;

/**
 * Интерфейс который служит для отображения жд документов и
 * для получения списка документов для последующей обработки
 * @see RailroadDocument
 */
public interface View {

    /**
     * Возвращает список всех документов.
     * @return List RailroadDocument
     * @see RailroadDocument
     */
    List<RailroadDocument> getAllDocuments();

    /**
     * Возвращает список выделенных документов.
     * @return List RailroadDocument
     * @see RailroadDocument
     */
    List<RailroadDocument> getSelectedDocuments();

    /**
     * Добавляет на панель документы
     * @param documents - список жд документов
     */
    void addDocuments(List<RailroadDocument> documents);

    /**
     * Возвращает порядок колонок для последующей обработки
     * @return
     */
    RDocEnum[] getColumnOrder();
    /**
     *
     */
    void setColumnOrder();

}
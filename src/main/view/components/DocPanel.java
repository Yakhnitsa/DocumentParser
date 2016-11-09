package main.view.components;


import main.bin.RailroadDocument;

import javax.swing.*;
import java.util.List;

/**
 * Created by Yuriy on 19.10.2016.
 */
public interface DocPanel {
    /**
     * Возвращает графическое представление панели.
     * @return графическое представление панели.
     */
    JComponent getRootPanel();
    /**
     * Добавляет документ на панель
     * @param document
     */
    void addDocument(RailroadDocument document);

    /**
     * Возвращает список всех документов на панели
     * @return список всех документов на панели
     */
    List<RailroadDocument> getAllDocuments();

    /**
     * Возвращает список выделенных документов на панели
     * @return - список выделенных документов
     */
    List<RailroadDocument> getSelectedDocuments();

    /**
     * Обновляет представление панели документов
     */
    void update();
    /**
     * Удаляет из панели выделенные документы.
     */
    void deleteSelected();
}

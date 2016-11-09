package main.view.components;


import main.bin.RailroadDocument;
import main.controller.MainController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

import static main.view.components.DocPanelImp.DocDataType.*;

/**
 * Панель документов приложения.
 */
//TODO Реализовать всплывающую панель и мнемоники для удаления выделенных документов
public class DocPanelImp implements DocPanel {
    private DocDataType[] columnOrder;
    private List<RailroadDocument> documentList = new ArrayList();
    private JPanel mainPanel;
    private DocumentTableModel tableModel;
    private JTable mainTable;
    private MainController controller;

    public DocPanelImp(MainController controller){
        columnOrder = new DocDataType[]{DOC_NUMBER,SEND_STATION_NAME_CODE,RECEIVE_STATION_NAME_CODE,CARRIAGE_NAME,FULL_NET_WEIGHT};
        this.controller = controller;
        mainPanel = new JPanel();
        tableModel = new DocumentTableModel();
        mainTable = new JTable(tableModel);
        mainPanel.add(new JScrollPane(mainTable));
    }

    /**
     * Получение данных из документа в зависимости от типа необходимых данных
     *
     * @param document - документ, с которого необходимо получить данные
     * @param dataType - тип данных, которые необходимо получить
     * @return данные из передаваемого документа в формате String
     */
    private static String getDataFromDocumentByType(RailroadDocument document, DocDataType dataType) {
        switch (dataType) {

            case DOC_NUMBER:
                return document.getDocNumber();
            case SEND_STATION_NAME_CODE:
                return document.getSendStation().getNameAndCode();
            case RECEIVE_STATION_NAME_CODE:
                return document.getReceiveStation().getNameAndCode();
            case SENDER_NAME:
                return document.getCargoSender().getName();
            case RECEIVER_NAME:
                return document.getCargoReceiver().getName();
            case CARRIAGE_NAME:
                return document.getCargoName();
            case FULL_NET_WEIGHT:
                return String.valueOf(document.getFullVeight());
            case VAGONS_COUNT:
                return String.valueOf(document.getVagonCount());
        }
        return "UNDONE";
    }

    /**
     * Добавляет документ в список документов
     * для обновления следует вызвать метод update
     *
     * @param document
     */
    @Override
    public void addDocument(RailroadDocument document) {
        documentList.add(document);
    }

    @Override
    public JComponent getRootPanel() {
        return mainPanel;
    }

    @Override
    public List<RailroadDocument> getAllDocuments() {
        return documentList;
    }

    @Override
    public List<RailroadDocument> getSelectedDocuments() {
        List<RailroadDocument> selectedDocuments = new ArrayList<>();
        int[] rows = mainTable.getSelectedRows();
        for (int i : rows) {
            selectedDocuments.add(documentList.get(i));
        }
        return selectedDocuments;
    }

    /**
     * Обновляет панель документов новыми данными
     */
    @Override
    public void update() {
        int rowCount = documentList.size();
        int columnCount = columnOrder.length;

        String[][] data = new String[rowCount][];
        int rowIndex = 0;
        for (int i = 0; i < rowCount; i++) {
            RailroadDocument document = documentList.get(i);
            String[] row = new String[columnCount];
            for (int j = 0; j < columnCount; j++) {
                row[j] = getDataFromDocumentByType(document, columnOrder[j]);
            }
            data[i] = row;
        }
        tableModel.updateData(data);
    }

    /**
     * Удаляет из списка документов выделенные на данный момент документы.
     */
    public void deleteSelected() {
        List<RailroadDocument> selectedDocuments = new ArrayList<>();
        int[] rows = mainTable.getSelectedColumns();
        for (int i : rows) {
            selectedDocuments.add(documentList.get(i));
        }
        documentList.removeAll(selectedDocuments);
    }

    /**
     * Тип данных в документе
     */
    enum DocDataType {
        DOC_NUMBER("№ док-а"),
        SEND_STATION_NAME_CODE("ст Отправления"),
        RECEIVE_STATION_NAME_CODE("ст Назначения"),
        SENDER_NAME("Отправитель"),
        RECEIVER_NAME("Получатель"),
        CARRIAGE_NAME("Груз"),
        FULL_NET_WEIGHT("Масса груза"),
        VAGONS_COUNT("Кол-во вагонов"),;
        private String name;

        DocDataType(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

    }

    /**
     * Модель таблицы отображения данных
     */
    private class DocumentTableModel extends AbstractTableModel {

        private String[][] results;

        DocumentTableModel() {

        }

        @Override
        public int getRowCount() {
            return documentList.size();
        }

        @Override
        public int getColumnCount() {
            return columnOrder.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return results[rowIndex][columnIndex];
        }

        void updateData(String[][] data) {
            results = data;
            fireTableDataChanged();
        }

        @Override
        public String getColumnName(int column) {
            return columnOrder[column].getName();
        }
    }

}

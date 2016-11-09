package main.view;


import main.controller.MainController;
import main.bin.RDocEnum;
import main.bin.RailroadDocument;
import main.ExceptionHandler;
import main.view.components.ColumsOrderWindow;
import main.view.components.DocPanel;
import main.view.components.DocPanelImp;
import main.view.menus.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Yuriy on 20.08.2016.
 */
public class GrahpicView extends JFrame implements View{

    private MainController controller;
    private DocPanel docPanel;
    private JButton addFilesButton;
    private JButton addFolderButton;
    private JButton saveDocumentsButton;
    private RDocEnum[] columsOrder;

    public GrahpicView(MainController controller) {
        this.controller = controller;
        docPanel = new DocPanelImp(controller);
        init();
        validate();
    }

    @Override
    public void addDocuments(List<RailroadDocument> documents) {
        for(RailroadDocument document: documents){
            docPanel.addDocument(document);
        }
        docPanel.update();
    }

    @Override
    public List<RailroadDocument> getAllDocuments() {
        return docPanel.getAllDocuments();
    }

    @Override
    public List<RailroadDocument> getSelectedDocuments() {
        return docPanel.getSelectedDocuments();
    }

    /**
     * Получение порядка столбцов.
     */
    @Override
    public RDocEnum[] getColumnOrder(){
        if(columsOrder != null)
            return columsOrder;
        else
            return RDocEnum.values();
    }

    /**
     * Изменение порядка столбцов импорта
     */
    @Override
    public void setColumnOrder(){
        columsOrder = ColumsOrderWindow.getOrder(this, columsOrder);
    }

    /*
    * Построение графического интерфейса
    */
    private void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        setTitle("Парсер жд документов версии 0.01");
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initButtons();
        addComponents();

    }

    /*
     * Добавление всех элементов на мейнфрейм
     */
    private void addComponents() {

        JPanel buttonPanel = new JPanel();
        MenuPanel menuPanel = new MenuPanel(this,controller);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addFilesButton);
        buttonPanel.add(addFolderButton);
        buttonPanel.add(saveDocumentsButton);

        getContentPane().add(menuPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(new JScrollPane(docPanel.getRootPanel()));

        revalidate();
    }

    /*
     * Инициализация и настройка слушателей для кнопок
     */
    private void initButtons() {
        addFilesButton = new JButton("Добавить документы");
        addFolderButton = new JButton("Добавить папку с документами");
        saveDocumentsButton = new JButton("Сохранить все документы в файл");

        addFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadDocsFromFiles();
            }
        });
        addFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadDocsFromFolder();
            }
        });
        saveDocumentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.saveAllDocsToFile();
            }
        });
    }


}
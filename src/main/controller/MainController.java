package main.controller;


import main.bin.RailroadDocument;
import main.model.Parser;
import main.model.Saver;
import main.view.MessageView;
import main.view.View;
import main.view.components.FileChooseDialog;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy on 14.08.2016.
 */
public class MainController {
    private View view;
    private MessageView messageView;
    private Saver saver;
    private Parser parser;

    /**
     * Загружает жд документы в программу из отдельных файлов
     * и отображает их в представлении
     */
    public void loadDocsFromFiles(){
        File[] files = FileChooseDialog.getFiles();
        List<RailroadDocument> documents = getRailroadDocuments(files);
        view.addDocuments(documents);
    }

    /**
     * Загружает жд документы в программу из папки с файлами
     * и отображает их в представлении
     */
    public void loadDocsFromFolder(){
        File folder = FileChooseDialog.getDirectory();
        List<RailroadDocument> documents = getRailroadDocuments(folder.listFiles());
        view.addDocuments(documents);
    }
    /**
     * Сохраняет выделенные документы в файл
     */
    public void saveSelectedDocsToFile(){
        List<RailroadDocument> files = view.getSelectedDocuments();
        File fileToSave = FileChooseDialog.getFileToSave();
        int docCount = saver.saveGroupToFile(files,fileToSave,view.getColumnOrder());

        messageView.showMessage(docCount + "документов успешно сохранено в файл: \n" + fileToSave.getAbsolutePath());
    }

    /**
     * Сохраняет выделенные документы по одному в отдельный файл
     * определенной папки
     */
    public void saveSelectedDocsToFolder(){
        List<RailroadDocument> documents = view.getSelectedDocuments();
        File folder = FileChooseDialog.getDirectory();
        int docCount = saver.saveGroupToFolder(documents,folder,view.getColumnOrder());
        messageView.showMessage(docCount + "документов успешно сохранено в папку: \n" + folder.getAbsolutePath());
    }

    /**
     * Сохраняет все документы в файл
     */
    public void saveAllDocsToFile(){
        List<RailroadDocument> files = view.getAllDocuments();
        File fileToSave = FileChooseDialog.getFileToSave();
        int docCount = saver.saveGroupToFile(files,fileToSave,view.getColumnOrder());

        messageView.showMessage(docCount + " документов успешно сохранено в файл: \n" + fileToSave.getAbsolutePath());
    }

    /**
     * Сохраняет все документы по одному в отдельный файл
     * определенной папки
     */
    public void saveAllDocsToFolder(){
        List<RailroadDocument> documents = view.getAllDocuments();
        File folder = FileChooseDialog.getDirectory();
        int docCount = saver.saveGroupToFolder(documents,folder,view.getColumnOrder());
        messageView.showMessage(docCount + " документов успешно сохранены в папку: \n" + folder.getAbsolutePath());
    }

    /**
     * Загружает список жд документов из передаваемого списка файлов,
     * попутно проверяя файлы.
     * @param files список системных файлов
     * @return список загруженных жд документов
     */
    private List<RailroadDocument> getRailroadDocuments(File[] files) {
        List<RailroadDocument> documents = new ArrayList<>();
        int docCount = 0;
        for(File file: files){
            if(checkFile(file,".xml"))
                try {
                    RailroadDocument doc = parser.parseFromFile(file);
                    documents.add(doc);
                    docCount++;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
        }

        messageView.showMessage("Успешно загружено " + docCount + "файлов");
        return documents;
    }

    /**
     * Проверяет файл на соответствие расширению
     * @param file - файл для проверки
     * @param fileType - разрширение файла, прим .xml
     * @return
     */
    private boolean checkFile(File file,String fileType){
        if(file.isDirectory())
            return false;
        if(!file.getName().toLowerCase().endsWith(fileType))
            return false;
        return true;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setSaver(Saver saver) {
        this.saver = saver;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setMessageView(MessageView messageView) {
        this.messageView = messageView;
    }
}

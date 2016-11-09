package main.view.components;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Yuriy on 14.08.2016.
 * Диалог для выбора файлов в системе
 * полностью завершен!!!
 * @see JFileChooser
 */
public class FileChooseDialog {

    private static File currentDir = null;
    private final static JFileChooser fileChooser = new JFileChooser(currentDir);

    /**
     * Возвращает список файлов в виде массива
     * @return File[] - Массив файлов формата xml
     */
    public static File[] getFiles() {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new MyFileFilter(".xml", "xml files"));
        fileChooser.setMultiSelectionEnabled(true);

        int choose = fileChooser.showOpenDialog(null);

        if (choose == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFiles();
        }

        throw new RuntimeException("Файлы не выбраны");
    }

    /**
     * Открывает окно для сохранения файла
     * @return File - файл для сохранения
     */
    public static File getFileToSave() {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new MyFileFilter(".xls", "xls excel files"));
        fileChooser.setMultiSelectionEnabled(false);

        int choose = fileChooser.showSaveDialog(null);
        if (choose == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        throw new RuntimeException("Файл для сохранения не выбран");
    }

    /**
     * Запускает окно выбора директории
     * @return File  - папка с файлами
     */
    public static File getDirectory() {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int choose = fileChooser.showOpenDialog(null);
        if (choose == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        throw new RuntimeException("Папка не выбрана");
    }

    /**
     * Вызывает окно выбора текущей директории
     * Устанавливает новую текущую директорию для программы
     */
    public static void setCurrentDir(){
        File dir = getDirectory();
        fileChooser.setCurrentDirectory(dir);
    }


    /*
     * Фильтр для файлов
     */
    private static class MyFileFilter extends FileFilter {
        String ext;
        String description;

        MyFileFilter(String ext, String description) {
            this.description = description;
            this.ext = ext;
        }

        @Override
        public String getDescription() {
            return description;
        }

        //Метод для любой проверки файла
        public boolean accept(File f) {
            if (f != null) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getName().toLowerCase().endsWith(ext);
            }
            return false;
        }
    }
}
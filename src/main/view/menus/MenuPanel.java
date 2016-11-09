package main.view.menus;


import main.controller.MainController;
import main.view.GrahpicView;
import main.view.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import static  main.view.menus.MenuItemType.*;

/**
 * Created by Yuriy on 22.08.2016.
 * Панель меню приложения
 */
public class MenuPanel extends JMenuBar {
    private View grahpicView;
    private MainController controller;
    private MenuListener menuListener;

    public MenuPanel(View view, MainController controller) {
        this.grahpicView = view;
        this.controller = controller;
        menuListener = new MenuListener(view,controller);
        initFileMenu();
        initExportMenu();
        initInfoMenu();
    }
    /*
     * Инициализация меню файл
     */
    private void initFileMenu(){
        JMenu fileMenu = new JMenu("Файл");
        add(fileMenu);
        //Добавление новых элементов на панель
        addMenuItem(fileMenu, ADD_FILES, menuListener);
        addMenuItem(fileMenu, ADD_FOLDER, menuListener);
        addMenuItem(fileMenu, SAVE_ALL_FILES, menuListener);
        addMenuItem(fileMenu, SAVE_SELECTED_FILES, menuListener);
        addMenuItem(fileMenu, SAVE_ALL_FILES_TO_FOLDER, menuListener);
        addMenuItem(fileMenu, SAVE_SELECTED_FILES_TO_FOLDER, menuListener);
        fileMenu.addSeparator();
        addMenuItem(fileMenu, EXIT, menuListener);
    }
    /*
     * Инициализация меню экспорта
     */
    private void initExportMenu(){
        JMenu importSettingsMenu = new JMenu("Настройки импорта");
        add(importSettingsMenu);
        addMenuItem(importSettingsMenu, SETUP_COLUMS, menuListener);
    }
    /*
     * Инициализация меню информации
     */
    private void initInfoMenu(){

    }
    private static EnumJMenuItem addMenuItem(JMenu parent, MenuItemType type, ActionListener actionListener) {
        EnumJMenuItem menuItem = new EnumJMenuItem(type);
        menuItem.addActionListener(actionListener);
        parent.add(menuItem);
        return menuItem;
    }

}

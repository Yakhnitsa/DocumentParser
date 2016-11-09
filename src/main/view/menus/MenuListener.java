package main.view.menus;


import main.controller.MainController;
import main.view.GrahpicView;
import main.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Юрий on 20.10.2016.
 */
class MenuListener implements ActionListener {
    private View gui;
    private MainController controller;

    public MenuListener(View gui, MainController controller) {
        this.gui = gui;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(!(source instanceof EnumJMenuItem)){
            throw new RuntimeException("Неподдерживаемый тип элемента меню.  требуется EnumJMenuItem");
        }
        MenuItemType event = ((EnumJMenuItem)source).getType();
        switch(event) {
            case ADD_FILES:
                controller.loadDocsFromFiles();
                break;
            case ADD_FOLDER:
                controller.loadDocsFromFolder();
                break;
            case SAVE_ALL_FILES:
                controller.saveAllDocsToFile();
                break;
            case SAVE_SELECTED_FILES:
                controller.saveSelectedDocsToFile();
                break;
            case SAVE_ALL_FILES_TO_FOLDER:
                controller.saveAllDocsToFolder();
                break;
            case SAVE_SELECTED_FILES_TO_FOLDER:
                controller.saveSelectedDocsToFolder();
                break;
            case EXIT:
//                gui.exit();
                break;
            case SETUP_COLUMS:
                gui.setColumnOrder();
            case INFO:
//                gui.showInfo();
                break;
            default:
                throw new RuntimeException("Неизвесный тип события в панели меню");
        }
    }

}

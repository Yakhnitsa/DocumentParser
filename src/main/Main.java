package main;

import main.controller.MainController;
import main.model.DocumentParser;
import main.model.DocumentSaver;
import main.view.GrahpicView;
import main.view.GraphicMessageView;
import main.view.MessageView;

public class Main {

    public static void main(String[] args) throws Exception {
        DocumentParser parser = new DocumentParser();
        DocumentSaver saver = new DocumentSaver();
        MainController controller = new MainController();

        controller.setParser(parser);
        controller.setSaver(saver);

        MessageView messageView = new GraphicMessageView();
        controller.setMessageView(messageView);

        GrahpicView gui = new GrahpicView(controller);
        controller.setView(gui);

        System.out.println("program is running...");
    }
}

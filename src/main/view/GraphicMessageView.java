package main.view;

import javax.swing.JOptionPane;

/**
 * Created by Yuriy on 22.10.2016.
 */
public class GraphicMessageView implements MessageView {
    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null,message);
    }

    @Override
    public void showProgress() {

    }
}
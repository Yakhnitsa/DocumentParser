package main.view.components;


import main.bin.RDocEnum;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Yuriy on 13.08.2016.
 * Окно, позволяющее выбирать список компонентов RDocEnum
 * для последующей обработки жд документа при сохнанении
 */
public class ColumsOrderWindow {

    public static RDocEnum[] getOrder(Frame parent,final RDocEnum[] oldOrder) {
        InsideInterface insideInterface = new InsideInterface(parent,oldOrder);
        return insideInterface.execute();
    }

    /*
     * Вложенный класс с окном:
     */
    private static class InsideInterface extends JDialog {
        private RDocEnum[] defaultOrder = RDocEnum.values();
        private RDocEnum[] selectOrder = new RDocEnum[0];

        //Правая панель, со всеми значениями
        private DefaultListModel<RDocEnum> leftListMod = new DefaultListModel();
        private JList leftList = new JList(leftListMod);

        //Левая панель, с выбранными значениями
        private DefaultListModel<RDocEnum> rightListMod = new DefaultListModel();
        private JList rightList = new JList(rightListMod);

        InsideInterface(Frame parent, RDocEnum[] order) {
            super(parent,true);
            if (order != null) {
                selectOrder = order;
            }
            buildInsideComponents();
            buildMainWindow();
        }

        private RDocEnum[] execute(){
            this.setVisible(true);
            return selectOrder;
        }
        /*
         * Получение выбранного списка компонентов
         */
        private void setNewOrder() {

            Object[] objects = rightListMod.toArray();
            RDocEnum[] result = new RDocEnum[objects.length];

            for (int i = 0; i < result.length; i++) {
                result[i] = (RDocEnum) objects[i];
            }
            selectOrder = result;
        }

        /*
         * Настройка основного окна и добавление компонентов
         */
        private void buildMainWindow() {
            setTitle("Настройка столбцов исходящего файла");
            setSize(300, 470);
            setLayout(new GridLayout(1, 2));
            add(leftList);
            add(rightList);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    setNewOrder();
                    dispose();
                }
            });
        }
        /*
         *  Настройка внутренних компонентов
         */
        private void buildInsideComponents() {
            Border listBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY);
            //Настройки правой панели:
            leftList.setBorder(listBorder);
            for (RDocEnum docEnum : defaultOrder) {
                leftListMod.addElement(docEnum);
            }
            leftList.addListSelectionListener(lftListListener);
            leftList.setCellRenderer(cellRenderer);

            //Настройки левой панели:
            rightList.setBorder(listBorder);
            for (RDocEnum docEnum : selectOrder) {
                rightListMod.addElement(docEnum);
            }
            rightList.addListSelectionListener(rghtListListener);
            rightList.setCellRenderer(cellRenderer);
        }

    /*
     * Настройка слушателей и внутренних компонентов
     */

        //Слушатель левого списка, добавляет компоненты в правую панель
        private ListSelectionListener lftListListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                    return;
                for (Object item : leftList.getSelectedValuesList()) {
                    rightListMod.addElement((RDocEnum) item);
                }
            }
        };
        //Слушатель правого списка, удаляет выделенные компоненты
        private ListSelectionListener rghtListListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                    return;
                int selectedIndex = rightList.getSelectedIndex();
                if (selectedIndex != -1) {
                    rightListMod.remove(selectedIndex);
                }
            }
        };

        //Настройка отображения элементов списка
        private ListCellRenderer<? super String> cellRenderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                return listCellRendererComponent;
            }
        };

    }

}
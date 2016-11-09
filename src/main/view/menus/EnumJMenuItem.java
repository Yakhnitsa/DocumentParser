package main.view.menus;

import javax.swing.JMenuItem;
import javax.swing.Action;
import javax.swing.Icon;
//
/**
 * Created by Юрий on 20.10.2016.
 */
class EnumJMenuItem extends JMenuItem {
    private final MenuItemType type;


    EnumJMenuItem(MenuItemType type) {
        super(type.getName());
        this.type = type;
    }

    EnumJMenuItem(MenuItemType type, Action a) {
        super(a);
        this.type = type;
    }

    EnumJMenuItem(MenuItemType type, Icon icon) {
        super(type.getName(), icon);
        this.type = type;
    }

    EnumJMenuItem(MenuItemType type, int mnemonic) {
        super(type.getName(), mnemonic);
        this.type = type;
    }

    public MenuItemType getType() {
        return type;
    }
}

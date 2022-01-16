package org.codehunter.dontstave.cheatfx.service;

import javafx.scene.control.MenuItem;
import org.codehunter.dontstave.cheatfx.model.Item;

public interface IMenuItemFactory {
    MenuItem createMenuItem(Item item);
}

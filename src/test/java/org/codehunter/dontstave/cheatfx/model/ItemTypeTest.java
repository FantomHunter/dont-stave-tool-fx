package org.codehunter.dontstave.cheatfx.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTypeTest {
    @Test
    void getItemTypeByString(){
        ItemType a = ItemType.valueOf("MAGIC");
        Assertions.assertNotNull(a);
    }
}

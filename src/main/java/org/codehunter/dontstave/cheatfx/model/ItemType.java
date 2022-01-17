package org.codehunter.dontstave.cheatfx.model;

public enum ItemType {
    DROP("drop"),
    TOOL("tool"),
    LIGHT("light"),
    FIGHT("fight"),
    REFINE("refine"),
    DRESS("dress"),
    COOK("cook"),
    MAGIC("magic"),
    SCIENCE("science"),
    SURVIVAL("survival"),
    ANCIENT("ancient"),
    TRINKET("trinket"),
    OTHER("other"),
    ;

    String name;

    ItemType(String name) {
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }
}

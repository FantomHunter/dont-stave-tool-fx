package org.codehunter.dontstave.cheatfx.model;

import org.codehunter.dontstave.cheatfx.util.JsoupUtil;

import java.io.IOException;

public record Item(String name, String code, String url, String imageUrl) {
    public Item(String name, String code, String url) {
        this(name, code, url, JsoupUtil.getOgImageUrl(url));
    }

}

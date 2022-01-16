package org.codehunter.dontstave.cheatfx;

import org.codehunter.dontstave.cheatfx.util.CsvUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class PatternTest {
    @Test
    void giveCorrectImageUrlThenPatternShouldMatched() {
        String url = "https://static.wikia.nocookie.net/dont-starve/images/3/35/Walrus_Tusk.png/revision/latest/scale-to-width-down/64?cb=20131226190239&path-prefix=vi";
        boolean matches = Pattern.matches(CsvUtil.IMAGE_URL_PATTERN, url);
        Assertions.assertTrue(matches);
    }
}

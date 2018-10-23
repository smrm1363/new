package com.mohammadreza.mirali.energyconsumption;

import java.util.Optional;

/**
 * Created by mmirali on 22/10/2018.
 */
public class MyUtil {

    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}

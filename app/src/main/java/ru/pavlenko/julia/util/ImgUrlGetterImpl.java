package ru.pavlenko.julia.util;

import java.util.Locale;

import ru.pavlenko.julia.BuildConfig;

public class ImgUrlGetterImpl implements ImgUrlGetter {
    @Override
    public String getUrl(int id) {
        return String.format(Locale.US, "%scoins/64x64/%d.png", BuildConfig.CMC_IMG_URL, id);
    }
}

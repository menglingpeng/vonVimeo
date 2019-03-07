package com.menglingpeng.vonvimeo.utils.regionpicker;

import android.support.annotation.NonNull;

public class LetterEntity implements PinYinEntity {

    public final String letter;

    public LetterEntity(String letter) { this.letter = letter; }

    @Override @NonNull
    public String getPinyin() { return letter.toLowerCase(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LetterEntity that = (LetterEntity) o;

        return letter.toLowerCase().equals(that.letter.toLowerCase());
    }

    @Override
    public int hashCode() { return letter.toLowerCase().hashCode(); }
}
}

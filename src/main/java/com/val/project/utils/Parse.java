package com.val.project.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Parse {
  public static String removeAccents(final String text) {
    String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(normalized).replaceAll("");
  }
}

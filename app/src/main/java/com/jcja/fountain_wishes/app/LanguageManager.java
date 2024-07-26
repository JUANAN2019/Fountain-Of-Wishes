package com.jcja.fountain_wishes.app;

import android.os.Build;
import android.widget.TextView;

import com.jcja.fountain_wishes.ApiGemini;

import java.util.Locale;

public class LanguageManager {
    public static final String DEFAULT_APP_LANGUAGE = "es";
    private static String translatedText;

    public interface TranslationCallback {
        void onTranslationComplete(String translatedText);}

    public static String getDefaultLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static void translateTextOnScreen(TextView textView, String languageDestination) {

        String originalText = textView.getText().toString();
        System.out.println(originalText);

        translate(textView, originalText, languageDestination, new TranslationCallback() {
            @Override
            public void onTranslationComplete(String translatedText) {
                textView.setText(translatedText); // Actualiza el TextView con el resultado
            }
        });


    }

    private  static void translate(TextView textView, String originalText, String languageDestination, TranslationCallback translationCallback) {
        ApiGemini apiGemini = new ApiGemini();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            apiGemini.CallGeminiAPI(originalText, languageDestination, new ApiGemini.ApiCallback() {
                @Override
                public void onApiResponse(String result) {
                    translationCallback.onTranslationComplete(result);
                }
            });
        }
    }
}
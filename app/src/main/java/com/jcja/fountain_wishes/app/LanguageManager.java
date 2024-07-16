package com.jcja.fountain_wishes.app;

import android.app.Activity;
import android.os.Build;
import android.widget.TextView;

import com.jcja.fountain_wishes.ApiGemini;

import java.util.Locale;

public class LanguageManager {
    public static final String DEFAULT_APP_LANGUAGE = "es";

    public static String getDefaultLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static void translateTextOnScreen(Activity activity, int textViewId, String languageDestination) {
        TextView textView = activity.findViewById(textViewId);
        String originalText = textView.getText().toString();
        System.out.println(originalText);

        // Realiza la traducción usando la API (Google Cloud Translate o Gemini)
        //String translatedText = translate(((TextView) activity.findViewById(textViewId)).getText().toString(), getDefaultLanguage());
        String translatedText = translate(activity, textView,originalText,languageDestination);

        // Actualiza el TextView con el texto traducido
        textView.setText(translatedText);
    }

    private static String translate(Activity activity,  TextView textView, String originalText, String languageDestination) {
        // Aquí usarías la API de traducción para traducir el texto// ...
        ApiGemini apiGemini = new ApiGemini();
        //String translatedText = "Texto traducido";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            return apiGemini.CallGeminiAPI(activity, textView, languageDestination); // Reemplaza con la traducción obtenida de la API
        }
        return "estas en otra version de android";
    }
}
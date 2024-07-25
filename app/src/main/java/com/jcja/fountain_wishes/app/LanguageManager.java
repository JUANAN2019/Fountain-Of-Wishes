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
        //System.out.println("translatetextonscreen");

        // Realiza la traducción usando la API (Google Cloud Translate o Gemini)
        //String translatedText = translate(((TextView) activity.findViewById(textViewId)).getText().toString(), getDefaultLanguage());
        translate(textView, originalText, languageDestination, new TranslationCallback() {
            @Override
            public void onTranslationComplete(String translatedText) {
                textView.setText(translatedText); // Actualiza el TextView con el resultado
            }
        });

        // Actualiza el TextView con el texto traducido

    }

    private  static void translate(TextView textView, String originalText, String languageDestination, TranslationCallback translationCallback) {
        // Aquí usarías la API de traducción para traducir el texto// ...
        ApiGemini apiGemini = new ApiGemini();

        //String translatedText = "Texto traducido";
        System.out.println("translate");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            final String[] texto = {""};
            apiGemini.CallGeminiAPI(originalText, languageDestination, new ApiGemini.ApiCallback() {
                @Override
                public void onApiResponse(String result) {
                    translationCallback.onTranslationComplete(result);
//                    translatedText = result;
//                    System.out.println("texto: "+ translatedText);
//                    texto[0] = result;
//                    textView.setText(texto[0]); // Actualiza el TextView con el resultado
                }
            });
            //System.out.println("texto: "+ texto[0]);

            //return texto[0];
           // return translatedText;
            //return apiGemini.CallGeminiAPI(activity, textView, languageDestination); // Reemplaza con la traducción obtenida de la API
        }
        //return "estas en otra version de android";
    }
}
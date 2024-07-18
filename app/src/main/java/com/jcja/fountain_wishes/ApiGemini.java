package com.jcja.fountain_wishes;

import static com.jcja.fountain_wishes.BuildConfig.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.android.gms.fitness.data.Value;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.checkerframework.common.value.qual.PolyValue;

import java.util.concurrent.CountDownLatch;

public class ApiGemini {
    // ... (otros miembros de la clase) ...
    private String resultText;


    @RequiresApi(api = Build.VERSION_CODES.P)
    public String CallGeminiAPI(Activity activity, TextView textView, String languageDestination) {

        // ... (inicialización de GenerativeModel y GenerativeModelFutures)...
        System.out.println("callgemini");
        GenerativeModel gm = new GenerativeModel(/* modelName */ "gemini-pro",
                /* apiKey */ "AIzaSyCQHATpQfoyXOG5dkGoXbk6EUMeEqq6tO4");

        GenerativeModelFutures model = GenerativeModelFutures.from(gm);
        Content emptyContent = new Content.Builder().build();


        CountDownLatch latch = new CountDownLatch(1); // Inicializa el CountDownLatch
        Content content = new Content.Builder()
                .addText("¿Cuanto son 2 mas 2?")
                .build();
        new AsyncTask<Void, Void, GenerateContentResponse>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected GenerateContentResponse doInBackground(Void... voids) {
                try {
                    System.out.println("try");
                    return model.generateContent(content).get();
                } catch (Exception e) {
                    System.out.println("Error en la llamada a la API: " + e.getMessage());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(GenerateContentResponse response) {
                if (response != null) {
                    System.out.println("Respuesta de la API: " + response.getText());
                    resultText = response.getText();
                    // Actualiza la interfaz de usuario aquí, por ejemplo:
                    // textView.setText(response.getText());
                } else {
                    System.out.println("No se pudo obtener una respuesta de la API");
                }
            }
        }.execute();
        return resultText;
    }
}

//        try {
//            // Llamada a la API de Gemini fuera de onSuccess
//            System.out.println("try");
//            GenerateContentResponse response = model.generateContent(content).get();
//            System.out.println("Respuesta de la API: " + response.getText());
//        } catch (Exception e) {
//            System.out.println("Error en la llamada a la API: " + e.getMessage());
//        } finally {
//            System.out.println("Bloque finally ejecutado");
//        }
//
//        try {
//            ListenableFuture<GenerateContentResponse> response = model.generateContent(emptyContent);
//            Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
//                @Override
//                public void onSuccess(GenerateContentResponse result) {
//                    // ... (construcción de Content) ...
//                    Content content = new Content.Builder() // Define content aquí
//                            .addText("traduce este texto que te voy a pasar a español. texto: " + "me llamo pepe")
//                            .build();
//
//                    ListenableFuture<GenerateContentResponse> newResponse = model.generateContent(content);
//                    System.out.println("sout primer on succes");
//                    Futures.addCallback(newResponse, new FutureCallback<GenerateContentResponse>() {
//                        @Override
//                        public void onSuccess(GenerateContentResponse finalResult) {
//                            resultText = finalResult.getText();
//                            latch.countDown(); // Decrementa el CountDownLatch cuando la respuesta esté disponible
//                            System.out.println("sout primer on succes");
//
//                        }
//
//                        @Override
//                        public void onFailure(Throwable t) {
//                            Log.e("ApiGemini", "Error al llamar a la API de Gemini 1on failure", t);
//                            textView.setText("Error en la traducción: " + t.getMessage());
//                            latch.countDown(); // Decrementa el CountDownLatch en caso de error
//                            System.out.println("sout primer on failure");
//                        }}, ContextCompat.getMainExecutor(activity));
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//                    Log.e("ApiGemini", "Error al llamar a la API de Gemini", t);
//                    latch.countDown(); // Decrementa el CountDownLatch en caso de error
//                    System.out.println("sout segundo on failure");
//                }
//            }, ContextCompat.getMainExecutor(activity));
//        } catch (Exception e) {
//            System.out.println("Error en model.generateContent: " + e.getMessage());
//            // Maneja el error de forma apropiada, por ejemplo, mostrando un mensaje al usuario
//            latch.countDown(); // Asegúrate de decrementar el latch en caso de error
//        }
//
//        try {
//            latch.await(); // Espera a que el CountDownLatch llegue a 0 (respuesta disponible)
//            System.out.println("sout try");
//            return resultText; // Devuelve la respuesta
//        } catch (InterruptedException e) {
//            // Maneja la interrupción
//            System.out.println("sout catch");
//            return "Error: " + e.getMessage();
//        }



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
    private ApiCallback callback;

    public interface ApiCallback {
        void onApiResponse(String result);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void CallGeminiAPI(String originalText, String languageDestination, ApiCallback callback) {
        this.callback = callback;
        //String textoOriginal = textView.getText().toString();
        // ...(inicialización de GenerativeModel y GenerativeModelFutures)...
        System.out.println("callgemini");
        GenerativeModel gm = new GenerativeModel(/* modelName */ "gemini-pro",
                /* apiKey */ "AIzaSyCQHATpQfoyXOG5dkGoXbk6EUMeEqq6tO4");

        GenerativeModelFutures model = GenerativeModelFutures.from(gm);
        Content emptyContent = new Content.Builder().build();

        Content content = new Content.Builder()
                .addText("traduce el texto que te voy a pasar a " + languageDestination + ". texto: " + originalText )
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
                    //System.out.println("Respuesta de la API: " + response.getText());
                    resultText = response.getText();
                    callback.onApiResponse(resultText);
                } else {
                    callback.onApiResponse("Error: No se pudo obtener una respuesta de la API");
                }
            }
        }.execute();
    }
}




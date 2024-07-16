package com.jcja.fountain_wishes;

import android.app.Activity;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class ApiGemini {
    // ... (otros miembros de la clase) ...
    private String resultText;
    private TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public String CallGeminiAPI(Activity activity, TextView textView, String languageDestination) {
        // For text-only input, use the gemini-pro model

        GenerativeModel gm = new GenerativeModel(/* modelName */ "gemini-1.5-flash-latest",
                /* apiKey */ "*************");

        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        Content emptyContent = new Content.Builder().build();

        // Llama a generateContent con null temporalmente
        ListenableFuture<GenerateContentResponse> response = model.generateContent(emptyContent);
        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                // Construye Content aquí, después de que textView esté inicializado
                Content content = new Content.Builder()
                        .addText("traduce este texto que te voy a pasar a español. texto: " + textView.getText().toString())
                        .build();

                // Llama a generateContent con el Content construido
                ListenableFuture<GenerateContentResponse> newResponse = model.generateContent(content);

                // Maneja la respuesta de newResponse (puedes usar otro FutureCallback o un mecanismo de sincronización)
                // ...
            }

            @Override
            public void onFailure(Throwable t) {
                textView.setText(t.toString());
            }
        }, ContextCompat.getMainExecutor(activity));

        return resultText; // Puedes devolver un valor temporal o null aquí
    }

}





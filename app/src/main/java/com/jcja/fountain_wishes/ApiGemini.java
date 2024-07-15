package com.jcja.fountain_wishes;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class ApiGemini extends AppCompatActivity {
    private String resultText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.select);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public String CallGeminiAPI(

    ){
        // For text-only input, use the gemini-pro model
        GenerativeModel gm = new GenerativeModel(/* modelName */ "gemini-pro",
// Access your API key as a Build Configuration variable (see "Set up your API key" above)
                /* apiKey */ "+++++++++++++++++++++");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);
        Content content = new Content.Builder()
                .addText("traduce este texto que te voy a pasar a español. texto: " + textView.getText().toString())
                .build();

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                resultText = result.getText();
                textView.setText(resultText);
            }
            @Override
            public void onFailure(Throwable t) {
                textView.setText(t.toString());
            }
        }, this.getMainExecutor());

        return resultText;
    }

}

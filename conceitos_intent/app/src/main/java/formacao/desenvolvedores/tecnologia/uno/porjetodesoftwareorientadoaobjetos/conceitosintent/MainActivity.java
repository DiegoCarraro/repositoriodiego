package formacao.desenvolvedores.tecnologia.uno.porjetodesoftwareorientadoaobjetos.conceitosintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import formacao.desenvolvedores.tecnologia.uno.porjetodesoftwareorientadoaobjetos.conceitosintent.utils.app.UtilsApp;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "";
    private Button btnPerguntar;
    private TextView tvExibirRespota;
    private TextView tvTitulo;
    private EditText edtPergunta;
    private ImageButton btnDelete;
    public static final int REQUEST_CODE = 5;
    private ActivityResultLauncher<Intent> activityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPerguntar = findViewById(R.id.btnPerguntar);
        tvExibirRespota = findViewById(R.id.tvExibirResposta);
        edtPergunta = findViewById(R.id.edtPergunta);
        btnDelete = findViewById(R.id.btnDelete);
        tvTitulo = findViewById(R.id.tvTitulo);

        tvTitulo.setVisibility(View.INVISIBLE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {

        } else {

        }

        btnPerguntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtPergunta.getText().toString().isEmpty()) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                        Intent intent = new Intent(MainActivity.this, RespostaActivity.class);

                        String conteudo = edtPergunta.getText().toString();
                        intent.putExtra("Pergunta", conteudo);

                        if (!tvExibirRespota.getText().toString().isEmpty()) {
                            String myResposta = tvExibirRespota.getText().toString();
                            intent.putExtra("Resposta", myResposta);
                        }
                        startActivityForResult(intent, REQUEST_CODE);

                    } else {
                       openActivityForResult();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Insira uma pergunta", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPergunta.setText("");
                tvExibirRespota.setText("");
            }
        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode()== Activity.RESULT_OK) {
                            Intent data = result.getData();
                            //tvExibirRespota.setText(data.getExtras().toString());
                            tvTitulo.setVisibility(View.VISIBLE);
                            tvExibirRespota.setText(data.getExtras().getString("returnData"));
                        }

                   }
                }
        );
        UtilsApp utilsApp = new UtilsApp();
        Log.d(TAG, "Valor convertido de tipos primitivos float para int "
                + utilsApp.convertToInt(5.197));

        byte b = -27;
        Log.d(TAG, "Valor convertido de tipos primitivos byte para int "
                + utilsApp.convertToInt(b));

        short valorShort = -1500;
        Log.d(TAG, "Valor convertido de tipos primitivos short para int "
                + utilsApp.convertToInt(valorShort));

        char F = 15;
        Log.d(TAG, "Valor convertido de tipos primitivos char para int "
                + utilsApp.convertToInt(F));

        long valorLong = 9223372036854775800L;
        Log.d(TAG, "Valor convertido de tipos primitivos long para int "
                + utilsApp.convertToInt(valorLong));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == REQUEST_CODE) && (resultCode == RESULT_OK)) {

            String returnString = data.getExtras().getString("returnData");


            if (returnString != null) {
                if (!returnString.isEmpty()) {
                    tvTitulo.setVisibility(View.VISIBLE);
                    edtPergunta.setText(returnString);

                }
            }

            tvExibirRespota.setText(returnString);
        }
    }

    private void openActivityForResult() {//aqui tem a assinatura e sua complementação
        Intent intent = new Intent(MainActivity.this, RespostaActivity.class);
        intent.putExtra("Pergunta", edtPergunta.getText().toString());

        activityResultLauncher.launch(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(activityResultLauncher != null){
            activityResultLauncher.unregister();
        }
    }
}


package carraro.diego.uno.calcular;//package carraro.diego.uno.calcular;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String TAG           = "MainActivity";
    public static final String DIVISAO       = "Divisão";
    public static final String MULTIPLICACAO = "Multiplicação";
    public static final String SOMA          = "Soma";
    public static final String SUBTRACAO     = "Subtração";
    private Spinner spiOpcoes;
    private Button btnCalcular;
    private EditText edtOperando1, edtOperando2;
    private ImageView imgOperacao, imgView2;
    private TextView tvResultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        spiOpcoes       = findViewById(R.id.spiOpcoes);
        btnCalcular     = findViewById(R.id.btnCalcular);
        edtOperando1    = findViewById(R.id.edtOperando1);
        edtOperando2    = findViewById(R.id.edtOperando2);
        imgOperacao     = findViewById(R.id.imgOperacao);
        imgView2        = findViewById(R.id.imageView2);
        tvResultado     = findViewById(R.id.tvResultado);


        imgOperacao.setVisibility(View.INVISIBLE);


        ArrayAdapter<String> adapterOperacoes = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item,
        getResources().getStringArray(R.array.operacoes_matematica));
        adapterOperacoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spiOpcoes.setAdapter(adapterOperacoes);
        spiOpcoes.setOnItemSelectedListener(this);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String operacaoSelecionada = spiOpcoes.getSelectedItem().toString();

                if (operacaoSelecionada == DIVISAO) {// == "divisão"

                } else if (operacaoSelecionada == MULTIPLICACAO) {// == "multiplicação"

                } else if (operacaoSelecionada.equals(SOMA)) {// == "somar'
                    tvResultado.setText(somar());

                } else if (operacaoSelecionada == SUBTRACAO) {// == "subtração"

                } else {
                    Toast.makeText(MainActivity.this, "Por favor selecione uma opção matemática",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        //Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(),
        //Toast.LENGTH_SHORT).show();
        imgOperacao.setVisibility(View.VISIBLE);

        if(adapterView.getItemAtPosition(i).toString().equals(DIVISAO)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.divisao, getTheme()));

        } else if(adapterView.getItemAtPosition(i).toString().equals(MULTIPLICACAO)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.multiplica, getTheme()));

        } else if(adapterView.getItemAtPosition(i).toString().equals(SOMA)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.soma, getTheme()));

        } else if(adapterView.getItemAtPosition(i).toString().equals(SUBTRACAO)){
            imgOperacao.setImageDrawable(getResources().getDrawable(R.drawable.subtracao, getTheme()));

        } else{
            Log.d(TAG, "Nenhuma opção foi selecionada.");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private String somar(){
        String resultado = "";
        int n1 = Integer.valueOf(edtOperando1.getText().toString());
        int n2 = Integer.valueOf(edtOperando2.getText().toString());

        return resultado;
    }

}
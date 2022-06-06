package carraro.diego.uno.calcular;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    public static final String DIVISAO = "Divisão";
    public static final String MULTIPLICAO = "Multiplicação";
    public static final String SOMA = "Soma";
    public static final String SUBTRACAO = "Subtração";
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
                android.R.layout.simple_spinner_item
                , getResources().getStringArray(R.array.operacoes_matematica));
        adapterOperacoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spiOpcoes.setAdapter(adapterOperacoes);
        spiOpcoes.setOnItemSelectedListener(this);

        // PEGA A OPÇÃO SELECIONADA DO SPINNER
        String operacaoSelecionada = spiOpcoes.getSelectedItem().toString();

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (operacaoSelecionada == DIVISAO) {// == "divisao"
                }
                if (operacaoSelecionada == MULTIPLICAO) {// == "multiplicação"
                }
                if (operacaoSelecionada == SOMA) {// == "somar'
                }
                if (operacaoSelecionada == SUBTRACAO) {// == "subtração"
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(MainActivity.this, adapterView.getItemIdAtPosition(i).toString(),
                Toast.LENGTH_SHORT).show();

        if (adapterView.getItemIdAtPosition(i).toString() == DIVISAO{
            imgOperacao.setImageDrawable();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}
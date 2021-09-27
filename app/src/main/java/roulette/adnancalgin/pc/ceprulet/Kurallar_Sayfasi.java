package roulette.adnancalgin.pc.ceprulet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Kurallar_Sayfasi extends AppCompatActivity {

    TextView black,red,even,odd,firsthalf,secondhalf,first12,second12,third12,number,cevap1,cevap2,cevap3,cevap4,cevap5,cevap6,cevap7,cevap8,cevap9,cevap10,eglence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurallar__sayfasi);

        black = findViewById(R.id.black);
        red = findViewById(R.id.red);
        even = findViewById(R.id.even);
        odd = findViewById(R.id.odd);
        firsthalf = findViewById(R.id.firsthalf);
        secondhalf = findViewById(R.id.secondhalf);
        first12 = findViewById(R.id.first12);
        second12 = findViewById(R.id.second12);
        third12 = findViewById(R.id.third12);
        number = findViewById(R.id.number);
        cevap1 = findViewById(R.id.cevap1);
        cevap2 = findViewById(R.id.cevap2);
        cevap3 = findViewById(R.id.cevap3);
        cevap4 = findViewById(R.id.cevap4);
        cevap5 = findViewById(R.id.cevap5);
        cevap6 = findViewById(R.id.cevap6);
        cevap7 = findViewById(R.id.cevap7);
        cevap8 = findViewById(R.id.cevap8);
        cevap9 = findViewById(R.id.cevap9);
        cevap10 = findViewById(R.id.cevap10);



    }
}

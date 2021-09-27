package roulette.adnancalgin.pc.ceprulet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> selection = new ArrayList<String>();

    Button spin;
    TextView textView, tv_Kurallar, placebet, tv_gold;
    ImageView iv_wheel, iv_gold;
    CheckBox cb_red, cb_black, cb_even, cb_odd, cb_firsthalf, cb_secondhalf;
    EditText et_miktar;

    Random r;

    int degree = 0, degree_old = 0;
    private Wheel wheel;
    private int goldValue = 0 ;


    private static final float FACTOR = 4.86f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wheel = new Wheel();

        spin = (Button) findViewById(R.id.spin);
        tv_Kurallar = (TextView) findViewById(R.id.tv_Kurallar);
        textView = (TextView) findViewById(R.id.textView);
        placebet = (TextView) findViewById(R.id.placebet);
        tv_gold = (TextView) findViewById(R.id.tv_gold);
        et_miktar = (EditText) findViewById(R.id.et_miktar);
        iv_wheel = (ImageView) findViewById(R.id.iv_wheel);
        iv_gold = (ImageView) findViewById(R.id.iv_gold);
        cb_red = (CheckBox) findViewById(R.id.cb_red);
        cb_black = (CheckBox) findViewById(R.id.cb_black);
        cb_even = (CheckBox) findViewById(R.id.cb_even);
        cb_odd = (CheckBox) findViewById(R.id.cb_odd);
        cb_firsthalf = (CheckBox) findViewById(R.id.cb_firsthalf);
        cb_secondhalf = (CheckBox) findViewById(R.id.cb_secondhalf);


        r = new Random();
        tv_Kurallar.setOnClickListener(this);
        spin.setOnClickListener(this);
        tv_gold.setText(String.valueOf(goldValue));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_Kurallar:
                startActivity(new Intent(this, Kurallar_Sayfasi.class));
                break;
            case R.id.spin:
                onSpinClick();
                break;

        }
    }

    private void onSpinClick() {
        Integer.parseInt(et_miktar.getText().toString());
        degree_old = degree % 360;
        degree = r.nextInt(3600) + 720;
        RotateAnimation rotate = new RotateAnimation(degree_old, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                textView.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setText(currentNummber(360 - (degree % 360)));
                calculatePrice();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv_wheel.startAnimation(rotate);
    }
    private void calculatePrice() {
        String wonText = "";
        String loseText = "";
        int miktar = Integer.parseInt(et_miktar.getText().toString());
        int selectedNumber = Integer.parseInt(et_miktar.getText().toString());
        // 0 black, 1 red, 2 even, 3 odd , 4 1-19,  19-36
        if (wheel.getNumber() == selectedNumber){
            int won = miktar * 1;
            wonText = String.valueOf(won) + " Kazandınız!";
            Toast.makeText(this, wonText, Toast.LENGTH_LONG).show();
            changeGoldValue(won, false);
        }
        if (wheel.getColorState() == Wheel.ColorState.BLACK && getSelectedItem() == 0) {
            int won = miktar * 2;
            wonText = String.valueOf(won) + " Kazandınız!";
            Toast.makeText(this, wonText, Toast.LENGTH_LONG).show();
            changeGoldValue(won, false);
        } else if (wheel.getColorState() == Wheel.ColorState.RED && getSelectedItem() == 1) {
            int won = miktar * 2;
            wonText = String.valueOf(won) + " Kazandınız!";
            Toast.makeText(this, wonText, Toast.LENGTH_LONG).show();
            changeGoldValue(won, false);
        } else if (wheel.getNumberState() == Wheel.NumberState.EVEN && getSelectedItem() == 2) {
            int won = miktar * 2;
            wonText = String.valueOf(won) + " Kazandınız!";
            Toast.makeText(this, wonText, Toast.LENGTH_LONG).show();
            changeGoldValue(won, false);
        } else if (wheel.getNumberState() == Wheel.NumberState.ODD && getSelectedItem() == 3) {
            int won = miktar * 2;
            wonText = String.valueOf(won) + " Kazandınız!";
            Toast.makeText(this, wonText, Toast.LENGTH_LONG).show();
            changeGoldValue(won, false);
        } else if (wheel.getBetweenState() == Wheel.BetweenState.FIRSTHALF && getSelectedItem() == 4) {
            int won = miktar * 2;
            wonText = String.valueOf(won) + " Kazandınız!";
            Toast.makeText(this, wonText, Toast.LENGTH_LONG).show();
            changeGoldValue(won, false);
        } else if (wheel.getBetweenState() == Wheel.BetweenState.SECONDHALF && getSelectedItem() == 5) {
            int won = miktar * 2;
            wonText = String.valueOf(won) + " Kazandınız!";
            Toast.makeText(this, wonText, Toast.LENGTH_LONG).show();
            changeGoldValue(won, false);
        } else {
            changeGoldValue(miktar, true);
            int lose = miktar;
            loseText = String.valueOf(lose) + " Kaybettiniz!";
            Toast.makeText(this, loseText , Toast.LENGTH_LONG).show();
        }
    }

    private void changeGoldValue(int gold, boolean isLose) {
        if (isLose) {
            goldValue -= gold;
            if (goldValue < -1000) {
                int borc_farki = goldValue + 1000;
                Toast.makeText(this,
                        "Çok kaybettin ekstra ödemen gereken değer :" + String.valueOf(borc_farki), Toast.LENGTH_LONG).show();
                goldValue = -1000;
            }
        } else {
            goldValue += gold;
        }
        tv_gold.setText(String.valueOf(goldValue));
    }


    private String currentNummber(int degrees) {
        String text = "";

        if (degrees >= (FACTOR * 1) && degrees < (FACTOR * 3)) {
            text = "32  RED  EVEN  19-36  3'RD 12";
            wheel.setNumber(32);
            wheel.setBetween(32);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 3) && degrees < (FACTOR * 5)) {
            text = "15  BLACK  ODD  1-18  2'ND 12";
            wheel.setNumber(15);
            wheel.setBetween(15);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 5) && degrees < (FACTOR * 7)) {
            text = "19  RED  ODD  19-36  2'ND 12";
            wheel.setNumber(19);
            wheel.setBetween(9);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 7) && degrees < (FACTOR * 9)) {
            text = "4  BLACK  EVEN  1-18  1'ST 12";
            wheel.setNumber(4);
            wheel.setBetween(4);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 9) && degrees < (FACTOR * 11)) {
            text = "21  RED  ODD  19-36  2'ND 12";
            wheel.setNumber(21);
            wheel.setBetween(21);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 11) && degrees < (FACTOR * 13)) {
            text = "2  BLACK  EVEN  1-18  1'ST 12";
            wheel.setNumber(2);
            wheel.setBetween(2);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 13) && degrees < (FACTOR * 15)) {
            text = "25  RED  ODD  19-36  3'RD 12";
            wheel.setNumber(25);
            wheel.setBetween(25);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 15) && degrees < (FACTOR * 17)) {
            text = "17  BLACK  ODD  1-18  2'ND 12";
            wheel.setNumber(17);
            wheel.setBetween(17);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 17) && degrees < (FACTOR * 19)) {
            text = "34  RED  EVEN  19-36  3'RD 12";
            wheel.setNumber(34);
            wheel.setBetween(34);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 19) && degrees < (FACTOR * 21)) {
            text = "6  BLACK  EVEN  1-18  1'ST 12";
            wheel.setNumber(6);
            wheel.setBetween(6);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 21) && degrees < (FACTOR * 23)) {
            text = "27  RED  ODD  19-36  3'RD 12";
            wheel.setNumber(27);
            wheel.setBetween(27);
            wheel.setColorState(Wheel.ColorState.RED);

        }
        if (degrees >= (FACTOR * 23) && degrees < (FACTOR * 25)) {
            text = "13  BLACK  ODD 1-18  2'ND 12";
            wheel.setNumber(13);
            wheel.setBetween(13);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 25) && degrees < (FACTOR * 27)) {
            text = "36  RED  EVEN  19-36  3'ND 12";
            wheel.setNumber(36);
            wheel.setBetween(36);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 27) && degrees < (FACTOR * 29)) {
            text = "11  BLACK  ODD  1-18  1'ST 12";
            wheel.setNumber(11);
            wheel.setBetween(11);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 29) && degrees < (FACTOR * 31)) {
            text = "30  RED  EVEN  19-36  3'RD 12";
            wheel.setNumber(30);
            wheel.setBetween(30);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 31) && degrees < (FACTOR * 33)) {
            text = "8  BLACK  EVEN  1-18  1'ST 12";
            wheel.setNumber(8);
            wheel.setBetween(8);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 33) && degrees < (FACTOR * 35)) {
            text = "23  RED  ODD  19-36  2'ND 12";
            wheel.setNumber(23);
            wheel.setBetween(23);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 35) && degrees < (FACTOR * 37)) {
            text = "10  BLACK  EVEN  1-18  1'ST 12";
            wheel.setNumber(10);
            wheel.setBetween(10);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 37) && degrees < (FACTOR * 39)) {
            text = "5  RED  ODD  1-18  1'ST 12";
            wheel.setNumber(5);
            wheel.setBetween(5);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 39) && degrees < (FACTOR * 41)) {
            text = "24  BLACK  EVEN  19-39  2'ND 12";
            wheel.setNumber(24);
            wheel.setBetween(24);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 41) && degrees < (FACTOR * 43)) {
            text = "16  RED  EVEN  1-18  2'ND 12";
            wheel.setNumber(16);
            wheel.setBetween(16);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 43) && degrees < (FACTOR * 45)) {
            text = "33  BLACK  ODD  19-36  3'RD 12";
            wheel.setNumber(33);
            wheel.setBetween(33);
            wheel.setColorState(Wheel.ColorState.BLACK);

        }
        if (degrees >= (FACTOR * 45) && degrees < (FACTOR * 47)) {
            text = "1  RED  ODD  1-18  1'ST  12";
            wheel.setNumber(1);
            wheel.setBetween(1);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 47) && degrees < (FACTOR * 49)) {
            text = "20  BLACK  EVEN  19-36  2'ND 12";
            wheel.setNumber(20);
            wheel.setBetween(20);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 49) && degrees < (FACTOR * 51)) {
            text = "14  RED  EVEN  1-18  1'ST 12";
            wheel.setNumber(14);
            wheel.setBetween(14);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 51) && degrees < (FACTOR * 53)) {
            text = "31  BLACK  ODD  19-36  3'RD 12";
            wheel.setNumber(31);
            wheel.setBetween(31);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 53) && degrees < (FACTOR * 55)) {
            text = "9  RED  ODD  1-18  1'ST 12";
            wheel.setNumber(9);
            wheel.setBetween(9);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 55) && degrees < (FACTOR * 57)) {
            text = "22  BLACK  EVEN  19-36  2'ND 12";
            wheel.setNumber(22);
            wheel.setBetween(22);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 57) && degrees < (FACTOR * 59)) {
            text = "18  RED  EVEN  1-18  2'ND 12";
            wheel.setNumber(18);
            wheel.setBetween(18);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 59) && degrees < (FACTOR * 61)) {
            text = "29  BLACK  ODD  19-36  3'RD 12";
            wheel.setNumber(29);
            wheel.setBetween(29);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 61) && degrees < (FACTOR * 63)) {
            text = "7  RED  ODD  1-18  1'ST 12";
            wheel.setNumber(7);
            wheel.setBetween(7);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 63) && degrees < (FACTOR * 65)) {
            text = "28  BLACK  EVEN  19-36  2'ND 12";
            wheel.setNumber(28);
            wheel.setBetween(28);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 65) && degrees < (FACTOR * 67)) {
            text = "12  RED  EVEN  1-18  1'ST 12";
            wheel.setNumber(12);
            wheel.setBetween(12);
            wheel.setColorState(Wheel.ColorState.RED);
        }
        if (degrees >= (FACTOR * 67) && degrees < (FACTOR * 69)) {
            text = "35  BLACK  ODD  19-36  3'RD 12";
            wheel.setNumber(35);
            wheel.setBetween(35);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if (degrees >= (FACTOR * 69) && degrees < (FACTOR * 71)) {
            text = "3  RED ODD  1-18  1'ST RD 12";
            wheel.setNumber(3);
            wheel.setBetween(3);
            wheel.setColorState(Wheel.ColorState.RED);
        }

        if (degrees >= (FACTOR * 71) && degrees < (FACTOR * 73)) {
            text = "26  BLACK  EVEN  19-36  2'RD 12";
            wheel.setNumber(26);
            wheel.setBetween(26);
            wheel.setColorState(Wheel.ColorState.BLACK);
        }
        if ((degrees >= (FACTOR * 73) && degrees < 360) || (degrees >= 0 && degrees < (FACTOR * 1))) {
            text = "0 GREEN";
            wheel.setNumber(0);
            wheel.setBetween(0);
            wheel.setColorState(Wheel.ColorState.GREEN);
        }
        return text;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.example_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item1:

                startActivity(new Intent(this, Kurallar_Sayfasi.class));

                break;

            case R.id.item2:
                startActivity(new Intent(this,iletisim_sayfasi.class));

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public int getSelectedItem() {
        boolean black = cb_black.isChecked();
        boolean red = cb_red.isChecked();
        boolean even = cb_even.isChecked();
        boolean odd = cb_odd.isChecked();
        boolean firsthalf = cb_firsthalf.isChecked();
        boolean secondhalf = cb_secondhalf.isChecked();

        if (black) {
            return 0;
        }
        if (red) {
            return 1;
        }
        if (even) {
            return 2;
        }
        if (odd) {
            return 3;
        }
        if (firsthalf) {
            return 4;
        }
        if (secondhalf) {
            return 5;
        }


        return -1;

//        boolean checked = ((CheckBox) view).isChecked();
//        switch (view.getId()) {
//            case R.id.cb_black:
//                if (checked) {
//                    return 0;
//                } else {
//
//                }
//                break;
//            case R.id.cb_red:
//                if (checked) {
//                    return 1;
//                } else {
//
//                }
//                break;
//            case R.id.cb_even:
//                if (checked) {
//                    return 2;
//                } else {
//
//                }
//                break;
//        }
    }

}






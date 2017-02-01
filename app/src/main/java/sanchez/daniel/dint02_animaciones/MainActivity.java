package sanchez.daniel.dint02_animaciones;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public boolean rotar1 = false;
    public boolean rotar2 = false;
    public boolean rotar3 = false;

    public int result1, result2, result3;

    public class Columna extends AsyncTask<Integer, Integer, Integer> {
        int column;

        protected Integer doInBackground(Integer... integers) { //Podría ser como el constructor del AsynTask. Esto es un hilo
            Long progress = 0L;
            column = integers[0];

            while ((column == 1 && rotar1) || (column == 2 && rotar2) || (column == 3 && rotar3)) {
                progress = (System.currentTimeMillis() % 9); //Se obtienen números de 0 a 8, los números que tenemos en array. Siempre sale un número menos como máximo del q se pone
                publishProgress(progress.intValue());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return progress.intValue(); //Devuelve el último valor de la fila central antes de cambiar el boolean a false
        }

        protected void onProgressUpdate(Integer... progress) { //Esto es otro hilo
            int value = progress[0];
            int prev = value == 0 ? 8 : value - 1;
            int post = value == 8 ? 0 : value + 1;

            if (column == 1) {
                frames[0].setImageDrawable(images[0][prev]);
                frames[1].setImageDrawable(images[0][value]);
                frames[2].setImageDrawable(images[0][post]);
            } else if (column == 2) {
                frames[3].setImageDrawable(images[1][prev]);
                frames[4].setImageDrawable(images[1][value]);
                frames[5].setImageDrawable(images[1][post]);
            } else if (column == 3) {
                frames[6].setImageDrawable(images[2][prev]);
                frames[7].setImageDrawable(images[2][value]);
                frames[8].setImageDrawable(images[2][post]);
            }
        }

        protected void onPostExecute(Integer result) { //Hilo principal. Se ejecuta con el return del doInBlackground
            //Se almacena el valor central de cada columna y en caso de estar las 3
            if (column == 1) {
                result1 = values[0][result];
            } else if (column == 2) {
                result2 = values[1][result];
            } else if (column == 3) {
                result3 = values[2][result];
            }
            if (!rotar1 && !rotar2 && !rotar3) {
                TextView t = (TextView) findViewById(R.id.txt);
                if(result1==result2&&result2==result3){
                    t.setText("Ganaste, ¡Enhorabuena!");
                }
                else{
                    t.setText("Suerte la próxima vez");
                }
            }
        }
    }

    ImageView[] frames;
    Drawable[][] images;
    Integer[][] values;
    Columna c1, c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frames = new ImageView[9];
        images = new Drawable[3][9];
        values = new Integer[3][9];

        frames[0] = (ImageView) findViewById(R.id.imageView11);
        frames[1] = (ImageView) findViewById(R.id.imageView12);
        frames[2] = (ImageView) findViewById(R.id.imageView13);
        frames[3] = (ImageView) findViewById(R.id.imageView21);
        frames[4] = (ImageView) findViewById(R.id.imageView22);
        frames[5] = (ImageView) findViewById(R.id.imageView23);
        frames[6] = (ImageView) findViewById(R.id.imageView31);
        frames[7] = (ImageView) findViewById(R.id.imageView32);
        frames[8] = (ImageView) findViewById(R.id.imageView33);

        //Inicialización columna 1 (Hilo 1)
        images[0][0] = getResources().getDrawable(R.drawable.tragaperras1);
        values[0][0] = 1;
        images[0][1] = getResources().getDrawable(R.drawable.tragaperras2);
        values[0][1] = 2;
        images[0][2] = getResources().getDrawable(R.drawable.tragaperras3);
        values[0][2] = 3;
        images[0][3] = getResources().getDrawable(R.drawable.tragaperras4);
        values[0][3] = 4;
        images[0][4] = getResources().getDrawable(R.drawable.tragaperras5);
        values[0][4] = 5;
        images[0][5] = getResources().getDrawable(R.drawable.tragaperras6);
        values[0][5] = 6;
        images[0][6] = getResources().getDrawable(R.drawable.tragaperras7);
        values[0][6] = 7;
        images[0][7] = getResources().getDrawable(R.drawable.tragaperras8);
        values[0][7] = 8;
        images[0][8] = getResources().getDrawable(R.drawable.tragaperras9);
        values[0][8] = 9;

        //Inicialización columna 2
        images[1][0] = getResources().getDrawable(R.drawable.tragaperras3);
        values[1][0] = 3;
        images[1][1] = getResources().getDrawable(R.drawable.tragaperras5);
        values[1][1] = 5;
        images[1][2] = getResources().getDrawable(R.drawable.tragaperras7);
        values[1][2] = 7;
        images[1][3] = getResources().getDrawable(R.drawable.tragaperras9);
        values[1][3] = 9;
        images[1][4] = getResources().getDrawable(R.drawable.tragaperras1);
        values[1][4] = 1;
        images[1][5] = getResources().getDrawable(R.drawable.tragaperras2);
        values[1][5] = 2;
        images[1][6] = getResources().getDrawable(R.drawable.tragaperras4);
        values[1][6] = 4;
        images[1][7] = getResources().getDrawable(R.drawable.tragaperras6);
        values[1][7] = 6;
        images[1][8] = getResources().getDrawable(R.drawable.tragaperras8);
        values[1][8] = 8;

        //Inicialización columna 3
        images[2][0] = getResources().getDrawable(R.drawable.tragaperras4);
        values[2][0] = 4;
        images[2][1] = getResources().getDrawable(R.drawable.tragaperras7);
        values[2][1] = 7;
        images[2][2] = getResources().getDrawable(R.drawable.tragaperras1);
        values[2][2] = 1;
        images[2][3] = getResources().getDrawable(R.drawable.tragaperras5);
        values[2][3] = 5;
        images[2][4] = getResources().getDrawable(R.drawable.tragaperras8);
        values[2][4] = 8;
        images[2][5] = getResources().getDrawable(R.drawable.tragaperras2);
        values[2][5] = 2;
        images[2][6] = getResources().getDrawable(R.drawable.tragaperras6);
        values[2][6] = 6;
        images[2][7] = getResources().getDrawable(R.drawable.tragaperras9);
        values[2][7] = 9;
        images[2][8] = getResources().getDrawable(R.drawable.tragaperras3);
        values[2][8] = 3;

        //Inicialización a los recursos del layout


        //Inicialización de la interfaz y el juego
        frames[0].setImageDrawable(images[0][0]);
        frames[1].setImageDrawable(images[0][1]);
        result1 = values[0][1];
        frames[2].setImageDrawable(images[0][2]);
        frames[3].setImageDrawable(images[1][0]);
        frames[4].setImageDrawable(images[1][1]);
        result2 = values[1][1];
        frames[5].setImageDrawable(images[1][2]);
        frames[6].setImageDrawable(images[2][0]);
        frames[7].setImageDrawable(images[2][1]);
        result3 = values[2][1];
        frames[8].setImageDrawable(images[2][2]);


    }

    public void firstColumn(View v){
        if(!rotar1){
            c1 = new Columna();
            c1.execute(1);
            rotar1=true;
            ((Button) v).setText("Parar");
            TextView t = (TextView) findViewById(R.id.txt);
            t.setText("Partida iniciada, ¡Buena Suerte!");
        }
        else{
            rotar1=false;
            ((Button) v).setText("Jugar");
        }
    }

    public void secondColumn(View v){
        if(!rotar2){
            c2 = new Columna();
            c2.execute(2);
            rotar2=true;
            ((Button) v).setText("Parar");
            TextView t = (TextView) findViewById(R.id.txt);
            t.setText("Partida iniciada, ¡Buena Suerte!");
        }
        else{
            rotar2=false;
            ((Button) v).setText("Jugar");
        }
    }

    public void thirdColumn(View v){
        if(!rotar3){
            c3 = new Columna();
            c3.execute(3);
            rotar3=true;
            ((Button) v).setText("Parar");
            TextView t = (TextView) findViewById(R.id.txt);
            t.setText("Partida iniciada, ¡Buena Suerte!");
        }
        else{
            rotar3=false;
            ((Button) v).setText("Jugar");
        }
    }
}
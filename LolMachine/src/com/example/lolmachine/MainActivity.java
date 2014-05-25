package com.example.lolmachine;

import android.os.Bundle;
import android.os.Handler;
import android.os.IInterface;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	boolean StopR1;
	boolean StopR2;
	boolean StopR3;
	int premio;
	final Handler handler1 = new Handler();
	final Handler handler2 = new Handler();
	final Handler handler3 = new Handler();
	Runnable runnableObject1;
	Runnable runnableObject2;
	Runnable runnableObject3;
	final giro juego = new giro();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		setContentView(R.layout.activity_main);
		
		int x = 0;	
		final Handler handler1 = new Handler();
		final Handler handler2 = new Handler();
		final Handler handler3 = new Handler();
		final ImageView rueda1 = (ImageView) findViewById(R.id.imageView1);
		final ImageView rueda2 = (ImageView) findViewById(R.id.imageView2);
		final ImageView rueda3 = (ImageView) findViewById(R.id.imageView3);
		ImageButton Bplay = (ImageButton) findViewById(R.id.playbutton);
		runnableObject1 = new Runnable() {
			public void run() {

				rueda1.setImageResource(imagen((int) (Math.random() * 8) + 1));

				handler1.postDelayed(this, 100);
			}
		};// rueda1

		runnableObject2 = new Runnable() {
			public void run() {

				rueda2.setImageResource(imagen((int) (Math.random() * 8) + 1));
				handler2.postDelayed(this, 100);
			}
		};// rueda2
		runnableObject3 = new Runnable() {
			public void run() {

				rueda3.setImageResource(imagen((int) (Math.random() * 8) + 1));
				handler3.postDelayed(this, 100);
			}
		};

		rueda1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				handler1.removeCallbacks(runnableObject1);
				rueda1.setImageResource(imagen(juego.a));
				setBoleanT(1);
				if (alltrue()) {
					if (premio > 0) {
						Intent i = new Intent(MainActivity.this,
								WinDialogActivity.class);
						Log.i("LolMachine", "iniciando activity");
						i.putExtra("premio", premio);
						startActivity(i);
					}
					StopR1 = false;
					StopR2 = false;
					StopR3 = false;
				}
				Log.i("LolMachine", "valor de a es:" + juego.a);
			}
		});
		rueda2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				handler2.removeCallbacks(runnableObject2);
				rueda2.setImageResource(imagen(juego.b));
				setBoleanT(2);
				if (alltrue()) {
					if (premio > 0) {
						Intent i = new Intent(MainActivity.this,
								WinDialogActivity.class);
						Log.i("LolMachine", "iniciando activity");
						i.putExtra("premio", premio);
						startActivity(i);
					}
					StopR1 = false;
					StopR2 = false;
					StopR3 = false;
				}
				Log.i("LolMachine", "valor de b es:" + juego.b);
			}
		});
		rueda3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				handler3.removeCallbacks(runnableObject3);
				rueda3.setImageResource(imagen(juego.c));
				setBoleanT(3);
				if (alltrue()) {
					if (premio > 0) {
						Intent i = new Intent(MainActivity.this,
								WinDialogActivity.class);
						
						Log.i("LolMachine", "iniciando activity");
						i.putExtra("premio", premio);
						startActivity(i);
					}
					StopR1 = false;
					StopR2 = false;
					StopR3 = false;
				} else {
					Log.i("LolMachine", "NO activity");
				}
				Log.i("LolMachine", "valor de c es:" + juego.c);
			}
		});

		Bplay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				handler3.postDelayed(runnableObject3, 100);
				handler2.postDelayed(runnableObject2, 100);
				handler1.postDelayed(runnableObject1, 100);
				juego.caja++;
				juego.premios[1] = (int) (juego.caja * .85);
				premio = juego.juego();
			}
		});
	}
	
	

	public void setBoleanT(int i) {
		if (i == 1) {
			StopR1 = true;
			Log.i("LolMachine", "rueda1: " + StopR1);
		}
		if (i == 2) {
			StopR2 = true;
			Log.i("LolMachine", "rueda2: " + StopR2);
		}
		if (i == 3) {
			StopR3 = true;
			Log.i("LolMachine", "rueda3: " + StopR3);
		}
	}

	public boolean alltrue() {
		boolean x = false;
		Log.i("LolMachine", "SR1= " + StopR1 + " SR2= " + StopR2 + "SR3= "
				+ StopR3);
		if (StopR1 && StopR2 && StopR3) {
			if (StopR1) {
				return true;
			}
		}
		return x;
	}
	


	public int imagen(int x) {
		int id_imagen = 0;
		// Log.i("LolMachine", "valor de x es:" + x);

		switch (x) {
		case 2:
			id_imagen = getResources().getIdentifier("adc", "drawable",
					getPackageName());
			// Log.i("LolMachine", "valor del id =" + id_imagen);
			break;
		case 6:
			id_imagen = getResources().getIdentifier("support", "drawable",
					getPackageName());
			break;
		case 5:
			id_imagen = getResources().getIdentifier("mid", "drawable",
					getPackageName());
			break;
		case 3:
			id_imagen = getResources().getIdentifier("top", "drawable",
					getPackageName());
			break;
		case 4:
			id_imagen = getResources().getIdentifier("jungler", "drawable",
					getPackageName());
			break;
		case 1:
			id_imagen = getResources().getIdentifier("victory", "drawable",
					getPackageName());
			break;
		case 8:
			id_imagen = getResources().getIdentifier("defeat", "drawable",
					getPackageName());
			break;
		case 7:
			id_imagen = getResources().getIdentifier("ward", "drawable",
					getPackageName());
			break;
		}

		return id_imagen;

	}

	

}

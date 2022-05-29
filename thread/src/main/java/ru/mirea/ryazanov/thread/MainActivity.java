package ru.mirea.ryazanov.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Текущий поток: " + mainThread.getName());
        mainThread.setName("MGUPIThread");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
    }
    public void onClick(View view) {
        Runnable runnable = new Runnable() {
            public void run()
            {
                int numberThread = count++;
                Log.i("ThreadProject", "Запущен поток № " + numberThread);
                long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime)
                {
                    synchronized (this) {
                        try
                        {
                            wait(endTime -
                                    System.currentTimeMillis());
                        }
                        catch
                        (Exception e)
                        {

                        }
                    }
                }
                Log.i("ThreadProject", "Выполнен поток № " + numberThread);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
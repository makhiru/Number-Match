package com.example.picturematch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtname, txtcount, txtcounttime;
    RecyclerView recyclers;
    Adapter_Recycler adapter;
    SeekBar seekBar;
    Runnable runnable;
    Handler handler;
    boolean win = false;

    int count = 0;
    int click = 1;
    int pos1;
    int pos2;
    View view1;
    int check;
    int l, max;

    ArrayList arrimg = new ArrayList();
    ArrayList arrtxt = new ArrayList();

    ArrayList<Integer> arrimage = new ArrayList<>();

    int[] image = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
            R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.zero};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtname = findViewById(R.id.txtname);
        txtcount = findViewById(R.id.txtcount);
        txtcounttime = findViewById(R.id.txtcounttime);
        recyclers = findViewById(R.id.recyclers);
        seekBar = findViewById(R.id.seekbar);

        l = getIntent().getIntExtra("key", 0);

        if (l == 1) {
            max = 15;
            txtcounttime.setText("0/15");
        } else if (l == 2) {
            max = 20;
            txtcounttime.setText("0/20");
        } else if (l == 3) {
            max = 25;
            txtcounttime.setText("0/25");
        }

        seekBar.setMax(max);
        seekBar.setProgress(5);
        handler = new Handler();
        runnable = new Runnable() {

            int h = 5;
            int t = -1;

            @Override
            public void run() {

                if (h > 0) {
                    h--;
                    seekBar.setProgress(h);
                }
                if (h == 0 && !win) {
                    t++;
                    seekBar.setProgress(t);
                    if (l == 1) {
                        txtcounttime.setText(t + "/" + max);
                    } else if (l == 2) {
                        txtcounttime.setText(t + "/" + max);
                    } else if (l == 3) {
                        txtcounttime.setText(t + "/" + max);
                    }
                }


                if (t == max) {
                    Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.timelimit_dialog);
                    dialog.create();
                    dialog.setCancelable(false);
                    dialog.findViewById(R.id.over_cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    dialog.findViewById(R.id.over_ok).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            recreate();
                        }
                    });

                    dialog.show();
                }
                handler.postDelayed(this::run, 1000);
            }
        };
        handler.post(runnable);

        count = 0;

        if (l == 1) {
            for (int i = 0; i < 12; i++) {
                arrimage.add(i);
            }
        } else if (l == 2) {
            for (int i = 0; i < 16; i++) {
                arrimage.add(i);
            }
        } else if (l == 3) {
            for (int i = 0; i < 20; i++) {
                arrimage.add(i);
            }
        }

        while (true) {
            if (l == 1) {
                int i = new Random().nextInt(image.length); //iamge
                int iv1 = new Random().nextInt(6); //imageview
                int iv2 = new Random().nextInt(12 - 6) + 6; //imageview

                if (!arrtxt.contains(i) && !arrimg.contains(iv1) && !arrimg.contains(iv2)) {
                    arrimage.set(iv1, image[i]);
                    arrimg.add(iv1);
                    arrimage.set(iv2, image[i]);
                    arrimg.add(iv2);

                    arrtxt.add(i);

                    if (arrtxt.size() >= 6) {
                        break;
                    }
                }
            } else if (l == 2) {
                int i = new Random().nextInt(image.length); //iamge
                int iv1 = new Random().nextInt(8); //imageview
                int iv2 = new Random().nextInt(16 - 8) + 8; //imageview

                if (!arrtxt.contains(i) && !arrimg.contains(iv1) && !arrimg.contains(iv2)) {
                    arrimage.set(iv1, image[i]);
                    arrimg.add(iv1);
                    arrimage.set(iv2, image[i]);
                    arrimg.add(iv2);

                    arrtxt.add(i);

                    if (arrtxt.size() >= 8) {
                        break;
                    }
                }
            } else if (l == 3) {
                int i = new Random().nextInt(image.length); //iamge
                int iv1 = new Random().nextInt(10); //imageview
                int iv2 = new Random().nextInt(20 - 10) + 10; //imageview

                if (!arrtxt.contains(i) && !arrimg.contains(iv1) && !arrimg.contains(iv2)) {
                    arrimage.set(iv1, image[i]);
                    arrimg.add(iv1);
                    arrimage.set(iv2, image[i]);
                    arrimg.add(iv2);

                    arrtxt.add(i);

                    if (arrtxt.size() >= 10) {
                        break;
                    }
                }
            }
        }
        if (l == 1) {
            recyclers.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            adapter = new Adapter_Recycler(MainActivity.this, arrimage, listner);
            recyclers.setAdapter(adapter);
        } else if (l == 2) {
            recyclers.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
            adapter = new Adapter_Recycler(MainActivity.this, arrimage, listner);
            recyclers.setAdapter(adapter);
        } else if (l == 3) {
            recyclers.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
            adapter = new Adapter_Recycler(MainActivity.this, arrimage, listner);
            recyclers.setAdapter(adapter);
        }
        txtcount = findViewById(R.id.txtcount);
    }

    Recycler_Clicklistner listner = new Recycler_Clicklistner() {
        @Override
        public void onClick(List<Integer> image, View view, int pos) {
            if (click == 1) {
                count++;
                view.setVisibility(View.INVISIBLE);
                click = 3;
                view1 = view;
                pos1 = pos;
                txtcount.setText(String.valueOf(count));


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        click = 2;
                    }
                }, 200);
            } else if (click == 2) {
                count++;
                view.setVisibility(View.INVISIBLE);
                click = 3;
                pos2 = pos;
                txtcount.setText(String.valueOf(count));

                if (image.get(pos1).equals(image.get(pos2))) {
                    view.setClickable(false);
                    view1.setClickable(false);
                    click = 1;
                    check++;

                    if (check == (image.size() / 2)) {

                        win = true;
                        Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.win_dialog);

                        Button btnok = dialog.findViewById(R.id.btnok);
                        Button btnrestart = dialog.findViewById(R.id.btnrestart);

                        TextView txttap = dialog.findViewById(R.id.txttap);
                        txttap.setText(String.valueOf(count));

                        TextView txttime = dialog.findViewById(R.id.txttime);
                        txttime.setText(txtcounttime.getText().toString());

                        btnok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();
                                finish();
                            }
                        });

                        btnrestart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();
                                recreate();
                            }
                        });

                        dialog.show();
                    }


                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(View.VISIBLE);
                            view1.setVisibility(View.VISIBLE);
                            click = 1;

                        }
                    }, 300);
                }
            }
        }
    };

}
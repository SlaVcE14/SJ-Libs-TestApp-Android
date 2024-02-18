package com.sjapps.testapp.firework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sjapps.library.firework.Firework;

import java.util.ArrayList;
import java.util.Random;

public class Renderer extends View {

    int width = 0;
    int height = 0;
    Random r = new Random();
    ArrayList<Firework> fireworks = new ArrayList<>();
    ArrayList<Firework> removeFireworks = new ArrayList<>();

    EditText c1, c2, lines;
    boolean randomLines;

    public Renderer(Context context) {
        super(context);
        createViews(context);
    }

    public Renderer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createViews(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (width == 0 && height == 0){
            width = this.getWidth();
            height = this.getHeight();
        }

        if (r.nextInt(50) == 4 && fireworks.size() < 2){
            int color1;
            int color2;
            try {
                color1 = Color.parseColor(c1.getText().toString());
            }catch (IllegalArgumentException e){
                color1 = Color.parseColor("#FFC18B30");
            }
            try {
                color2 = Color.parseColor(c2.getText().toString());
            }catch (IllegalArgumentException e){
                color2 = Color.parseColor("#FFFFDA94");
            }

            Firework firework = new Firework(r.nextInt(width),r.nextInt(height),color1,color2);

            if (randomLines)
                firework.setLineNum((r.nextInt(2) +2) * 10);
            else {
                int l;
                try {
                    l = Integer.parseInt(lines.getText().toString());
                    if (l == 0)
                        l = 20;
                }catch (NumberFormatException e){
                    l = 20;
                }
                firework.setLineNum(l);
            }
            firework.setType(r.nextInt(2));
            fireworks.add(firework.create());
        }

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        for (Firework f : fireworks){

            f.update(canvas,paint);
            if (f.isFinished())
                removeFireworks.add(f);
        }


        fireworks.removeAll(removeFireworks);

        removeFireworks.clear();
        invalidate();
    }

    private void createViews(Context context){

        LinearLayout main = new LinearLayout(context);
        main.setOrientation(LinearLayout.VERTICAL);
        LinearLayout colorLL = new LinearLayout(context);

        TextView titleColor1 = new TextView(context);
        titleColor1.setText("color1");
        TextView titleColor2 = new TextView(context);
        titleColor2.setText("color2");
        c1 = new EditText(context);
        c1.setText("#C18B30");
        c2 = new EditText(context);
        c2.setText("#FFDA94");

        c1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                c1.setTextColor((charSequence.length()<7||charSequence.length()>9||c1.getText().toString().contains(" "))? Color.RED: Color.WHITE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        c2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                c2.setTextColor((charSequence.length()<7||charSequence.length()>9||c2.getText().toString().contains(" "))? Color.RED: Color.WHITE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        LinearLayout linesLL = new LinearLayout(context);
        TextView titleLines = new TextView(context);
        titleLines.setText("num lines");
        lines = new EditText(context);
        lines.setText("20");

        CheckBox cb = new CheckBox(context);
        cb.setText("random");
        cb.setOnCheckedChangeListener((compoundButton, b) -> {
            lines.setVisibility(b?GONE:VISIBLE);
            randomLines = b;
        });
        cb.setChecked(true);

        colorLL.addView(titleColor1);
        colorLL.addView(c1);
        colorLL.addView(titleColor2);
        colorLL.addView(c2);
        linesLL.addView(titleLines);
        linesLL.addView(lines);
        linesLL.addView(cb);


        main.addView(colorLL);
        main.addView(linesLL);

        LinearLayout root = ((FireworkActivity) context).rootView;
        root.addView(main);

    }
}

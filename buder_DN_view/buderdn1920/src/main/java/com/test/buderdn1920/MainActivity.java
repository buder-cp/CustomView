package com.test.buderdn1920;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.table);
        recyclerView.setAdapter(new MyAdapter(this, 50));
//        handler.sendEmptyMessageDelayed(1, 8000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            recyclerView.setAdapter(new MyAdapter(MainActivity.this, 1111111150));
            super.handleMessage(msg);
        }
    };

    class MyAdapter implements Adapter {
        LayoutInflater inflater;
        private int height;
        private int count;

        public MyAdapter(Context context, int count) {
            Resources resources = context.getResources();
            height = resources.getDimensionPixelSize(R.dimen.table_height);
            inflater = LayoutInflater.from(context);
            this.count = count;
        }

        @Override
        public int getCount() {
            return count;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_table1, parent, false);
            }
            //
            TextView textView = (TextView) convertView.findViewById(R.id.text1);
            textView.setText("第 " + position + "行");
            return convertView;
        }

        @Override
        public int getItemViewType(int row) {
            return 0;
        }

        @Override
        public int getHeight(int index) {
            return height;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }
    }

}

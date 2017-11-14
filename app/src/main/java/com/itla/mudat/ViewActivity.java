package com.itla.mudat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

 private TextView txvertexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        txvertexto=(TextView) findViewById(R.id.Tvviestext);

        Bundle viewdata =new Bundle();
        viewdata=         getIntent().getExtras();
    txvertexto.setText(viewdata.getString("nombre").toString());
    }
}

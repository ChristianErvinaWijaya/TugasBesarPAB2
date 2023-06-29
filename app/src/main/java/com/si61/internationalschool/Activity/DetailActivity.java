package com.si61.internationalschool.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.si61.internationalschool.R;



public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvAlamat, tvNoTelepon, tvFasilitas;
    private String yId, yNama, yAlamat, yNotelp, yFasilitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tv_nama);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvNoTelepon = findViewById(R.id.tv_noTelepon);
        tvFasilitas = findViewById(R.id.tv_fasilitas);

        Intent tangkap = getIntent();
        yId = tangkap.getStringExtra("xId");
        yNama = tangkap.getStringExtra("xNama");
        yAlamat = tangkap.getStringExtra("xAlamat");
        yNotelp = tangkap.getStringExtra("xNoTelp");
        yFasilitas = tangkap.getStringExtra("xFasilitas");

        tvNama.setText(yNama);
        tvAlamat.setText(yAlamat);
        tvNoTelepon.setText(yNotelp);
        tvFasilitas.setText(yFasilitas);
    }
}
package com.si61.internationalschool.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.si61.internationalschool.API.APIRequestData;
import com.si61.internationalschool.API.RetroServer;
import com.si61.internationalschool.Adapter.AdapterSekolah;
import com.si61.internationalschool.Model.ModelResponse;
import com.si61.internationalschool.Model.ModelSekolah;
import com.si61.internationalschool.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvSekolah;
    private ProgressBar pbSekolah;
    private FloatingActionButton fabTambah;
    private RecyclerView.Adapter adSekolah;
    private RecyclerView.LayoutManager lmSekolah;
    private List<ModelSekolah> listsekolah = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSekolah = findViewById(R.id.rv_sekolah);
        pbSekolah = findViewById(R.id.pb_sekolah);
        fabTambah = findViewById(R.id.fab_tambah);
        lmSekolah = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSekolah.setLayoutManager(lmSekolah);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

    }

    public void retrieveSekolah(){
        pbSekolah.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listsekolah = response.body().getData();

                adSekolah = new AdapterSekolah(MainActivity.this, listsekolah);
                rvSekolah.setAdapter(adSekolah);
                adSekolah.notifyDataSetChanged();

                pbSekolah.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                pbSekolah.setVisibility(View.GONE);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveSekolah();
    }
}
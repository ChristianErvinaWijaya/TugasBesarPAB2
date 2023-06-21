package com.si61.internationalschool.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.si61.internationalschool.API.APIRequestData;
import com.si61.internationalschool.API.RetroServer;
import com.si61.internationalschool.Model.ModelResponse;
import com.si61.internationalschool.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etAlamat, etNoTelepon, etFasilitas;
    private Button btnSimpan;
    private String nama, alamat, noTelepon, fasilitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etNoTelepon = findViewById(R.id.et_noTelepon);
        etFasilitas = findViewById(R.id.et_fasilitas);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                noTelepon = etNoTelepon.getText().toString();
                fasilitas = etFasilitas.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama Harus Diisi");
                }
                else if (alamat.trim().isEmpty()){
                    etAlamat.setError("Alamat Harus Diisi");
                }
                else if (noTelepon.trim().isEmpty()){
                    etNoTelepon.setError("No. Telepon Harus Diisi");
                }
                else if (fasilitas.trim().isEmpty()){
                    etFasilitas.setError("Fasilitas Harus Diisi");
                }
                else {
                    prosesSimpan();
                }
            }
        });
    }

    private void prosesSimpan(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardCreate(nama, alamat, noTelepon, fasilitas);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : " + kode + " Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
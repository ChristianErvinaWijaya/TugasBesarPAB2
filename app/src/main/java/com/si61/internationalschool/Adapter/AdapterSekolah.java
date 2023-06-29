package com.si61.internationalschool.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.si61.internationalschool.API.APIRequestData;
import com.si61.internationalschool.API.RetroServer;
import com.si61.internationalschool.Activity.DetailActivity;
import com.si61.internationalschool.Activity.MainActivity;
import com.si61.internationalschool.Activity.TambahActivity;
import com.si61.internationalschool.Activity.UbahActivity;
import com.si61.internationalschool.Model.ModelResponse;
import com.si61.internationalschool.Model.ModelSekolah;
import com.si61.internationalschool.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterSekolah extends RecyclerView.Adapter<AdapterSekolah.VHSekolah>{
    private Context ctx;
    private List<ModelSekolah> listsekolah;

    public AdapterSekolah(Context ctx, List<ModelSekolah> listsekolah){
        this.ctx = ctx;
        this.listsekolah = listsekolah;
    }

    @NonNull
    @Override
    public VHSekolah onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_sekolah, parent, false);
        return new VHSekolah(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHSekolah holder, int position) {
        ModelSekolah MW = listsekolah.get(position);

        holder.tvId.setText(MW.getId());
        holder.tvNama.setText(MW.getNama());
        holder.tvAlamat.setText(MW.getAlamat());
        holder.tvNoTelp.setText(MW.getNoTelepon());
        holder.tvFasilitas.setText(MW.getFasilitas());


    }

    @Override
    public int getItemCount() {
        return listsekolah.size();
    }

    public class VHSekolah extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvAlamat, tvNoTelp, tvFasilitas;

        public VHSekolah(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvNoTelp = itemView.findViewById(R.id.tv_noTelepon);
            tvFasilitas = itemView.findViewById(R.id.tv_fasilitas);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Anda memilih "+ tvNama.getText().toString() +", Operasi Apa yang Akan Dilakukan ?");

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent kirim = new Intent(ctx, UbahActivity.class);
                            kirim.putExtra("xId", tvId.getText().toString());
                            kirim.putExtra("xNama", tvNama.getText().toString());
                            kirim.putExtra("xAlamat", tvAlamat.getText().toString());
                            kirim.putExtra("xNoTelp", tvNoTelp.getText().toString());
                            kirim.putExtra("xFasilitas", tvFasilitas.getText().toString());
                            ctx.startActivity(kirim);
                        }
                    });
                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            prosesHapus(tvId.getText().toString());

                        }
                    });
                    pesan.show();
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent kirim = new Intent(ctx, DetailActivity.class);
                    kirim.putExtra("xId", tvId.getText().toString());
                    kirim.putExtra("xNama", tvNama.getText().toString());
                    kirim.putExtra("xAlamat", tvAlamat.getText().toString());
                    kirim.putExtra("xNoTelp", tvNoTelp.getText().toString());
                    kirim.putExtra("xFasilitas", tvFasilitas.getText().toString());
                    ctx.startActivity(kirim);
                }
            });
        }

        void prosesHapus(String id){
            APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = API.ardDelete(id);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode : " + kode + " Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveSekolah();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx,"Gagal Menghubungi Server: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}

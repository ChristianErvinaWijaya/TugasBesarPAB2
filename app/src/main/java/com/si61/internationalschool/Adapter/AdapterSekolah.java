package com.si61.internationalschool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.si61.internationalschool.Model.ModelSekolah;
import com.si61.internationalschool.R;

import java.util.List;

public class AdapterSekolah extends RecyclerView.Adapter<AdapterSekolah.VHSekolah>{
    private Context ctx;
    private List<ModelSekolah> listsekolah;

    public AdapterSekolah(Context ctx, List<ModelSekolah> listwisata){
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
        }
    }
}

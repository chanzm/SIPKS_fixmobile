package id.ac.its.attendance.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.List;

import id.ac.its.attendance.R;
import id.ac.its.attendance.Response.Pengajuan.Pengajuan;
import id.ac.its.attendance.Response.Pengajuan.ResponsePengajuan;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.AdapterHolder>{

    private Context context;
    private List<Pengajuan> dataList;

    public ListAdapter(Context context, List<Pengajuan> datalist){
        this.context = context;
        this.dataList = datalist;
    }
    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent,false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
//    memetakan data taruh ke view
    public void onBindViewHolder(@NonNull final AdapterHolder holder, int position) {
        final Pengajuan pengajuan = dataList.get(position);
        String title = pengajuan.getJudulPengajuan();
        int body = pengajuan.getJumlahPengajuan();
        String tanggal =  pengajuan.getCreateTime();


        holder.judul.setText(title);
        holder.jumlah.setText(String.valueOf(body));
        holder.tanggal.setText(tanggal);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailPengajuanActivity.class);
                intent.putExtra("id",pengajuan.getIdPengajuan());
                intent.putExtra("konfirmasi",pengajuan.getStatusPengajuan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    //menghitung listnya
    public int getItemCount() {
        return dataList.size();
    }


    //memetakan view ke dalam objek
    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView judul, jumlah, tanggal;
        CardView item;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item);
            judul = itemView.findViewById(R.id.list_judul);
            jumlah = itemView.findViewById(R.id.list_total);
            tanggal = itemView.findViewById(R.id.list_tanggal);

        }
    }
}

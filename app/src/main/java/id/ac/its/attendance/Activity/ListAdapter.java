package id.ac.its.attendance.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        final Pengajuan pengajuan = dataList.get(position);
        String title = pengajuan.getJudulPengajuan();
        int body = pengajuan.getJumlahPengajuan();
        Object tanggal =  pengajuan.getCreatedAt();

//        holder.judul.setText(title);
    }

    @Override
    //menghitung listnya
    public int getItemCount() {
        return dataList.size();
    }


    //memetakan view ke dalam objek
    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView judul, jumlah, tanggal;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.list_judul);
            jumlah = itemView.findViewById(R.id.list_total);
            tanggal = itemView.findViewById(R.id.list_tanggal);

        }
    }
}

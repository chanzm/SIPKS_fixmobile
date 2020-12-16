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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.List;

import id.ac.its.attendance.R;
import id.ac.its.attendance.Response.DetailPengajuan.DetailPengajuan;
import id.ac.its.attendance.Response.DetailPengajuan.ResponseDetailPengajuan;
import id.ac.its.attendance.Response.Pengajuan.Pengajuan;

public class ListAdapterDetail extends RecyclerView.Adapter<ListAdapterDetail.AdapterHolder>{
    private Context context;
    private List<DetailPengajuan> dataList;

    public ListAdapterDetail(Context context, List<DetailPengajuan> datalist){
        this.context = context;
        this.dataList = datalist;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_detail, parent,false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
//    memetakan data taruh ke view
    public void onBindViewHolder(@NonNull final ListAdapterDetail.AdapterHolder holder, int position) {
        final DetailPengajuan detailpengajuan = dataList.get(position);
        String nama_detail = detailpengajuan.getNamaDetail();
        int hargasatu = detailpengajuan.getHargaSatuan();
        int jumlah = detailpengajuan.getJumlahDetail();
        int total_harga = detailpengajuan.getSubTotal();

        holder.nama.setText(nama_detail);
        holder.hargasatu.setText(String.valueOf(hargasatu));
        holder.jumlah.setText(String.valueOf(jumlah));
        holder.total.setText(String.valueOf(total_harga));

//        holder.konfirmasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context,DetailPengajuanActivity.class);
//                intent.putExtra("id",detailpengajuan.getIdPengajuan());
//                intent.putExtra("konfirmasi",detailpengajuan.getStatusPengajuan());
//                context.startActivity(intent);
//            }
//        });
    }


    @Override
    //menghitung listnya
    public int getItemCount() {
        return dataList.size();
    }


    //memetakan view ke dalam objek
    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView nama,hargasatu,jumlah,total;
        ConstraintLayout item_detail;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            item_detail = itemView.findViewById(R.id.item_detail);
            nama = itemView.findViewById(R.id.list_nama_detail);
            hargasatu = itemView.findViewById(R.id.list_harga_satuan);
            jumlah = itemView.findViewById(R.id.list_jumlah);
            total = itemView.findViewById(R.id.list_total);

        }
    }

}

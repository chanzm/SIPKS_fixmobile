package id.ac.its.attendance.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.its.attendance.R;
import id.ac.its.attendance.Response.DetailPengajuan.DetailPengajuan;
import id.ac.its.attendance.Response.Pengajuan.Pengajuan;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.AdapterHolder>{
    private Context context;
    private List<DetailPengajuan> dataList;

    public RecycleAdapter(Context context, List<DetailPengajuan> datalist){
        this.context = context;
        this.dataList = datalist;
    }
//    @NonNull
//    @Override
//    public ListAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent,false);
//        ListAdapter.AdapterHolder holder = new ListAdapter.AdapterHolder(view);
//        return holder;
//    }

    @NonNull
    @Override
    public RecycleAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_detail, parent,false);
        RecycleAdapter.AdapterHolder holder = new RecycleAdapter.AdapterHolder(view);
        return holder;
    }



    @Override
//    memetakan data taruh ke view
    public void onBindViewHolder(@NonNull final RecycleAdapter.AdapterHolder holder, int position) {
        final DetailPengajuan detailPengajuan = dataList.get(position);
        String title = detailPengajuan.getNamaDetail();
        int satuan = detailPengajuan.getHargaSatuan();
        int jumlah = detailPengajuan.getJumlahDetail();
        int total =  detailPengajuan.getSubTotal();


        holder.judul.setText(title);
        holder.jumlah.setText(String.valueOf(satuan));
        holder.satuan.setText(jumlah);
        holder.total.setText(total);

//        holder.item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context,DetailPengajuanActivity.class);
//                intent.putExtra("id",detailPengajuan.getIdPengajuan());
//                intent.putExtra("konfirmasi",pengajuan.getStatusPengajuan());
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
        TextView judul, satuan, jumlah, total;
        CardView item;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item_list_detail);
            judul = itemView.findViewById(R.id.list_nama_detail);
            satuan = itemView.findViewById(R.id.list_harga_satuan);
            jumlah = itemView.findViewById(R.id.list_jumlah);
            total = itemView.findViewById(R.id.list_total);

        }
    }
}

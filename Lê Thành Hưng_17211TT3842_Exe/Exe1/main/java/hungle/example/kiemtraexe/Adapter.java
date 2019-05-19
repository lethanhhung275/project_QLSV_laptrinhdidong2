package hungle.example.kiemtraexe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter  extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    Context context;
    ArrayList<NhanVien> data;

    public Adapter(Context context, ArrayList<NhanVien> data) {
        this.context = context;
        this.data = data;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        switch (i){
            case 1: itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item_layout_nam, viewGroup,false);
            break;
            case 2: itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item_layout_nu, viewGroup,false);
            break;
            default: itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item_layout_nam, viewGroup,false);
                break;
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).bGT){
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txtMaNV.setText(data.get(i).getsMaNV());
        myViewHolder.txtHoTen.setText(data.get(i).getsHoTen());
        myViewHolder.txtPhong.setText(data.get(i).getsPhong());
        myViewHolder.imageView.setImageResource(data.get(i).getIcon());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtMaNV, txtHoTen, txtPhong;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaNV = itemView.findViewById(R.id.tvMaNV);
            txtHoTen = itemView.findViewById(R.id.tvHoTen);
            txtPhong = itemView.findViewById(R.id.tvPhong);
            imageView = itemView.findViewById(R.id.image1);
        }

    }
}

package hungle.example.kiemtraexe;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter  extends ArrayAdapter {

    Context context;
    int layoutResourceId;
    ArrayList<NhanVien> data = null;

    public Adapter(Context context, int layoutResourceId, ArrayList<NhanVien> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    static class NVHolder{
        ImageView imgIcon;
        TextView txtMaNV, txtHoTen, txtPhong, txtSdt;
    }


    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        View row = convertView;
        NVHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new NVHolder();
            holder.imgIcon = row.findViewById(R.id.image1);
            holder.txtMaNV = row.findViewById(R.id.tvMaNV);
            holder.txtHoTen = row.findViewById(R.id.tvHoTen);
            holder.txtPhong = row.findViewById(R.id.tvPhong);
            holder.txtSdt = row.findViewById(R.id.tvSdt);

            row.setTag(holder);
        } else {
            holder = (NVHolder)row.getTag();
        }

        NhanVien item = data.get(position);
        holder.txtMaNV.setText(item.getsMaNV());
        holder.txtHoTen.setText(item.getsHoTen());
        holder.txtPhong.setText(item.getsPhong());
        holder.txtSdt.setText(item.getsSdt());
        if (item.isbGT()){
            holder.imgIcon.setImageResource(R.drawable.nam);
        }else {
            holder.imgIcon.setImageResource(R.drawable.nu);
        }
        return row;
    }
}

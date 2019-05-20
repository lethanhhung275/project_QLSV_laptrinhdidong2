package hungle.example.project_nhom8;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    Context context;
    int layoutResourceId;
    ArrayList<SinhVien> data = null;

    public SinhVienAdapter(Context context, int layoutResourceId, ArrayList<SinhVien> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    static class SinhVienHolder {
        ImageView img;
        TextView txt1, txt2, txt3;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SinhVienHolder holder = null;

        if(row != null)
        {
            holder = (SinhVienHolder) row.getTag();
        }
        else
        {
            holder = new SinhVienHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.listview_item_layout_sinhvien, parent, false);

            holder.img = (ImageView) row.findViewById(R.id.image1);
            holder.txt1 = (TextView) row.findViewById(R.id.tvMaSV);
            holder.txt2 = (TextView) row.findViewById(R.id.tvTenSV);
            holder.txt3 = (TextView) row.findViewById(R.id.tvKhoa);

            row.setTag(holder);
        }
        SinhVien sv = data.get(position);

        holder.img.setImageResource(R.drawable.ic_people_black_24dp);
        holder.txt1.setText("Mã SV: " + sv.getMaSV());
        holder.txt2.setText("Tên SV: " + sv.getTenSV());
        holder.txt3.setText("Khoa: " + sv.getKhoa());

        return row;
    }
}

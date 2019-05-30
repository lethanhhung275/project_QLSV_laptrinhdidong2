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

public class KhoaAdapter extends ArrayAdapter<Khoa> {
    Context context;
    int layoutResourceId;
    ArrayList<Khoa> data = null;

    public KhoaAdapter(Context context, int layoutResourceId, ArrayList<Khoa> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    static class KhoaHolder {
        ImageView img;
        TextView txt1, txt2;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        KhoaHolder holder = null;

        if(row != null)
        {
            holder = (KhoaHolder) row.getTag();
        }
        else
        {
            holder = new KhoaHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.listview_item_layout_khoa, parent, false);

            holder.img = (ImageView) row.findViewById(R.id.image2);
            holder.txt1 = (TextView) row.findViewById(R.id.tvMaKhoa);
            holder.txt2 = (TextView) row.findViewById(R.id.tvTenKhoa);

            row.setTag(holder);
        }
        Khoa khoa = data.get(position);

        holder.img.setImageResource(R.drawable.ic_filter_none_black_24dp);
        holder.txt1.setText("Mã khoa: " + khoa.getMaKhoa());
        holder.txt2.setText("Tên khoa: " + khoa.getTenKhoa());

        return row;
    }
}

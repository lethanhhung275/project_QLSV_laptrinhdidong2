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

public class NoteAdapter extends ArrayAdapter<TheoDoi> {
    Context context;
    int layoutResourceId;
    ArrayList<TheoDoi> data = null;


    public NoteAdapter(Context context, int layoutResourceId, ArrayList<TheoDoi> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    static class TheoDoiHolder {
        ImageView img;
        TextView txt1, txt2;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TheoDoiHolder holder = null;

        if(row != null)
        {
            holder = (TheoDoiHolder) row.getTag();
        }
        else
        {
            holder = new TheoDoiHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.listview_item_layout_note, parent, false);

            holder.img = (ImageView) row.findViewById(R.id.image3);
            holder.txt1 = (TextView) row.findViewById(R.id.tvNgayNote);
            holder.txt2 = (TextView) row.findViewById(R.id.tvNDNote);

            row.setTag(holder);
        }

        TheoDoi theoDoi = data.get(position);

        holder.img.setImageResource(R.drawable.ic_filter_none_black_24dp);
        holder.txt1.setText("Ngày: " + theoDoi.getNgay());
        holder.txt2.setText(theoDoi.getNote());

        return row;
    }
}

package hungle.example.project_nhom8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    TextInputLayout inputLayoutNote;
    Button btnThem, btnHuy;
    Calendar now = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        setControl();
        setEvent();
    }

    public void setControl(){
        inputLayoutNote = (TextInputLayout) findViewById(R.id.textNote);
        btnThem = (Button) findViewById(R.id.btnThemNote);
        btnHuy = (Button) findViewById(R.id.btnHuyNote);
    }

    public void setEvent(){
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputLayoutNote.getEditText().getText().length() != 0)   {
                    addNote();
                    Toast.makeText(AddNoteActivity.this, "Thêm note thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddNoteActivity.this, NoteActivity.class);
                    intent.putExtra("masv", getNote().getMaSV());
                    startActivity(intent);

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this) ;
                    builder.setTitle("Thông báo!!!")  ;
                    builder.setMessage("Bạn vui lòng nhập nội dung note.");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }) ;
                    builder.show();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputLayoutNote.getEditText().getText().length() != 0)   {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this) ;
                    builder.setTitle("Thông báo!!!")  ;
                    builder.setMessage("Tác vụ chưa hoàn thành. Bạn có muốn thoát?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(AddNoteActivity.this, NoteActivity.class);
                            intent.putExtra("masv", getNote().getMaSV());
                            startActivity(intent);
                        }
                    }) ;
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }else {
                    Intent intent = new Intent(AddNoteActivity.this, NoteActivity.class);
                    intent.putExtra("masv", getNote().getMaSV());
                    startActivity(intent);
                }
            }
        });
    }

    private TheoDoi getNote() {
        TheoDoi theoDoi = new TheoDoi();
        theoDoi.setNote(inputLayoutNote.getEditText().getText().toString());
        Intent intent = getIntent();
        theoDoi.setMaSV(intent.getStringExtra("masv"));
        theoDoi.setNgay(now.get(Calendar.DATE) + "/" + (now.get(Calendar.MONTH + 1) + "/" + now.get(Calendar.YEAR)));
        return theoDoi;
    }
    public void addNote()
    {
        Database db = new Database(this);
        TheoDoi theoDoi = getNote();
        db.saveTheoDoi(theoDoi);
    }
}

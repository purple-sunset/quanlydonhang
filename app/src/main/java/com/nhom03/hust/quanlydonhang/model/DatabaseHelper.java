package com.nhom03.hust.quanlydonhang.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 30/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Cơ sở dữ liệu
    private static final String TAG = "SQLite";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Hang_hoa";

    //Tên các bảng
    private static final String TABLE_DH = "Don_hang";
    private static final String TABLE_KH = "Khach_hang";
    private static final String TABLE_HH = "Hang_hoa";
    private static final String TABLE_CTDH = "Chi_tiet_don_hang";

    //Các trường của bảng Don_hang
    private static final String COLUMN_ID_DH = "id_don_hang";
    private static final String COLUMN_NG = "ngay_gio";
    private static final String COLUMN_CP = "cuoc_phi";
    private static final String COLUMN_DC = "dia_chi";

    //Các trường của bảng Khach_hang
    private static final String COLUMN_ID_KH = "id_khach_hang";
    private static final String COLUMN_TKH = "ten_khach_hang";
    private static final String COLUMN_TCT = "ten_cong_ty";
    private static final String COLUMN_TD = "tieu_de";
    private static final String COLUMN_TP = "thanh_pho";
    private static final String COLUMN_V = "vung";
    private static final String COLUMN_QG = "quoc_gia";
    private static final String COLUMN_SDT = "so_dien_thoai";
    private static final String COLUMN_F = "fax";
    private static final String COLUMN_MBC = "ma_buu_chinh";

    //Các trường của bảng Hang_hoa
    private static final String COLUMN_ID_HH = "id_hang_hoa";
    private static final String COLUMN_TH = "ten_hang";
    private static final String COLUMN_HB = "huy_ban";
    private static final String COLUMN_DG = "don_gia";
    private static final String COLUMN_SLT = "so_luong_ton";
    private static final String COLUMN_SLD = "so_luong_da_dat";
    private static final String COLUMN_CT = "chi_tiet";

    //Các trường của bảng Chi_tiet_don_hang
    private static final String COLUMN_SL = "so_luong";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Tạo các bảng.
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG, "DatabaseHelper.onCreate ... ");

        // Script tạo bảng.
        String taoBangDH = "CREATE TABLE " + TABLE_DH + "("
                + COLUMN_ID_DH + " INT PRIMARY KEY," + COLUMN_NG + " TEXT NOT NULL,"
                + COLUMN_CP + " TEXT NOT NULL," + COLUMN_DC + " TEXT,"
                +" FOREIGN KEY ("+ COLUMN_ID_KH +") REFERENCES "+ TABLE_KH +"("+ COLUMN_ID_KH + ") )";

        String taoBangKH = "CREATE TABLE " + TABLE_KH + "("
                + COLUMN_ID_KH + " TEXT PRIMARY KEY," + COLUMN_TKH + " TEXT NOT NULL,"
                + COLUMN_TCT + " TEXT," + COLUMN_TD + " TEXT," + COLUMN_DC + " TEXT NOT NULL,"
                + COLUMN_TP + " TEXT NOT NULL," + COLUMN_V + " TEXT NOT NULL," + COLUMN_QG + " TEXT NOT NULL,"
                + COLUMN_SDT + " TEXT NOT NULL," + COLUMN_F + " TEXT," + COLUMN_MBC + " TEXT" + ")";

        String taoBangHH = "CREATE TABLE " + TABLE_HH + "("
                + COLUMN_ID_HH + " INTEGER PRIMARY KEY," + COLUMN_TH + " TEXT NOT NULL,"
                + COLUMN_HB + " BOOLEAN NOT NULL," + COLUMN_DG + " BIGINT NOT NULL," + COLUMN_SLT + " INT NOT NULL,"
                + COLUMN_SLD + " INT NOT NULL," + COLUMN_CT + " TEXT" + ")";

        String taoBangCTDH = "CREATE TABLE " + TABLE_CTDH + "("
                + "FOREIGN KEY ("+ COLUMN_ID_DH +") REFERENCES "+ TABLE_DH +"("+ COLUMN_ID_DH + "),"
                + "FOREIGN KEY ("+ COLUMN_ID_HH +") REFERENCES "+ TABLE_HH +"("+ COLUMN_ID_HH + "),"
                + COLUMN_SL + " INT NOT NULL" + ")";

        // Chạy lệnh tạo bảng.
        db.execSQL(taoBangKH);
        db.execSQL(taoBangDH);
        db.execSQL(taoBangHH);
        db.execSQL(taoBangCTDH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "DatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CTDH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KH);

        // Và tạo lại.
        onCreate(db);
    }

    public void addDonHang(DonHang dh) {
        Log.i(TAG, "DatabaseHelper.addDonHang ... " + dh.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_DH, dh.getId());
        values.put(COLUMN_NG, dh.getNgayGio().toString());
        values.put(COLUMN_CP, dh.getCuocPhi());
        values.put(COLUMN_DC, dh.getDiaChi());
        values.put(COLUMN_ID_KH, dh.getKhachHang().getId());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_DH, null, values);

        // Đóng kết nối database.
        db.close();
    }


    public DonHang getDonHang(int id) {
        Log.i(TAG, "DatabaseHelper.getDonHang ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_DH, new String[] { COLUMN_ID_DH,
                        COLUMN_NG, COLUMN_CP, COLUMN_DC, COLUMN_ID_KH }, COLUMN_ID_DH + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (c != null)
            c.moveToFirst();

        DonHang dh = new DonHang();
        dh.setId(c.getInt(c.getColumnIndex(COLUMN_ID_DH)));
        try {
            DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy");
            Date d = df.parse(c.getString(c.getColumnIndex(COLUMN_NG)));
            dh.setNgayGio(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dh.setCuocPhi(c.getLong(c.getColumnIndex(COLUMN_CP)));
        dh.setDiaChi(c.getString(c.getColumnIndex(COLUMN_DC)));
        dh.setKhachHang(getKhachHang(c.getString(c.getColumnIndex(COLUMN_ID_KH))));
        dh.setDsHang(getAllChiTietDonHang(dh.getId()));

        // return Don Hang
        return dh;
    }


    public List<ContactsContract.CommonDataKinds.Note> getAllNotes() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<ContactsContract.CommonDataKinds.Note> noteList = new ArrayList<Note>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setNoteId(Integer.parseInt(cursor.getString(0)));
                note.setNoteTitle(cursor.getString(1));
                note.setNoteContent(cursor.getString(2));

                // Thêm vào danh sách.
                noteList.add(note);
            } while (cursor.moveToNext());
        }

        // return note list
        return noteList;
    }

    public int updateNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());

        // updating row
        return db.update(TABLE_NOTE, values, COLUMN_NOTE_ID + " = ?",
                new String[]{String.valueOf(note.getNoteId())});
    }

    public void deleteNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getNoteTitle() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTE, COLUMN_NOTE_ID + " = ?",
                new String[] { String.valueOf(note.getNoteId()) });
        db.close();
    }
}

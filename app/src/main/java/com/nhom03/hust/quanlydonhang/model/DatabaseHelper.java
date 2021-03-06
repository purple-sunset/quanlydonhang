package com.nhom03.hust.quanlydonhang.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nhom03.hust.quanlydonhang.MyApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Admin on 30/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instan = null;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.US);

    //Cơ sở dữ liệu
    private static final String TAG = "SQLite";
    private static final int DB_VERSION = 8;
    private static final String DB_NAME = "Quan_ly_don_hang";

    //Tên các bảng
    private static final String TABLE_DH = "Don_hang";
    private static final String TABLE_KH = "Khach_hang";
    private static final String TABLE_HH = "Hang_hoa";
    private static final String TABLE_TL = "The_loai";
    private static final String TABLE_HHCDH = "Hang_hoa_cua_don_hang";

    //Các trường của bảng Don_hang
    private static final String COLUMN_ID_DH = "id_don_hang";
    private static final String COLUMN_ID_NV = "id_nhan_vien";
    private static final String COLUMN_NG = "ngay_gio";
    private static final String COLUMN_CP = "cuoc_phi";
    private static final String COLUMN_TCH = "ten_chuyen_hang";
    private static final String COLUMN_SV = "ship_via";

    //Các trường của bảng Khach_hang
    private static final String COLUMN_ID_KH = "id_khach_hang";
    private static final String COLUMN_TKH = "ten_khach_hang";
    private static final String COLUMN_TCT = "ten_cong_ty";
    private static final String COLUMN_TD = "tieu_de";
    private static final String COLUMN_DC = "dia_chi";
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
    private static final String COLUMN_ROL = "re_order_level";
    private static final String COLUMN_DG = "don_gia";
    private static final String COLUMN_SLT = "so_luong_ton";
    private static final String COLUMN_SLD = "so_luong_da_dat";
    private static final String COLUMN_CT = "chi_tiet";
    private static final String COLUMN_SP_ID = "id_supplier";

    //Các trường của bảng The_loai
    private static final String COLUMN_ID_TL = "id_the_loai";
    private static final String COLUMN_TTL = "ten_the_loai";
    private static final String COLUMN_MT = "mo_ta";

    //Các trường của bảng Hang_hoa_cua_don_hang
    private static final String COLUMN_SL = "so_luong";
    private static final String COLUMN_GG = "giam_gia";

    public static DatabaseHelper getInstance(){
        if(instan == null){
            instan = new DatabaseHelper();
        }
        return instan;
    }

    public DatabaseHelper() {
        super(MyApplication.getAppContext(), DB_NAME, null, DB_VERSION);
    }



    // Tạo các bảng.
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG, "DatabaseHelper.onCreate ... ");

        // Script tạo bảng.
        String taoBangDH = "CREATE TABLE " + TABLE_DH + "("
                + COLUMN_ID_DH + " INTEGER PRIMARY KEY," + COLUMN_ID_NV + " INT NOT NULL," + COLUMN_NG + " TEXT NOT NULL,"
                + COLUMN_CP + " FLOAT NOT NULL," + COLUMN_TCH + " TEXT," +  COLUMN_DC + " TEXT,"
                + COLUMN_TP + " TEXT," + COLUMN_V + " TEXT," + COLUMN_QG + " TEXT," + COLUMN_MBC + " TEXT,"
                + COLUMN_SV + " INT NOT NULL," + COLUMN_ID_KH + " TEXT NOT NULL,"
                + " FOREIGN KEY ("+ COLUMN_ID_KH +") REFERENCES "+ TABLE_KH +"("+ COLUMN_ID_KH + ") )";

        String taoBangKH = "CREATE TABLE " + TABLE_KH + "("
                + COLUMN_ID_KH + " TEXT PRIMARY KEY," + COLUMN_TKH + " TEXT NOT NULL,"
                + COLUMN_TCT + " TEXT," + COLUMN_TD + " TEXT," + COLUMN_DC + " TEXT NOT NULL,"
                + COLUMN_TP + " TEXT," + COLUMN_V + " TEXT," + COLUMN_QG + " TEXT,"
                + COLUMN_SDT + " TEXT NOT NULL," + COLUMN_F + " TEXT," + COLUMN_MBC + " TEXT" + ")";

        String taoBangHH = "CREATE TABLE " + TABLE_HH + "("
                + COLUMN_ID_HH + " INT PRIMARY KEY," + COLUMN_TH + " TEXT NOT NULL,"
                + COLUMN_HB + " BOOLEAN NOT NULL," + COLUMN_ROL + " INT NOT NULL," + COLUMN_DG + " FLOAT NOT NULL," + COLUMN_SLT + " INT NOT NULL,"
                + COLUMN_SLD + " INT NOT NULL," + COLUMN_CT + " TEXT," + COLUMN_SP_ID + " INT NOT NULL,"
                + COLUMN_ID_TL + " INT NOT NULL," + "FOREIGN KEY ("+ COLUMN_ID_TL +") REFERENCES "+ TABLE_TL +"("+ COLUMN_ID_TL + ") )";

        String taoBangHHCDH = "CREATE TABLE " + TABLE_HHCDH + "("
                + COLUMN_ID_DH + " INTEGER NOT NULL," + COLUMN_ID_HH + " INT NOT NULL," + COLUMN_SL + " INT NOT NULL,"
                + COLUMN_GG + " FLOAT," + COLUMN_DG + " FLOAT NOT NULL,"
                + "FOREIGN KEY ("+ COLUMN_ID_DH +") REFERENCES "+ TABLE_DH +"("+ COLUMN_ID_DH + "),"
                + "FOREIGN KEY ("+ COLUMN_ID_HH +") REFERENCES "+ TABLE_HH +"("+ COLUMN_ID_HH + ") )";

        String taoBangTL = "CREATE TABLE " + TABLE_TL + "("
                + COLUMN_ID_TL + " INT PRIMARY KEY," + COLUMN_TTL + " TEXT NOT NULL,"
                + COLUMN_MT + " TEXT" + ")";

        // Chạy lệnh tạo bảng.
        db.execSQL(taoBangKH);
        db.execSQL(taoBangTL);
        db.execSQL(taoBangHH);
        db.execSQL(taoBangDH);
        db.execSQL(taoBangHHCDH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "DatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HHCDH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KH);


        // Và tạo lại.
        onCreate(db);
    }

    public void update(){
        Log.i(TAG, "DatabaseHelper.onUpgrade ... ");
        SQLiteDatabase db = this.getWritableDatabase();

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HHCDH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KH);


        // Và tạo lại.
        onCreate(db);
    }

    //Đơn Hàng
    public void themDonHang(DonHang dh) {
        Log.i(TAG, "DatabaseHelper.themDonHang ... " + dh.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_DH, dh.getId());
        values.put(COLUMN_ID_NV, dh.getIdNhanVien());
        values.put(COLUMN_NG, df.format(dh.getNgayGio()));
        values.put(COLUMN_CP, dh.getCuocPhi());
        values.put(COLUMN_TCH, dh.getTenChuyenHang());
        values.put(COLUMN_DC, dh.getDiaChiGiaoHang());
        values.put(COLUMN_TP, dh.getThanhPhoGiaoHang());
        values.put(COLUMN_V, dh.getVungGiaoHang());
        values.put(COLUMN_QG, dh.getQuocGiaGiaoHang());
        values.put(COLUMN_MBC, dh.getMbcGiaoHang());
        values.put(COLUMN_ID_KH, dh.getIdKhachHang());
        values.put(COLUMN_SV, dh.getShipVia());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_DH, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public long themDonHangNoID(DonHang dh) {
        Log.i(TAG, "DatabaseHelper.themDonHang ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_NV, dh.getIdNhanVien());
        values.put(COLUMN_NG, df.format(dh.getNgayGio()));
        values.put(COLUMN_CP, dh.getCuocPhi());
        values.put(COLUMN_TCH, dh.getTenChuyenHang());
        values.put(COLUMN_DC, dh.getDiaChiGiaoHang());
        values.put(COLUMN_TP, dh.getThanhPhoGiaoHang());
        values.put(COLUMN_V, dh.getVungGiaoHang());
        values.put(COLUMN_QG, dh.getQuocGiaGiaoHang());
        values.put(COLUMN_MBC, dh.getMbcGiaoHang());
        values.put(COLUMN_ID_KH, dh.getIdKhachHang());
        values.put(COLUMN_SV, dh.getShipVia());

        // Trèn một dòng dữ liệu vào bảng.
        long id = db.insert(TABLE_DH, null, values);

        // Đóng kết nối database.
        dh.setId(id);
        db.close();
        return  id;
    }

    public DonHang layDonHang(long id) {
        Log.i(TAG, "DatabaseHelper.layDonHang ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_DH, new String[] { COLUMN_ID_DH, COLUMN_ID_NV, COLUMN_NG, COLUMN_CP,
                        COLUMN_TCH, COLUMN_DC, COLUMN_TP, COLUMN_V, COLUMN_QG, COLUMN_MBC, COLUMN_SV, COLUMN_ID_KH }, COLUMN_ID_DH + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (c != null)
            c.moveToFirst();

        DonHang dh = new DonHang();
        dh.setId(c.getInt(c.getColumnIndex(COLUMN_ID_DH)));
        try {
            Date d = df.parse(c.getString(c.getColumnIndex(COLUMN_NG)));
            dh.setNgayGio(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dh.setIdNhanVien(c.getInt(c.getColumnIndex(COLUMN_ID_NV)));
        dh.setCuocPhi(c.getFloat(c.getColumnIndex(COLUMN_CP)));
        dh.setTenChuyenHang(c.getString(c.getColumnIndex(COLUMN_TCH)));
        dh.setDiaChiGiaoHang(c.getString(c.getColumnIndex(COLUMN_DC)));
        dh.setThanhPhoGiaoHang(c.getString(c.getColumnIndex(COLUMN_TP)));
        dh.setVungGiaoHang(c.getString(c.getColumnIndex(COLUMN_V)));
        dh.setQuocGiaGiaoHang(c.getString(c.getColumnIndex(COLUMN_QG)));
        dh.setMbcGiaoHang(c.getString(c.getColumnIndex(COLUMN_MBC)));
        dh.setShipVia(c.getInt(c.getColumnIndex(COLUMN_SV)));
        dh.setIdKhachHang(c.getString(c.getColumnIndex(COLUMN_ID_KH)));
        dh.setShipVia(c.getInt(c.getColumnIndex(COLUMN_SV)));
        dh.setKhachHang(layKhachHang(dh.getIdKhachHang()));
        dh.setDsHang(layDSHangHoaCuaDonHang(dh.getId()));

        // return Don Hang
        return dh;
    }

    public ArrayList<DonHang> layDSDonHang() {
        Log.i(TAG, "MyDatabaseHelper.layDSDonHang ... " );

        ArrayList<DonHang> dsDonHang = new ArrayList<DonHang>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (c.moveToFirst()) {
            do {
                DonHang dh = new DonHang();
                dh.setId(c.getLong(c.getColumnIndex(COLUMN_ID_DH)));
                try {
                    Date d = df.parse(c.getString(c.getColumnIndex(COLUMN_NG)));
                    dh.setNgayGio(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dh.setIdNhanVien(c.getInt(c.getColumnIndex(COLUMN_ID_NV)));
                dh.setCuocPhi(c.getFloat(c.getColumnIndex(COLUMN_CP)));
                dh.setTenChuyenHang(c.getString(c.getColumnIndex(COLUMN_TCH)));
                dh.setDiaChiGiaoHang(c.getString(c.getColumnIndex(COLUMN_DC)));
                dh.setThanhPhoGiaoHang(c.getString(c.getColumnIndex(COLUMN_TP)));
                dh.setVungGiaoHang(c.getString(c.getColumnIndex(COLUMN_V)));
                dh.setQuocGiaGiaoHang(c.getString(c.getColumnIndex(COLUMN_QG)));
                dh.setMbcGiaoHang(c.getString(c.getColumnIndex(COLUMN_MBC)));
                dh.setShipVia(c.getInt(c.getColumnIndex(COLUMN_SV)));
                dh.setIdKhachHang(c.getString(c.getColumnIndex(COLUMN_ID_KH)));
                dh.setShipVia(c.getInt(c.getColumnIndex(COLUMN_SV)));
                dh.setKhachHang(layKhachHang(dh.getIdKhachHang()));
                dh.setDsHang(layDSHangHoaCuaDonHang(dh.getId()));

                // Thêm vào danh sách.
                dsDonHang.add(dh);
            } while (c.moveToNext());
        }

        // return ds Don Hang
        return dsDonHang;
    }

    public int suaDonHang(DonHang dh) {
        Log.i(TAG, "DatabaseHelper.suaDonHang ... "  + dh.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_NV, dh.getIdNhanVien());
        values.put(COLUMN_NG, df.format(dh.getNgayGio()));
        values.put(COLUMN_CP, dh.getCuocPhi());
        values.put(COLUMN_TCH, dh.getTenChuyenHang());
        values.put(COLUMN_DC, dh.getDiaChiGiaoHang());
        values.put(COLUMN_TP, dh.getThanhPhoGiaoHang());
        values.put(COLUMN_V, dh.getVungGiaoHang());
        values.put(COLUMN_QG, dh.getQuocGiaGiaoHang());
        values.put(COLUMN_MBC, dh.getMbcGiaoHang());
        values.put(COLUMN_ID_KH, dh.getIdKhachHang());
        values.put(COLUMN_SV, dh.getShipVia());

        // updating row
        return db.update(TABLE_DH, values, COLUMN_ID_DH + " = ?",
                new String[]{String.valueOf(dh.getId())});
    }

    public void xoaDonHang(DonHang dh) {
        Log.i(TAG, "DatabaseHelper.xoaDonHang ... " + dh.getId() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DH, COLUMN_ID_DH + " = ?",
                new String[] { String.valueOf(dh.getId()) });
        db.close();
    }

    //Khách Hàng
    public void themKhachHang(KhachHang kh) {
        Log.i(TAG, "DatabaseHelper.themKhachHang ... " + kh.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_KH, kh.getId());
        values.put(COLUMN_TKH, kh.getTenKH());
        values.put(COLUMN_TCT, kh.getTenCT());
        values.put(COLUMN_TD, kh.getTieuDe());
        values.put(COLUMN_DC, kh.getDiaChi());
        values.put(COLUMN_TP, kh.getThanhPho());
        values.put(COLUMN_V, kh.getVung());
        values.put(COLUMN_QG, kh.getQuocGia());
        values.put(COLUMN_SDT, kh.getSdt());
        values.put(COLUMN_F, kh.getFax());
        values.put(COLUMN_MBC, kh.getMaBuuChinh());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_KH, null, values);

        // Đóng kết nối database.
        db.close();
    }


    public KhachHang layKhachHang(String id) {
        Log.i(TAG, "DatabaseHelper.layKhachHang ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_KH, new String[] { COLUMN_ID_KH,
                        COLUMN_TKH, COLUMN_TCT, COLUMN_TD, COLUMN_DC, COLUMN_TP, COLUMN_V,
                COLUMN_QG, COLUMN_SDT, COLUMN_F, COLUMN_MBC }, COLUMN_ID_KH + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (c != null)
            c.moveToFirst();

        KhachHang kh = new KhachHang();
        kh.setId(c.getString(c.getColumnIndex(COLUMN_ID_KH)));
        kh.setTenKH(c.getString(c.getColumnIndex(COLUMN_TKH)));
        kh.setTenCT(c.getString(c.getColumnIndex(COLUMN_TCT)));
        kh.setTieuDe(c.getString(c.getColumnIndex(COLUMN_TD)));
        kh.setDiaChi(c.getString(c.getColumnIndex(COLUMN_DC)));
        kh.setThanhPho(c.getString(c.getColumnIndex(COLUMN_TP)));
        kh.setVung(c.getString(c.getColumnIndex(COLUMN_V)));
        kh.setQuocGia(c.getString(c.getColumnIndex(COLUMN_QG)));
        kh.setSdt(c.getString(c.getColumnIndex(COLUMN_SDT)));
        kh.setFax(c.getString(c.getColumnIndex(COLUMN_F)));
        kh.setMaBuuChinh(c.getString(c.getColumnIndex(COLUMN_MBC)));

        // return Khach Hang
        return kh;
    }


    public ArrayList<KhachHang> layDSKhachHang() {
        Log.i(TAG, "MyDatabaseHelper.layDSKhachHang ... " );

        ArrayList<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_KH;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (c.moveToFirst()) {
            do {
                KhachHang kh = new KhachHang();
                kh.setId(c.getString(c.getColumnIndex(COLUMN_ID_KH)));
                kh.setTenKH(c.getString(c.getColumnIndex(COLUMN_TKH)));
                kh.setTenCT(c.getString(c.getColumnIndex(COLUMN_TCT)));
                kh.setTieuDe(c.getString(c.getColumnIndex(COLUMN_TD)));
                kh.setDiaChi(c.getString(c.getColumnIndex(COLUMN_DC)));
                kh.setThanhPho(c.getString(c.getColumnIndex(COLUMN_TP)));
                kh.setVung(c.getString(c.getColumnIndex(COLUMN_V)));
                kh.setQuocGia(c.getString(c.getColumnIndex(COLUMN_QG)));
                kh.setSdt(c.getString(c.getColumnIndex(COLUMN_SDT)));
                kh.setFax(c.getString(c.getColumnIndex(COLUMN_F)));
                kh.setMaBuuChinh(c.getString(c.getColumnIndex(COLUMN_MBC)));

                // Thêm vào danh sách.
                dsKhachHang.add(kh);
            } while (c.moveToNext());
        }

        // return ds Khach Hang
        return dsKhachHang;
    }

    public int suaKhachHang(KhachHang kh) {
        Log.i(TAG, "DatabaseHelper.suaKhachHang ... "  + kh.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TKH, kh.getTenKH());
        values.put(COLUMN_TCT, kh.getTenCT());
        values.put(COLUMN_TD, kh.getTieuDe());
        values.put(COLUMN_DC, kh.getDiaChi());
        values.put(COLUMN_TP, kh.getThanhPho());
        values.put(COLUMN_V, kh.getVung());
        values.put(COLUMN_QG, kh.getQuocGia());
        values.put(COLUMN_SDT, kh.getSdt());
        values.put(COLUMN_F, kh.getFax());
        values.put(COLUMN_MBC, kh.getMaBuuChinh());

        // updating row
        return db.update(TABLE_KH, values, COLUMN_ID_KH + " = ?",
                new String[]{String.valueOf(kh.getId())});
    }

    public void xoaKhachHang(KhachHang kh) {
        Log.i(TAG, "DatabaseHelper.xoaKhachHang ... " + kh.getId() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_KH, COLUMN_ID_KH + " = ?",
                new String[] { String.valueOf(kh.getId()) });
        db.close();
    }

    //Hàng Hóa
    public void themHangHoa(HangHoa hh) {
        Log.i(TAG, "DatabaseHelper.themHanghoa ... " + hh.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_HH, hh.getId());
        values.put(COLUMN_TH, hh.getTen());
        values.put(COLUMN_HB, hh.isHuyBan());
        values.put(COLUMN_ROL, hh.getReOrderLevel());
        values.put(COLUMN_DG, hh.getDonGia());
        values.put(COLUMN_SLT, hh.getSlTon());
        values.put(COLUMN_SLD, hh.getSlDat());
        values.put(COLUMN_CT, hh.getChiTiet());
        values.put(COLUMN_ID_TL, hh.getIdTheLoai());
        values.put(COLUMN_SP_ID, hh.getSupplierId());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_HH, null, values);

        // Đóng kết nối database.
        db.close();
    }


    public HangHoa layHangHoa(int id) {
        Log.i(TAG, "DatabaseHelper.layHangHoa ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_HH, new String[] { COLUMN_ID_HH,
                        COLUMN_TH, COLUMN_HB, COLUMN_DG, COLUMN_SLT, COLUMN_SLD, COLUMN_CT, COLUMN_ID_TL }
                , COLUMN_ID_HH + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (c != null)
            c.moveToFirst();

        HangHoa hh = new HangHoa();
        hh.setId(c.getInt(c.getColumnIndex(COLUMN_ID_HH)));
        hh.setTen(c.getString(c.getColumnIndex(COLUMN_TH)));
        hh.setHuyBan(c.getInt(c.getColumnIndex(COLUMN_HB)) != 0);
        hh.setReOrderLevel(c.getInt(c.getColumnIndex(COLUMN_ROL)));
        hh.setDonGia(c.getFloat(c.getColumnIndex(COLUMN_DG)));
        hh.setSlTon(c.getInt(c.getColumnIndex(COLUMN_SLT)));
        hh.setSlDat(c.getInt(c.getColumnIndex(COLUMN_SLD)));
        hh.setChiTiet(c.getString(c.getColumnIndex(COLUMN_CT)));
        hh.setIdTheLoai(c.getInt(c.getColumnIndex(COLUMN_ID_TL)));
        hh.setSupplierId(c.getInt(c.getColumnIndex(COLUMN_SP_ID)));

        // return Hang Hoa
        return hh;
    }


    public ArrayList<HangHoa> layDSHangHoa() {
        Log.i(TAG, "MyDatabaseHelper.layDSHangHoa ... " );

        ArrayList<HangHoa> dsHangHoa = new ArrayList<HangHoa>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HH;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (c.moveToFirst()) {
            do {
                HangHoa hh = new HangHoa();
                hh.setId(c.getInt(c.getColumnIndex(COLUMN_ID_HH)));
                hh.setTen(c.getString(c.getColumnIndex(COLUMN_TH)));
                hh.setHuyBan(c.getInt(c.getColumnIndex(COLUMN_HB)) != 0);
                hh.setReOrderLevel(c.getInt(c.getColumnIndex(COLUMN_ROL)));
                hh.setDonGia(c.getFloat(c.getColumnIndex(COLUMN_DG)));
                hh.setSlTon(c.getInt(c.getColumnIndex(COLUMN_SLT)));
                hh.setSlDat(c.getInt(c.getColumnIndex(COLUMN_SLD)));
                hh.setChiTiet(c.getString(c.getColumnIndex(COLUMN_CT)));
                hh.setIdTheLoai(c.getInt(c.getColumnIndex(COLUMN_ID_TL)));
                hh.setSupplierId(c.getInt(c.getColumnIndex(COLUMN_SP_ID)));

                // Thêm vào danh sách.
                dsHangHoa.add(hh);
            } while (c.moveToNext());
        }

        // return ds Hang Hoa
        return dsHangHoa;
    }

    public ArrayList<HangHoa> layDSHangHoa(int idTheLoai) {
        Log.i(TAG, "MyDatabaseHelper.layDSHangHoa ... " + idTheLoai );

        ArrayList<HangHoa> dsHangHoa = new ArrayList<HangHoa>();
        // Select All Query
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_HH, new String[] { COLUMN_ID_HH,
                        COLUMN_TH, COLUMN_HB, COLUMN_ROL, COLUMN_DG, COLUMN_SLT, COLUMN_SLD, COLUMN_CT, COLUMN_ID_TL, COLUMN_SP_ID }
                , COLUMN_ID_TL + "=?",
                new String[] { String.valueOf(idTheLoai) }, null, null, null, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (c.moveToFirst()) {
            do {
                HangHoa hh = new HangHoa();
                hh.setId(c.getInt(c.getColumnIndex(COLUMN_ID_HH)));
                hh.setTen(c.getString(c.getColumnIndex(COLUMN_TH)));
                hh.setHuyBan(c.getInt(c.getColumnIndex(COLUMN_HB)) != 0);
                hh.setReOrderLevel(c.getInt(c.getColumnIndex(COLUMN_ROL)));
                hh.setDonGia(c.getFloat(c.getColumnIndex(COLUMN_DG)));
                hh.setSlTon(c.getInt(c.getColumnIndex(COLUMN_SLT)));
                hh.setSlDat(c.getInt(c.getColumnIndex(COLUMN_SLD)));
                hh.setChiTiet(c.getString(c.getColumnIndex(COLUMN_CT)));
                hh.setIdTheLoai(c.getInt(c.getColumnIndex(COLUMN_ID_TL)));
                hh.setSupplierId(c.getInt(c.getColumnIndex(COLUMN_SP_ID)));

                // Thêm vào danh sách.
                dsHangHoa.add(hh);
            } while (c.moveToNext());
        }

        // return ds Hang Hoa
        return dsHangHoa;
    }

    public int suaHangHoa(HangHoa hh) {
        Log.i(TAG, "DatabaseHelper.suaHangHoa ... "  + hh.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TH, hh.getTen());
        values.put(COLUMN_HB, hh.isHuyBan());
        values.put(COLUMN_ROL, hh.getReOrderLevel());
        values.put(COLUMN_DG, hh.getDonGia());
        values.put(COLUMN_SLT, hh.getSlTon());
        values.put(COLUMN_SLD, hh.getSlDat());
        values.put(COLUMN_CT, hh.getChiTiet());
        values.put(COLUMN_ID_TL, hh.getIdTheLoai());
        values.put(COLUMN_SP_ID, hh.getSupplierId());

        // updating row
        return db.update(TABLE_HH, values, COLUMN_ID_HH + " = ?",
                new String[]{String.valueOf(hh.getId())});
    }

    public void xoaHangHoa(HangHoa hh) {
        Log.i(TAG, "DatabaseHelper.xoaHangHoa ... " + hh.getId() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HH, COLUMN_ID_HH + " = ?",
                new String[] { String.valueOf(hh.getId()) });
        db.close();
    }

    //Chi Tiết Đơn Hàng
    public void themHangHoaCuaDonHang(HangHoaCuaDonHang hhdh) {
        Log.i(TAG, "DatabaseHelper.themHangHoaCuaDonHang ... " + hhdh.getIdDonHang() + " " + hhdh.getIdHangHoa());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_DH, hhdh.getIdDonHang());
        values.put(COLUMN_ID_HH, hhdh.getIdHangHoa());
        values.put(COLUMN_SL, hhdh.getSoLuong());
        values.put(COLUMN_DG, hhdh.getDonGia());
        values.put(COLUMN_GG, hhdh.getGiamGia());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_HHCDH, null, values);

        // Đóng kết nối database.
        db.close();
    }


    public HangHoaCuaDonHang layHangHoaCuaDonHang(int idDh, int idHH) {
        Log.i(TAG, "DatabaseHelper.layHangHoaCuaDonHang ... " + idDh + " " + idHH);

        String selectQuery = "SELECT " + COLUMN_ID_DH +", " + TABLE_HHCDH + "." + COLUMN_ID_HH + ", " + COLUMN_TH + ", " + COLUMN_SL + ", " + TABLE_HHCDH + "." + COLUMN_DG + ", " + COLUMN_GG
                + " FROM " + TABLE_HHCDH + ", " + TABLE_HH + " WHERE " + TABLE_HHCDH + "." + COLUMN_ID_DH + "=" + idDh
                + " AND " + TABLE_HH + "." + COLUMN_ID_HH + "=" + idHH;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();

        HangHoaCuaDonHang hhdh = new HangHoaCuaDonHang();
        hhdh.setIdDonHang(c.getInt(c.getColumnIndex(COLUMN_ID_DH)));
        hhdh.setIdHangHoa(c.getInt(c.getColumnIndex(COLUMN_ID_HH)));
        hhdh.setTen(c.getString(c.getColumnIndex(COLUMN_TH)));
        hhdh.setSoLuong(c.getInt(c.getColumnIndex(COLUMN_SL)));
        hhdh.setDonGia(c.getFloat(c.getColumnIndex(COLUMN_DG)));
        hhdh.setGiamGia(c.getFloat(c.getColumnIndex(COLUMN_GG)));

        // return Chi Tiet Don Hang
        return hhdh;
    }


    public ArrayList<HangHoaCuaDonHang> layDSHangHoaCuaDonHang(long id) {
        Log.i(TAG, "MyDatabaseHelper.layDSHangHoaCuaDonHang ... " + id );

        ArrayList<HangHoaCuaDonHang> dsHangHoaCuaDonHang = new ArrayList<HangHoaCuaDonHang>();
        // Select All Query
        String selectQuery = "SELECT " + TABLE_HHCDH + "." + COLUMN_ID_HH + ", " + COLUMN_TH + ", " + COLUMN_SL + ", " + TABLE_HHCDH + "." + COLUMN_DG + ", " + COLUMN_GG
                + " FROM " + TABLE_HHCDH + ", " + TABLE_HH + " WHERE " + TABLE_HHCDH + "." + COLUMN_ID_DH + "=" + id
                + " AND " + TABLE_HH + "." + COLUMN_ID_HH + "=" + TABLE_HHCDH + "." + COLUMN_ID_HH;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (c.moveToFirst()) {
            do {
                HangHoaCuaDonHang hhdh = new HangHoaCuaDonHang();
                hhdh.setIdDonHang(id);
                hhdh.setIdHangHoa(c.getInt(c.getColumnIndex(COLUMN_ID_HH)));
                hhdh.setTen(c.getString(c.getColumnIndex(COLUMN_TH)));
                hhdh.setSoLuong(c.getInt(c.getColumnIndex(COLUMN_SL)));
                hhdh.setDonGia(c.getFloat(c.getColumnIndex(COLUMN_DG)));
                hhdh.setGiamGia(c.getFloat(c.getColumnIndex(COLUMN_GG)));

                // Thêm vào danh sách.
                dsHangHoaCuaDonHang.add(hhdh);
            } while (c.moveToNext());
        }

        // return ds Chi Tiet Don Hang
        return dsHangHoaCuaDonHang;
    }

    public int suaHangHoaCuaDonHang(HangHoaCuaDonHang hhdh) {
        Log.i(TAG, "DatabaseHelper.suaHangHoaCuaDonHang ... "  + hhdh.getIdDonHang() + " " + hhdh.getIdHangHoa());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SL, hhdh.getSoLuong());
        values.put(COLUMN_DG, hhdh.getDonGia())
;        values.put(COLUMN_GG, hhdh.getGiamGia());

        // updating row
        return db.update(TABLE_HHCDH, values, COLUMN_ID_DH + " = ?" + "AND " + COLUMN_ID_HH + " =?",
                new String[]{String.valueOf(hhdh.getIdDonHang()), String.valueOf(hhdh.getIdHangHoa())});
    }

    public void xoaHangHoaCuaDonHang(HangHoaCuaDonHang hhdh) {
        Log.i(TAG, "DatabaseHelper.xoaHangHoaCuaDonHang ... " + hhdh.getIdDonHang() + " " + hhdh.getIdHangHoa() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HHCDH, COLUMN_ID_DH + " = ?" + "AND " + COLUMN_ID_HH + " = ?",
                new String[]{String.valueOf(hhdh.getIdDonHang()), String.valueOf(hhdh.getIdHangHoa())});
        db.close();
    }

    //Thể Loại
    public void themTheLoai(TheLoai tl) {
        Log.i(TAG, "DatabaseHelper.themTheLoai ... " + tl.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_TL, tl.getId());
        values.put(COLUMN_TTL, tl.getTen());
        values.put(COLUMN_MT, tl.getMoTa());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_TL, null, values);

        // Đóng kết nối database.
        db.close();
    }


    public TheLoai layTheLoai(int id) {
        Log.i(TAG, "DatabaseHelper.layTheLoai ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_TL, new String[] { COLUMN_ID_TL,
                        COLUMN_TTL, COLUMN_MT}, COLUMN_ID_TL + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (c != null)
            c.moveToFirst();

        TheLoai tl = new TheLoai();
        tl.setId(c.getInt(c.getColumnIndex(COLUMN_ID_TL)));
        tl.setTen(c.getString(c.getColumnIndex(COLUMN_TTL)));
        tl.setMoTa(c.getString(c.getColumnIndex(COLUMN_MT)));
        tl.setDsHangHoa(layDSHangHoa(tl.getId()));


        // return The Loai
        return tl;
    }


    public ArrayList<TheLoai> layDSTheLoai() {
        Log.i(TAG, "MyDatabaseHelper.layDSTheLoai ... " );

        ArrayList<TheLoai> dsTheLoai = new ArrayList<TheLoai>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (c.moveToFirst()) {
            do {
                TheLoai tl = new TheLoai();
                tl.setId(c.getInt(c.getColumnIndex(COLUMN_ID_TL)));
                tl.setTen(c.getString(c.getColumnIndex(COLUMN_TTL)));
                tl.setMoTa(c.getString(c.getColumnIndex(COLUMN_MT)));
                tl.setDsHangHoa(layDSHangHoa(tl.getId()));

                // Thêm vào danh sách.
                dsTheLoai.add(tl);
            } while (c.moveToNext());
        }

        // return ds Khach Hang
        return dsTheLoai;
    }

    public int suaTheLoai(TheLoai tl) {
        Log.i(TAG, "DatabaseHelper.suaTheLoai ... "  + tl.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TTL, tl.getTen());
        values.put(COLUMN_MT, tl.getMoTa());

        // updating row
        return db.update(TABLE_TL, values, COLUMN_ID_TL + " = ?",
                new String[]{String.valueOf(tl.getId())});
    }

    public void xoaTheLoai(TheLoai tl) {
        Log.i(TAG, "DatabaseHelper.xoaTheLoai ... " + tl.getId() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TL, COLUMN_ID_TL + " = ?",
                new String[] { String.valueOf(tl.getId()) });
        db.close();
    }

}

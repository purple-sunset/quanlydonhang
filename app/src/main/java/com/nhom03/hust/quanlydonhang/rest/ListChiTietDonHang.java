package com.nhom03.hust.quanlydonhang.rest;

import android.app.Notification;

import com.google.gson.annotations.SerializedName;
import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;

import java.util.List;

/**
 * Created by Admin on 03/12/2016.
 */
class ListChiTietDonHang {

    @SerializedName("GetAllOrderDetailResult")
    List<ChiTietDonHang> chiTietDonHangList;

    public List<ChiTietDonHang> getChiTietDonHangList() {
        return chiTietDonHangList;
    }

    public void setChiTietDonHangList(List<ChiTietDonHang> chiTietDonHangList) {
        this.chiTietDonHangList = chiTietDonHangList;
    }
}

class MessageJson {

    @SerializedName("Message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


class KetQuaThem {

    @SerializedName("AddOrderDetailResult")
    MessageJson messageJson;

    public MessageJson getMessageJson() {
        return messageJson;
    }

    public void setMessageJson(MessageJson messageJson) {
        this.messageJson = messageJson;
    }
}

class KetQuaSua {

    @SerializedName("EditOrderDetailResult")
    MessageJson messageJson;

    public MessageJson getMessageJson() {
        return messageJson;
    }

    public void setMessageJson(MessageJson messageJson) {
        this.messageJson = messageJson;
    }
}

class KetQuaXoa {

    @SerializedName("DeleteOrderDetailResult")
    MessageJson messageJson;

    public MessageJson getMessageJson() {
        return messageJson;
    }

    public void setMessageJson(MessageJson messageJson) {
        this.messageJson = messageJson;
    }
}

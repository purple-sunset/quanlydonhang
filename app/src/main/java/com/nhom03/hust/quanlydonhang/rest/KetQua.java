package com.nhom03.hust.quanlydonhang.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 04/12/2016.
 */

public class KetQua {
}

class KetQuaThem {

    @SerializedName(value="AddCustomerResult", alternate={"AddOrderResult", "AddOrderDetailResult"})
    MessageJson messageJson;

    public MessageJson getMessageJson() {
        return messageJson;
    }

    public void setMessageJson(MessageJson messageJson) {
        this.messageJson = messageJson;
    }
}

class KetQuaSua {

    @SerializedName(value="EditCustomerResult", alternate={"EditOrderResult", "EditOrderDetailResult"})
    MessageJson messageJson;

    public MessageJson getMessageJson() {
        return messageJson;
    }

    public void setMessageJson(MessageJson messageJson) {
        this.messageJson = messageJson;
    }
}

class KetQuaXoa {

    @SerializedName(value="DeleteCustomerResult", alternate={"DeleteOrderResult", "DeleteOrderDetailResult"})
    MessageJson messageJson;

    public MessageJson getMessageJson() {
        return messageJson;
    }

    public void setMessageJson(MessageJson messageJson) {
        this.messageJson = messageJson;
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
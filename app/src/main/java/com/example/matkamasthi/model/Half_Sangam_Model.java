package com.example.matkamasthi.model;

import android.widget.EditText;

public class Half_Sangam_Model {
    String open_ank;
    String close_patti;
    String amount;

    public Half_Sangam_Model(String open_ank, String close_patti, String amount) {
        this.open_ank = open_ank;
        this.close_patti = close_patti;
        this.amount = amount;
    }

    public String getOpen_ank() {
        return open_ank;
    }

    public String getClose_patti() {
        return close_patti;
    }

    public String getAmount() {
        return amount;
    }

    public void setOpen_ank(String open_ank) {
        this.open_ank = open_ank;
    }

    public void setClose_patti(String close_patti) {
        this.close_patti = close_patti;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


}

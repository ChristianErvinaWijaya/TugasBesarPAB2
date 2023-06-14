package com.si61.internationalschool.Model;

import java.util.List;

public class ModelResponse {
    private String kode, pesan;
    private List<ModelSekolah> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelSekolah> getData() {
        return data;
    }
}

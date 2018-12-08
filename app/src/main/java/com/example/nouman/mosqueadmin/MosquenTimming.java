package com.example.nouman.mosqueadmin;

public class MosquenTimming {
    private Mosque mosque;

    private PrayerTImings prayerTimmings;

    public MosquenTimming(){}

    public MosquenTimming(Mosque mosque, PrayerTImings prayerTimmings) {
        this.mosque = mosque;
        this.prayerTimmings = prayerTimmings;
    }

    public Mosque getMosque() {
        return mosque;
    }

    public void setMosque(Mosque mosque) {
        this.mosque = mosque;
    }

    public PrayerTImings getPrayerTimmings() {
        return prayerTimmings;
    }

    public void setPrayerTimmings(PrayerTImings prayerTimmings) {
        this.prayerTimmings = prayerTimmings;
    }
}

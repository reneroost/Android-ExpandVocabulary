package ee.android.reneroost.isiklikprojekt.expandvocabulary.mudel;

import java.util.UUID;

public class Sonapaar {

    private UUID mId;
    private String mIngliseKeeles;
    private String mEestiKeeles;

    public Sonapaar() {
        mId = UUID.randomUUID();
    }

    public UUID saaId() {
        return mId;
    }

    public String saaIngliseKeeles() {
        return mIngliseKeeles;
    }

    public void maaraIngliseKeeles(String mIngliseKeeles) {
        this.mIngliseKeeles = mIngliseKeeles;
    }

    public String saaEestiKeeles() {
        return mEestiKeeles;
    }

    public void maaraEestiKeeles(String mEestiKeeles) {
        this.mEestiKeeles = mEestiKeeles;
    }
}

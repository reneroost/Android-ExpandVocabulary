package ee.android.reneroost.isiklikprojekt.expandvocabulary.mudel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ee.android.reneroost.isiklikprojekt.expandvocabulary.andmebaas.SonastikAndmebaasiAbistaja;
import ee.android.reneroost.isiklikprojekt.expandvocabulary.andmebaas.SonastikAndmebaasiSkeem.SonastikuTabel;

public class Sonastik {

    private static Sonastik sSonastik;

    private Context mKontekst;
    private SQLiteDatabase mAndmebaas;

    public Sonastik (Context kontekst) {
        mKontekst = kontekst.getApplicationContext();
        mAndmebaas = new SonastikAndmebaasiAbistaja(mKontekst).getWritableDatabase();
    }

    public void lisaSonapaar(Sonapaar s) {

    }

    public List<Sonapaar> saaSonastik() {
        return new ArrayList<>();
    }

    public Sonapaar saaSonapaar(UUID id) {
        return null;
    }

    private static ContentValues saaSisuVaartused(Sonapaar sonapaar) {
        ContentValues vaartused = new ContentValues();
        vaartused.put(SonastikuTabel.Tulbad.UUID, sonapaar.saaId().toString());
        vaartused.put(SonastikuTabel.Tulbad.INGLISEKEELES, sonapaar.saaIngliseKeeles());
        vaartused.put(SonastikuTabel.Tulbad.EESTIKEELES, sonapaar.saaEestiKeeles());

        return vaartused;
    }
}

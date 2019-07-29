package ee.android.reneroost.isiklikprojekt.expandvocabulary.andmebaas;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ee.android.reneroost.isiklikprojekt.expandvocabulary.andmebaas.SonastikuTabel;

public class SonastikAndmebaasiAbistaja extends SQLiteOpenHelper {

    private static final String ANDMEBAASI_NIMI = "SonastikAndmebaas.db";
    private static final int VERSIOON = 1;

    public SonastikAndmebaasiAbistaja(Context kontekst) {
        super(kontekst, ANDMEBAASI_NIMI, null, VERSIOON);
    }

    @Override
    public void onCreate(SQLiteDatabase andmebaas) {
        andmebaas.execSQL("create table " +
                        SonastikuTabel.NIMI + "(" +
                        SonastikuTabel.Tulbad.ID + " integer primary key autoincrement, " +
                        SonastikuTabel.Tulbad.INGLISEKEELES + ", " +
                        SonastikuTabel.Tulbad.EESTIKEELES + ")"
                );



        sisestaSonapaar(andmebaas, "insidious", "ahvatlev");
        sisestaSonapaar(andmebaas, "adumbrate", "üldjoontes ette kujutama");
        sisestaSonapaar(andmebaas, "slant", "kallak");
        sisestaSonapaar(andmebaas, "lenient", "leebe");
        sisestaSonapaar(andmebaas, "ubiquitous", "üldlevinud");
        sisestaSonapaar(andmebaas, "ternary", "kolmik");
        sisestaSonapaar(andmebaas, "courier", "kuller");
        sisestaSonapaar(andmebaas, "convolution", "pöörlemine");
        sisestaSonapaar(andmebaas, "pinnacle", "kõrgpunkt");
        sisestaSonapaar(andmebaas, "endeavour", "püüdlus");
        sisestaSonapaar(andmebaas, "plucky", "väle");
        sisestaSonapaar(andmebaas, "herald", "kuulutaja");
        sisestaSonapaar(andmebaas, "volatile", "püsimatu");
        sisestaSonapaar(andmebaas, "swivel", "pöörlema");
        sisestaSonapaar(andmebaas, "augmentation", "juurdekasv");
        sisestaSonapaar(andmebaas, "gizmo", "abivahend");
        sisestaSonapaar(andmebaas, "jettison", "(lasti) üle parda heitma");
        sisestaSonapaar(andmebaas, "warp", "vääne");
        sisestaSonapaar(andmebaas, "venerable", "kõrgeauline");
        sisestaSonapaar(andmebaas, "behest", "korraldus");
        sisestaSonapaar(andmebaas, "ancillary", "kõrval-");
        sisestaSonapaar(andmebaas, "convoluted", "kurruline");
        sisestaSonapaar(andmebaas, "gloomy", "sünge");
        sisestaSonapaar(andmebaas, "abate", "sumbuma");
        sisestaSonapaar(andmebaas, "tidbit", "maiuspala");
        sisestaSonapaar(andmebaas, "grout", "krohv");
        sisestaSonapaar(andmebaas, "terse", "napisõnaline");
        sisestaSonapaar(andmebaas, "ambivalent", "vastakas");
        sisestaSonapaar(andmebaas, "boon", "õnnistus");
        sisestaSonapaar(andmebaas, "unruly", "korratu");
        sisestaSonapaar(andmebaas, "whimsical", "pentsik");
        sisestaSonapaar(andmebaas, "formidable", "aukartustäratav");
        sisestaSonapaar(andmebaas, "confectionery", "kommipood");
        sisestaSonapaar(andmebaas, "ebullient", "pulbitsev");
        sisestaSonapaar(andmebaas, "superlative", "ülivõrre");
        sisestaSonapaar(andmebaas, "gleeful", "lustlik");
        sisestaSonapaar(andmebaas, "bespoken", "mahakuulutatud");
        sisestaSonapaar(andmebaas, "edible", "söödav");
        sisestaSonapaar(andmebaas, "sporadic", "juhuti esinev");
        sisestaSonapaar(andmebaas, "unsung", "ununenud");


    }

    @Override
    public void onUpgrade(SQLiteDatabase andmebaas, int vanaVersioon, int uusVersioon) {

    }

    private static void sisestaSonapaar(SQLiteDatabase andmebaas,
                                        String ingliseKeeles, String eestiKeeles) {
        ContentValues sonapaariVaartused = new ContentValues();
        sonapaariVaartused.put(SonastikuTabel.Tulbad.INGLISEKEELES, ingliseKeeles);
        sonapaariVaartused.put(SonastikuTabel.Tulbad.EESTIKEELES, eestiKeeles);

        andmebaas.insert(SonastikuTabel.NIMI, null, sonapaariVaartused);
    }

    public int saaSonadeHulk() {
        SQLiteDatabase andmebaas = this.getReadableDatabase();
        long hulk = DatabaseUtils.queryNumEntries(andmebaas, SonastikuTabel.NIMI);
        andmebaas.close();
        return (int) hulk;
    }

}

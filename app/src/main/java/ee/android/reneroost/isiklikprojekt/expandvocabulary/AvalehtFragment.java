package ee.android.reneroost.isiklikprojekt.expandvocabulary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import ee.android.reneroost.isiklikprojekt.expandvocabulary.andmebaas.SonastikAndmebaasiAbistaja;
import ee.android.reneroost.isiklikprojekt.expandvocabulary.andmebaas.SonastikuTabel;

public class AvalehtFragment extends Fragment {

    private static avalehtFragmentKuular kuular;

    private void vahetaAkent() {
        if(kuular != null) {
            kuular.naitaKoikiSonu();
        }
    }

    public interface avalehtFragmentKuular {
        void naitaKoikiSonu();
    }

    public static void maaraAvalehtFragmentKuular(avalehtFragmentKuular kuular) {
        AvalehtFragment.kuular = kuular;
    }

    private TextView suvalineIngliseKeelesTekstiVaade;
    private TextView suvalineEestiKeelesTekstiVaade;
    private String[] sonapaar;

    private int suvalineArv = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater taispuhuja, ViewGroup konteiner, Bundle savedInstanceState) {
        View vaade = taispuhuja.inflate(R.layout.fragment_avaleht, konteiner, false);

        suvalineIngliseKeelesTekstiVaade = vaade.findViewById(R.id.tekstivaade_avaleht_suvaline_sona_inglise_keeles);
        suvalineEestiKeelesTekstiVaade = vaade.findViewById(R.id.tekstivaade_avaleht_suvaline_sona_eesti_keeles);

        sonapaar = votaSuvalineSonapaar();
        kuvaSuvalineSonapaar(sonapaar);

        Button jargmineSonaNupp = vaade.findViewById(R.id.nupp_avaleht_jargmine_sona);
        jargmineSonaNupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sonapaar = votaSuvalineSonapaar();
                kuvaSuvalineSonapaar(sonapaar);
            }
        });

        Button kuvaKoikSonadNupp = vaade.findViewById(R.id.nupp_avaleht_kuva_koik_sonad);
        kuvaKoikSonadNupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vahetaAkent();
            }
        });

        return vaade;
    }

    private String[] votaSuvalineSonapaar() {

        Cursor kursor = null;
        String[] sonapaar = new String[2];

        SonastikAndmebaasiAbistaja sonastikAndmebaasiAbistaja = new SonastikAndmebaasiAbistaja(getActivity());

        Random suvaline = new Random();
        int uusSuvalineArv;
        do {
            uusSuvalineArv = suvaline.nextInt(sonastikAndmebaasiAbistaja.saaSonadeHulk()) + 1;
        } while (suvalineArv == uusSuvalineArv);
        suvalineArv = uusSuvalineArv;

        try {
            SQLiteDatabase andmebaas = sonastikAndmebaasiAbistaja.getReadableDatabase();
            kursor = andmebaas.query(SonastikuTabel.NIMI,
                    new String[] {SonastikuTabel.Tulbad.INGLISEKEELES, SonastikuTabel.Tulbad.EESTIKEELES},
                    "_id = ?",
                    new String[] {Integer.toString(suvalineArv)},
                    null, null, null);

            if (kursor != null && kursor.moveToFirst()) {
                sonapaar[0] = kursor.getString(0);
                sonapaar[1] = kursor.getString(1);
            }

            andmebaas.close();
        } catch(SQLiteException e) {
            Toast rost = Toast.makeText(getContext(), "Andmebaas pole saadaval", Toast.LENGTH_SHORT);
            rost.show();
        } finally {
            if (kursor != null) {
                kursor.close();
            }
        }
        return sonapaar;
    }

    private void kuvaSuvalineSonapaar(String[] sonapaar) {
        if (sonapaar[0].length() > 12) {
            suvalineIngliseKeelesTekstiVaade.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        } else {
            suvalineIngliseKeelesTekstiVaade.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f);
        }
        if (sonapaar[1].length() > 12) {
            suvalineEestiKeelesTekstiVaade.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        } else {
            suvalineEestiKeelesTekstiVaade.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f);
        }
        suvalineIngliseKeelesTekstiVaade.setText(sonapaar[0]);
        suvalineEestiKeelesTekstiVaade.setText(sonapaar[1]);
    }
}

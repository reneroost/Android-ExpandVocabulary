package ee.android.reneroost.isiklikprojekt.expandvocabulary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import ee.android.reneroost.isiklikprojekt.expandvocabulary.andmebaas.SonastikAndmebaasiAbistaja;
import ee.android.reneroost.isiklikprojekt.expandvocabulary.andmebaas.SonastikuTabel;

public class SonapaarideListFragment extends Fragment {

    private SQLiteDatabase andmebaas;
    private Cursor kursor;

    @Override
    public View onCreateView(@NonNull LayoutInflater taispuhuja, ViewGroup konteiner, Bundle savedInstanceState) {
        View vaade = taispuhuja.inflate(R.layout.fragment_sonapaaride_nimekiri, konteiner, false);

        ListView nimekiriSonad = vaade.findViewById(R.id.nimekiri_sonad);
        SQLiteOpenHelper sonastikuAndmebaasiAbistaja = new SonastikAndmebaasiAbistaja(getActivity());

        try {
            andmebaas = sonastikuAndmebaasiAbistaja.getReadableDatabase();
            kursor = andmebaas.query(SonastikuTabel.NIMI,
                    new String[] {
                            SonastikuTabel.Tulbad.ID,
                            SonastikuTabel.Tulbad.INGLISEKEELES,
                            SonastikuTabel.Tulbad.EESTIKEELES},
                    null, null, null, null, null);
            SimpleCursorAdapter nimekiriAdapter = new SimpleCursorAdapter(getActivity(),
                    R.layout.lihtne_sonapaar_paigutus,
                    kursor,
                    new String[] {SonastikuTabel.Tulbad.INGLISEKEELES, SonastikuTabel.Tulbad.EESTIKEELES},
                    new int[] {R.id.inglisekeelne, R.id.eestikeelne},
                    0);
            nimekiriSonad.setAdapter(nimekiriAdapter);
        } catch (SQLiteException erind) {
            Toast rost = Toast.makeText(getActivity(), "Andmebaas pole saadaval", Toast.LENGTH_SHORT);
            rost.show();
        }

        return vaade;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        kursor.close();
        andmebaas.close();
    }

}

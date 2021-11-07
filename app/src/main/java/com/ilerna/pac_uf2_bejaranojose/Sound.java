package com.ilerna.pac_uf2_bejaranojose;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;

/**
 * Clase que hereda de Servicio y que se encargara de reproducir el
 * fichero de audio en segundo plano
 *
 * @nota recordemos que hay que declararlo  en el manifest.xml
 * @nota remember that all the files under res should ONLY contain lowercase letters, numbers and underscores.
 * If you have your mp3 with the wrong name conditions will not compile.
 */
public class Sound extends Service {


     MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, R.string.startServ, Toast.LENGTH_SHORT).show();
        if(player!=null && player.isPlaying()) player.stop();
        player = MediaPlayer.create(this, R.raw.gotviolin);
        player.setLooping(true);
        player.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, R.string.stopServ, Toast.LENGTH_SHORT).show();
        if(player!=null) player.release();
        super.onDestroy();
    }
}

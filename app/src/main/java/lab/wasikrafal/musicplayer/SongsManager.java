package lab.wasikrafal.musicplayer;

/**
 * Created by Rafał on 06.06.2017.
 */

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongsManager {

    Cursor cursor;
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    Context context;

// Constructor

    public SongsManager(Context context){

        this.context = context;

    }

/**

 * Function to read all mp3 files from sdcard

 * and store the details in ArrayList

 * */

    public ArrayList<HashMap<String, String>> getPlayList(){

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {

                MediaStore.Audio.Media._ID,

                MediaStore.Audio.Media.ARTIST,

                MediaStore.Audio.Media.TITLE,

                MediaStore.Audio.Media.DATA,

                MediaStore.Audio.Media.DISPLAY_NAME,

                MediaStore.Audio.Media.DURATION

        };

        ContentResolver contentResolver = context.getContentResolver();

        cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection, selection,null,null);

        while(cursor.moveToNext()) {

            HashMap<String, String> song = new HashMap<String, String>();

            song.put("songID",cursor.getString(0));

            song.put("songTitle",cursor.getString(2));

            song.put("songPath",cursor.getString(3));

// Adding each song to SongList

            songsList.add(song);

        }

// return songs list array

        return songsList;

    }

}
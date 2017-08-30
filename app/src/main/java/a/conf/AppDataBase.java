package a.conf;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import a.data.dao.ContentDao;
import a.data.model.ContentModel;

/**
 * Created by Ashif on 22/8/17,August,2017
 * github.com/SheikhZayed
 */

@Database(entities = {ContentModel.Content.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

  public abstract ContentDao contentDao();
}

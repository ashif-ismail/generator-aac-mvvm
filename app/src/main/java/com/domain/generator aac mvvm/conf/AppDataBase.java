package com.domain.generator aac mvvm.conf;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.domain.generator aac mvvm.data.dao.ContentDao;
import com.domain.generator aac mvvm.data.model.ContentModel;

/**
 * Created by Ashif on 22/8/17,August,2017
 * github.com/SheikhZayed
 */

@Database(entities = {ContentModel.Content.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

  public abstract ContentDao contentDao();
}

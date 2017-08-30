package a.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.List;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */


public class ContentModel {

    private List<Content> content;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

  @Entity(tableName = "content")
  public static class Content {

    /**
     * id : 1
     * title : It Must be in Beard!
     * img : https://www.wired.com/wp-content/uploads/blogs/wiredenterprise/wp-content/uploads//2012/06/beard-programmers-final-two.png
     */

    @PrimaryKey
    private int id;
    private String title;
    private String img;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getImg() {
      return img;
    }

    public void setImg(String img) {
      this.img = img;
    }
  }
}

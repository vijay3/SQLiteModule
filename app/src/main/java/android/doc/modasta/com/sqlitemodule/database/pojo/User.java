package android.doc.modasta.com.sqlitemodule.database.pojo;

/**
 * Created by vijay.hiremath on 18/09/16.
 */
public class User
{
    Integer primary_key;
    String  title;
    String  name;
    String  created_on;

    public User(Integer primary_key, String title, String name, String created_on)
    {
        this.primary_key = primary_key;
        this.title = title;
        this.name = name;
        this.created_on = created_on;
    }

    public Integer getPrimary_key()
    {
        return primary_key;
    }

    public void setPrimary_key(Integer primary_key)
    {
        this.primary_key = primary_key;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCreated_on()
    {
        return created_on;
    }

    public void setCreated_on(String created_on)
    {
        this.created_on = created_on;
    }
}

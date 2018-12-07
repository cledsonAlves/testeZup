package br.com.testezup.model;

import android.arch.persistence.room.Entity;

@Entity(tableName = "ratings")
public class Ratings
{
    private String Source;

    private String Value;

    public String getSource ()
    {
        return Source;
    }

    public void setSource (String Source)
    {
        this.Source = Source;
    }

    public String getValue ()
    {
        return Value;
    }

    public void setValue (String Value)
    {
        this.Value = Value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Source = "+Source+", Value = "+Value+"]";
    }
}

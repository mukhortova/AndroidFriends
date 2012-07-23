package models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Party extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String user;

    @Constraints.Required
    public String title;

    @Constraints.Required
    public Date timeStart;

    public Date timeFinish;

    public String description;

    @Constraints.Required
    public Long latitude;

    @Constraints.Required
    public Long longitude;

    public static Finder<Long, Party> find = new Finder<Long, Party>(Long.class, Party.class);
}

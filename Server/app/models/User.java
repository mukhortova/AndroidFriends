package models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonIgnoreProperties({"id"})
@Entity
public class User extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String email;

    public Long latitude;

    public Long longitude;

    public String tags;

    @Constraints.Required
    public String token;

    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);
}

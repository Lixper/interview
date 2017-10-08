package lv.neueda.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Vladislavs Mitrosenko on 08.Oct.17.
 */
@Entity
@Table(name = "users")
public class User extends IdEntity {
    private String name;

    public String getName() {
        return name;
    }
}

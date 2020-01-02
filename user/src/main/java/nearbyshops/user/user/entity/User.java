package nearbyshops.user.user;

import javax.persistence.*;
import java.util.ArrayList;


@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "user")
    private List<Generictype> preferredShops = new ArrayList<>();
}

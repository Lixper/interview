package lv.neueda.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vladislavs Mitrosenko on 08.Oct.17.
 */
@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {
}

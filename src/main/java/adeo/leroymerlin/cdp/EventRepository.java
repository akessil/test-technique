package adeo.leroymerlin.cdp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface EventRepository extends Repository<Event, Long> {

    @Transactional(readOnly = false)
    void delete(Long eventId);

    List<Event> findAllBy();

    @Modifying
    @Query("update Event e set e.comment = :comment , e.nbStars = :nbStars where e.id = :id")
    void saveCommentAndNbStars(@Param("id") Long id, @Param("nbStars") int nbStars, @Param("comment") String comment);
}

package tz.project.guidebook.landmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tz.project.guidebook.landmark.Landmark;

public interface LandmarkRepository extends JpaRepository<Landmark, Long>, JpaSpecificationExecutor<Landmark> {

    @Modifying(flushAutomatically = true)
    @Query(value = """
            UPDATE landmarks l SET l.average_rating = (
                SELECT AVG(r.rating)
                FROM rating_records r
                WHERE r.landmark_id = :id
                GROUP BY r.landmark_id
                )
            WHERE l.id = :id
            """, nativeQuery = true)
    void setAverageRating(@Param("id") Long id);
}

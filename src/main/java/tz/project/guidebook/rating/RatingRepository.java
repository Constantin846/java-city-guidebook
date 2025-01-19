package tz.project.guidebook.rating;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingRecord, Long> {
}

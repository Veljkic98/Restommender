package pro.restommender.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pro.restommender.model.Restaurant;

@Repository
@Transactional
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM restaurant where name ilike %:name%")
    public List<Restaurant> findAllNameRegex(@Param("name") String name);

}

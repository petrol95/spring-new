package com.geekbrains.dao;

import com.geekbrains.entities.Disk;
import com.geekbrains.entities.DiskHib;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiskHibRepository extends CrudRepository<DiskHib, Long> {

    List<DiskHib> findAllByProducedYearAndId(int producedYear, Long id);

//    @Query("SELECT d FROM disks d WHERE LOWER(d.title) = LOWER(:name)")
//    Disk retrieveByName(@Param("name") String name);

    // And Or Is/Equals Between IsNull IsNotNull GreaterThan GreaterThanEqual
    // findByActiveTrue findByActiveFalse
    // findByTitleIgnoreCase
}

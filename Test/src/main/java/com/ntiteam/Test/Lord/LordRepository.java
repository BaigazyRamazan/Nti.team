package com.ntiteam.Test.Lord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LordRepository extends JpaRepository<Lord,Long> {

//    SELECT * FROM Lord  ORDER BY age ASC LIMIT 10
    List<Lord> findTop10ByOrderByAgeAsc();
}

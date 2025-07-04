package com.canteen.canteensystem.repository;

import com.canteen.canteensystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCanteenId(Long canteenId);
}

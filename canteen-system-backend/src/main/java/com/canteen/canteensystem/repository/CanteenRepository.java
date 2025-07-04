package com.canteen.canteensystem.repository;

import com.canteen.canteensystem.model.Canteen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanteenRepository extends JpaRepository<Canteen, Long> {

}

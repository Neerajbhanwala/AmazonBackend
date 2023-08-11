package com.StateCity.Trip.ArtRepositorys;


import com.StateCity.Trip.ProductsEntity.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


public interface ArtRepository extends JpaRepository<Art,Integer>
{
    List<Art> findByCategory(String category);
}

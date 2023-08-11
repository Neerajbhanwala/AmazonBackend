package com.StateCity.Trip.ArtServices;


import com.StateCity.Trip.ArtRepositorys.ArtRepository;
import com.StateCity.Trip.ProductsEntity.Art;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtService
{
    @Autowired
    private ArtRepository artRepository;

    public Art saveArt(Art art)
    {
        return artRepository.save(art);
    }

    public List<Art> getAllArt()
    {
        List<Art> art =artRepository.findAll();
        return art;
    }

    public Optional<Art> getById(int id){
        Optional<Art> art1 = artRepository.findById(id);

        return art1;
    }

    public List<Art> findByCategory(String category){
        return artRepository.findByCategory(category);
    }



}
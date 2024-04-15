package com.banque.persistance.repository;

import com.banque.persistance.model.Compte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends CrudRepository<Compte, Integer> {
}

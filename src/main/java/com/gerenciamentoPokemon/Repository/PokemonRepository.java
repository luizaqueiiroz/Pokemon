package com.gerenciamentoPokemon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciamentoPokemon.Entities.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

}

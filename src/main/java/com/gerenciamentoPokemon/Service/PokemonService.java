package com.gerenciamentoPokemon.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamentoPokemon.Entities.Pokemon;
import com.gerenciamentoPokemon.Repository.PokemonRepository;

@Service
public class PokemonService {
	
	private final PokemonRepository pokemonRepository;
	
	@Autowired
	public PokemonService(PokemonRepository pokemonRepository) {
		this.pokemonRepository = pokemonRepository;
	}
	public List<Pokemon> buscaTodosPokemons(){
		return pokemonRepository.findAll();
	}
	public Pokemon buscaPokemonId(Long id) {
		Optional <Pokemon> Pokemon = pokemonRepository.findById(id);
		return Pokemon.orElse(null);
	}
	public Pokemon salvaPokemon(Pokemon Pokemon) {
		return pokemonRepository.save(Pokemon);
	}
	public Pokemon alterarPokemon(Long id, Pokemon alterarP) {
		Optional <Pokemon> existePokemon = pokemonRepository.findById(id);
		if (existePokemon.isPresent()) {
			alterarP.setId(id);
			return pokemonRepository.save(alterarP);
		}
		return null;
	}
	public boolean apagarPokemon(Long id) {
		Optional <Pokemon> existePokemon = pokemonRepository.findById(id);
		if(existePokemon.isPresent()) {
			pokemonRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

package com.gerenciamentoPokemon.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamentoPokemon.Entities.Pokemon;
import com.gerenciamentoPokemon.Service.PokemonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
	
	private final PokemonService pokemonService;
	
	@Autowired
	public PokemonController(PokemonService pokemonService) {
		this.pokemonService = pokemonService;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Pokemon> buscaPokemonControlId(@PathVariable Long id){
		Pokemon pokemon = pokemonService.buscaPokemonId(id);
		if(pokemon != null) {
			return ResponseEntity.ok(pokemon);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	public ResponseEntity<List<Pokemon>> buscaTodosPokemonsControl(){
		List<Pokemon> Pokemons = pokemonService.buscaTodosPokemons();
		return ResponseEntity.ok(Pokemons);
	}
	@PostMapping ("/")
	public ResponseEntity<Pokemon> salvaPokemonsControl(@RequestBody @Valid Pokemon pokemon){
		Pokemon salvaPokemon = pokemonService.salvaPokemon(pokemon);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaPokemon);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Pokemon> alteraPokemonControl(@PathVariable Long id, @RequestBody @Valid Pokemon pokemon){
		Pokemon alteraPokemon = pokemonService.alterarPokemon(id, pokemon);
		if(alteraPokemon != null) {
			return ResponseEntity.ok(pokemon);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Pokemon> apagaPokemonControl(@PathVariable Long id){
		boolean apagar = pokemonService.apagarPokemon(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}

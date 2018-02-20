package io.github.girirajvyas.heroes.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HeroEndpoint {
	
	List<Hero> heroes = new ArrayList<>();
	
	{
		heroes.add(new Hero(11, "Mr. Nice"));
		heroes.add(new Hero(12, "Narco"));
		heroes.add(new Hero(13, "Bombasto"));
		heroes.add(new Hero(14, "Celeritas"));
		heroes.add(new Hero(15, "Magneta"));
	}
	
	/**
	 * unmodifiable list is returned and hence unable to add elements
	 */
	Hero heroesArray[] = { new Hero(11, "Mr. Nice"), new Hero(12, "Narco"), new Hero(13, "Bombasto"),
			new Hero(14, "Celeritas"), new Hero(15, "Magneta")};
	
	/*@RequestMapping("/heroes")
	public List<Hero> getHeroes() {
		System.out.println("Inside heroes");
		return heroes;
	}*/
	
	@RequestMapping("/heroes")
	public List<Hero> searchHeroesByName(@RequestParam(value="name", required=false) String name){
		System.out.println("searchHeroesByName" + name);
		String my = "Mr. Nice";
		System.out.println("Mr. Nice contains n: " + my.contains("n"));
		System.out.println("Mr. Nice contains N: " + my.contains("N"));
		System.out.println("Mr. Nice indexOf n: " + my.indexOf("n"));
		System.out.println("Mr. Nice indexOf N: " + my.indexOf("N"));
		if(name!=null && !name.trim().isEmpty()){
			List<Hero> filteredHeroes = heroes.stream().filter(hero -> (hero.getName().toLowerCase().contains(name.toLowerCase()))).collect(Collectors.toList());
			/*for(Hero hero : heroes){
				if(hero.getName().contains(name)){
					filteredHeroes.add(hero);
				}
			}*/
			return filteredHeroes;
		}
		return heroes;
	}
	
	@RequestMapping("/heroes/{id}")
	public Hero getHero(@PathVariable("id") Integer id) {
		System.out.println("Inside getHero with id: " + id);
		Optional<Hero> hero2 = heroes.stream().filter(hero -> hero.getId() == id).findFirst();
		if(hero2.isPresent())
			return heroes.stream().filter(hero -> hero.getId() == id).findFirst().get();
		return null;
	}
	
	@RequestMapping(value="/heroes/{id}", method = RequestMethod.PUT)
	public boolean updateHero(@PathVariable("id") Integer id, @RequestBody Hero hero){
		System.out.println(hero.toString() + id);

		heroes.stream().map( h -> {
			if(h.getId() == id){
				return hero;
			}else {
				return h;
			}
		});
		
		System.out.println(heroes);
		Hero hFromArray = heroes.stream().filter(h -> h.getId() == id).findFirst().get();
		hFromArray.setId(hero.getId());
		hFromArray.setName(hero.getName());
		
		return true;
	}
	
	@RequestMapping(value="/heroes", method = RequestMethod.POST)
	public Hero addHero(@RequestBody Hero hero){
		System.out.println(hero);
		if(hero.getId() == null){
			heroes.sort(new Comparator<Hero>() {
				public int compare(Hero h, Hero h2){
					return h.getId().compareTo(h2.getId());
				}
			});
			System.out.println(heroes.get(heroes.size()-1).getId());
			hero.setId(heroes.get(heroes.size()-1).getId()+1);
		}
		heroes.add(hero);
		
		return hero;
	}
	
	@RequestMapping(value="/heroes/{id}", method = RequestMethod.DELETE)
	public boolean deleteHero(@PathVariable("id") Integer id){

		Iterator<Hero> itr = heroes.iterator();
		while(itr.hasNext()){
			if(itr.next().getId() == id){
				System.out.println("removing element with id: " + id);
				itr.remove();
			}
		}
		
		return true;
	}
}

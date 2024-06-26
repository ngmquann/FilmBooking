package com.cinemas.initializer;

import com.cinemas.entities.Celebrity;
import com.cinemas.enums.RoleCeleb;
import com.cinemas.repositories.CelebrityRepository;
import com.cinemas.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class CelebrityDataInitializer {
    @Autowired
    CelebrityRepository celebrityRepository;
    @Autowired
    CountryRepository countryRepository;

    public void initCelebrities() {
        if (celebrityRepository.count() == 0) {
            Celebrity[] celebrities = {

                    // Actors
                    Celebrity.builder().id(1).name("Leonardo DiCaprio").slug("leonardo-dicaprio")
                            .dateOfBirth(LocalDate.of(1974, 11, 11))
                            .description("Leonardo DiCaprio is an American actor and film producer known for his roles in Titanic, Inception, and The Revenant.")
                            .biography("A versatile actor, DiCaprio has won several awards including an Academy Award for Best Actor.")
                            .role(RoleCeleb.ACTOR)
                            .image("ACTOR/ed0b4f33-2858-404b-9dfc-c837702ed780")
                            .country(countryRepository
                                    .findById(getRandomCountryId()))
                            .build(),

                    Celebrity.builder().id(2).name("Scarlett Johansson").slug("scarlett-johansson").dateOfBirth(LocalDate.of(1984, 11, 22)).description("Scarlett Johansson is an American actress and singer. She is known for her work in films such as Lost in Translation, The Avengers, and Marriage Story.").biography("Johansson is one of the world's highest-paid actresses and has received multiple awards and nominations.").role(RoleCeleb.ACTOR).image("ACTOR/c5c74f74-3a8f-4681-bead-b22e76f76a4b").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(3).name("Brad Pitt").slug("brad-pitt").dateOfBirth(LocalDate.of(1963, 12, 18)).description("Brad Pitt is an American actor and film producer known for his roles in Fight Club, Ocean's Eleven, and Once Upon a Time in Hollywood.").biography("Pitt has received multiple accolades, including an Academy Award for Best Supporting Actor.").role(RoleCeleb.ACTOR).image("ACTOR/de65bcf5-cb00-4cd3-821b-23a6960c712b").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(4).name("Meryl Streep").slug("meryl-streep").dateOfBirth(LocalDate.of(1949, 6, 22)).description("Meryl Streep is an American actress often described as the 'best actress of her generation'. She is known for her versatility and accent adaptation.").biography("Streep has won three Academy Awards and has been nominated for many others.").role(RoleCeleb.ACTOR).image("ACTOR/c95dfaf6-195d-4075-bcc9-4e44a396a566").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(5).name("Tom Hanks").slug("tom-hanks").dateOfBirth(LocalDate.of(1956, 7, 9)).description("Tom Hanks is an American actor and filmmaker known for his comedic and dramatic roles in films such as Forrest Gump, Saving Private Ryan, and Cast Away.").biography("Hanks is one of the most popular and recognizable film stars worldwide.").role(RoleCeleb.ACTOR).image("ACTOR/3955b722-a5f3-4651-a72d-6c9bdd56fa28").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(6).name("Cate Blanchett").slug("cate-blanchett").dateOfBirth(LocalDate.of(1969, 5, 14)).description("Cate Blanchett is an Australian actress and theatre director known for her roles in Elizabeth, The Lord of the Rings trilogy, and Blue Jasmine.").biography("Blanchett has won two Academy Awards and three Golden Globe Awards.").role(RoleCeleb.ACTOR).image("ACTOR/959a4c7f-d381-40ac-8e98-81b3df6f66e1").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(7).name("Daniel Day-Lewis").slug("daniel-day-lewis").dateOfBirth(LocalDate.of(1957, 4, 29)).description("Daniel Day-Lewis is an English actor known for his meticulous method acting and for roles in films such as My Left Foot, There Will Be Blood, and Lincoln.").biography("Day-Lewis is the only male actor to have won three Academy Awards for Best Actor.").role(RoleCeleb.ACTOR).image("ACTOR/3463a8b1-cc4a-4deb-aab7-4751e087d60d").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(8).name("Natalie Portman").slug("natalie-portman").dateOfBirth(LocalDate.of(1981, 6, 9)).description("Natalie Portman is an Israeli-American actress known for her roles in Black Swan, V for Vendetta, and the Star Wars prequel trilogy.").biography("Portman has received various accolades, including an Academy Award and two Golden Globe Awards.").role(RoleCeleb.ACTOR).image("ACTOR/18cc2e19-d2cd-4611-8d2b-55c7d96587be").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(9).name("Denzel Washington").slug("denzel-washington").dateOfBirth(LocalDate.of(1954, 12, 28)).description("Denzel Washington is an American actor, director, and producer known for his performances in films like Training Day, Malcolm X, and Fences.").biography("Washington has received two Academy Awards and three Golden Globe Awards.").role(RoleCeleb.ACTOR).image("ACTOR/fd3eda4e-5b67-472a-9b2e-c4efd16696bb").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(10).name("Penélope Cruz").slug("penelope-cruz").dateOfBirth(LocalDate.of(1974, 4, 28)).description("Penélope Cruz is a Spanish actress and model known for her roles in Vanilla Sky, Vicky Cristina Barcelona, and Volver.").biography("Cruz has won an Academy Award and a BAFTA Award.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/cc933d56-0a01-4ca8-94ae-04b55937b3e8").country(countryRepository.findById(getRandomCountryId())).build(),

                    // Directors
                    Celebrity.builder().id(11).name("Steven Spielberg").slug("steven-spielberg").dateOfBirth(LocalDate.of(1946, 12, 18)).description("Steven Spielberg is an American film director, producer, and screenwriter. He is one of the most popular directors and producers in film history.").biography("Spielberg's filmography includes Jaws, E.T., Jurassic Park, and Schindler's List.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/951b331a-3d08-48a1-85d8-4c319826c172").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(12).name("Christopher Nolan").slug("christopher-nolan").dateOfBirth(LocalDate.of(1970, 7, 30)).description("Christopher Nolan is a British-American film director, producer, and screenwriter known for his distinctive, mind-bending films.").biography("Nolan's notable works include Inception, The Dark Knight Trilogy, and Interstellar.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/43487dc1-79c9-477f-993a-942c27f8d776").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(13).name("Quentin Tarantino").slug("quentin-tarantino").dateOfBirth(LocalDate.of(1963, 3, 27)).description("Quentin Tarantino is an American film director, screenwriter, producer, and actor known for his stylistic, non-linear storytelling.").biography("Tarantino's notable films include Pulp Fiction, Kill Bill, and Django Unchained.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/fbf92eb2-7025-4840-aa2b-17e095557a73").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(14).name("Martin Scorsese").slug("martin-scorsese").dateOfBirth(LocalDate.of(1942, 11, 17)).description("Martin Scorsese is an American film director, producer, screenwriter, and actor. He is widely regarded as one of the greatest directors in film history.").biography("Scorsese's notable films include Taxi Driver, Goodfellas, and The Wolf of Wall Street.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/ef6f5b49-2744-412a-8982-f721085b8f86").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(15).name("James Cameron").slug("james-cameron").dateOfBirth(LocalDate.of(1954, 8, 16)).description("James Cameron is a Canadian film director, producer, and screenwriter known for his innovative use of special effects.").biography("Cameron's notable films include Titanic, Avatar, and Terminator 2: Judgment Day.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/4255dc04-d3d8-4c04-a268-2aec2e0b59cb").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(16).name("Peter Jackson").slug("peter-jackson").dateOfBirth(LocalDate.of(1961, 10, 31)).description("Peter Jackson is a New Zealand film director, screenwriter, and film producer. He is best known for directing The Lord of the Rings trilogy.").biography("Jackson's work on The Lord of the Rings and The Hobbit trilogies has earned him international acclaim.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/4255dc04-d3d8-4c04-a268-2aec2e0b59cb").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(17).name("Ridley Scott").slug("ridley-scott").dateOfBirth(LocalDate.of(1937, 11, 30)).description("Ridley Scott is a British film director and producer known for his work on a wide range of films, from science fiction to historical epics.").biography("Scott's notable films include Alien, Blade Runner, and Gladiator.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/94692583-3eb5-44f9-8563-3c6ce51ad505").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(18).name("Alfonso Cuarón").slug("alfonso-cuaron").dateOfBirth(LocalDate.of(1961, 11, 28)).description("Alfonso Cuarón is a Mexican film director, screenwriter, producer, and editor known for his innovative storytelling and visual style.").biography("Cuarón's notable films include Gravity, Roma, and Children of Men.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/af37d02c-5a64-4fb1-9fbd-065cddb0d7c2").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(19).name("Guillermo del Toro").slug("guillermo-del-toro").dateOfBirth(LocalDate.of(1964, 10, 9)).description("Guillermo del Toro is a Mexican film director, producer, screenwriter, and author known for his love of fantasy and horror.").biography("Del Toro's notable films include Pan's Labyrinth, The Shape of Water, and Pacific Rim.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/331ecbf6-cfc9-454b-9fd5-d6525652203d").country(countryRepository.findById(getRandomCountryId())).build(),

                    Celebrity.builder().id(20).name("David Fincher").slug("david-fincher").dateOfBirth(LocalDate.of(1962, 8, 28)).description("David Fincher is an American film director known for his dark and stylish thrillers.").biography("Fincher's notable films include Fight Club, The Social Network, and Gone Girl.").role(RoleCeleb.DIRECTOR).image("DIRECTOR/e9c90b5b-e986-48f9-85a8-41430e013eff").country(countryRepository.findById(getRandomCountryId())).build()};

            celebrityRepository.saveAll(List.of(celebrities));
        }
    }

    private static int getRandomCountryId() {
        return ThreadLocalRandom.current().nextInt(1, 21);
    }
}

package com.example.prueba;


import com.example.prueba.Model.Persona;
import com.example.prueba.Model.PersonaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class PersonaRepositoryTest {
    @Autowired private PersonaRepository repo;

    @Test
    public void ingresarPersonaTest(){
        Persona persona= new Persona(null,"Juan","Hernandez","juan@gmail.com",321014745);

        Persona GuardarPersona= repo.save(persona);

        Assertions.assertThat(GuardarPersona).isNotNull();
        Assertions.assertThat(GuardarPersona.getId()).isGreaterThan(0);
    }

    @Test
    public void listarPersonasTest(){
        Iterable<Persona> personas= repo.findAll();
        Assertions.assertThat(personas).hasSizeGreaterThan(0);

        for (Persona persona : personas){
            System.out.println(persona);
        }
    }


    @Test
    public void actualizarTest(){
        Integer idPersona=2;
        Optional<Persona> optionalPersona=repo.findById(idPersona);
        Persona persona=optionalPersona.get();
        persona.setApellido("Ramirez");
        repo.save(persona);

        Persona updatePersona=repo.findById(idPersona).get();
        Assertions.assertThat(updatePersona.getApellido()).isEqualTo("Ramirez");
    }


    @Test
    public void eliminarPersonaTest(){
        Integer idPersona= 2;
        repo.deleteById(idPersona);

        Optional<Persona>optionalPersona= repo.findById(idPersona);
        Assertions.assertThat(optionalPersona).isNotPresent();

    }



}

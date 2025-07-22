package com.example.backend_auth0;

import com.example.backend_auth0.data.entities.Administrador;
import com.example.backend_auth0.data.entities.Dentista;
import com.example.backend_auth0.data.entities.Especialidad;
import com.example.backend_auth0.data.entities.Usuario;
import com.example.backend_auth0.data.repository.AdministradorRepository;
import com.example.backend_auth0.data.repository.DentistaRepository;
import com.example.backend_auth0.data.repository.EspecialidadRepository;
import com.example.backend_auth0.data.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class BackendAuth0Application {

	public static void main(String[] args) {
		SpringApplication.run(BackendAuth0Application.class, args);
		System.out.println("Backend Auth0 Application is running! Ready to handle requests.");
	}

	@Bean
	public CommandLineRunner dataLoader(
			UsuarioRepository usuarioRepository,
			EspecialidadRepository especialidadRepository,
			AdministradorRepository administradorRepository,
			DentistaRepository dentistaRepository
	){
		return args -> {

			Especialidad odontologia = new Especialidad("Odontología General");
			Especialidad ortodoncia = new Especialidad("Ortodoncia");
			Especialidad endodoncia = new Especialidad("Endodoncia");
			Especialidad periodoncia = new Especialidad("Periodoncia");
			Especialidad cirugiaOral = new Especialidad("Cirugía Oral");
			especialidadRepository.saveAll(List.of(
					odontologia,
					ortodoncia,
					endodoncia,
					periodoncia,
					cirugiaOral
			));

			Usuario adminUser = Usuario.builder()
					.auth0Id("google-oauth2|108508741747669676481")
					.nombre("Ger")
					.apellido("Hidalgo")
					.email("germhidalgo@gmail.com")
					.build();

			usuarioRepository.save(adminUser);

			Administrador administrador = Administrador.builder()
					.usuario(adminUser)
					.build();

			administradorRepository.save(administrador);

			Usuario dentistaUser = Usuario.builder()
					.auth0Id("google-oauth2|112729835259215619527")
					.nombre("Florencio")
					.apellido("Saul")
					.email("aftercodesoftware@gmail.com")
					.build();

			usuarioRepository.save(dentistaUser);

			Dentista dentista = Dentista.builder()
					.usuario(dentistaUser)
					.matricula("123456")
					.especialidad(odontologia)
					.build();

			dentistaRepository.save(dentista);
		};
	}

}

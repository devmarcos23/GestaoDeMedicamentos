package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.security.UsuarioService;


/*@Author https://github.com/devmarcos23*/

@SpringBootApplication
public class GestaoMedicamentoApplication implements CommandLineRunner{
	//@
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public static void main(String[] args) {
		 	
		SpringApplication.run(GestaoMedicamentoApplication.class, args);
		try {
            String url = "http://localhost:8080/login"; // Substitua pela URL do seu aplicativo
            String osName = System.getProperty("os.name").toLowerCase();

            if (osName.contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
                Runtime.getRuntime().exec("open " + url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void run(String... args) throws Exception {
		usuarioService.criarUsuarioSuperAdminCasoNaoExista();
		
	}

}

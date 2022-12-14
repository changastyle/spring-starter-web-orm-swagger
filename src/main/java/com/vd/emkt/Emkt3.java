package com.vd.emkt;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.time.Duration;

@SpringBootApplication
@Controller
@EnableJpaRepositories("com.vd.emkt.repo")
public class Emkt3
{
    public static ApplicationContext appContext;
    public static  Environment environment;
    private static String IP;
    private static String NOMBRE_PROYECTO;

    public static void main(String[] args)
    {
        SpringApplication.run(Emkt3.class, args);
        String puertoServer = environment.getProperty("local.server.port");

        System.out.println("CORRIENDO EMKT 3 EN http://localhost:" + puertoServer);
        IP = dameVariableEntorno("IP");
        NOMBRE_PROYECTO = dameVariableEntorno("NOMBRE_PROYECTO");
        System.out.println("IP: " + IP);
        System.out.println("NOMBRE_PROYECTO: " + NOMBRE_PROYECTO);
    }

    @GetMapping(value = "/api")
    public static RedirectView swagger()
    {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("../swagger-ui.html");

        return redirectView;
    }
    @GetMapping(value = "/")
    public static RedirectView web()
    {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("web/");

        return redirectView;
    }



    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .pathsToMatch("/api/**")
                .build();
    }
    // NECESARY:
    @Autowired
    public void setearEnvironment(Environment environment) {
        Emkt3.environment = environment;
    }
    private static String dameVariableEntorno(String nombreVariable)
    {
        String rta = environment.getProperty(nombreVariable);
        return rta;
    }
    public static ApplicationContext dameAppContext() {
        return appContext;
    }


    public static String getIP()
    {
        return IP;
    }

    public static String getNombreProyecto()
    {
        return NOMBRE_PROYECTO;
    }

    public static String getURLVisualizacionWEB()
    {
        return "http://" + IP + ":8081/" + NOMBRE_PROYECTO + "/upload/";
    }
    public static String getURLFS()
    {
        return "C:\\xampp\\htdocs\\" + NOMBRE_PROYECTO + "\\upload\\";
    }

}

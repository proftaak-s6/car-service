package nl.fontysproject.government.api.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("")
@OpenAPIDefinition(info = @Info(
        title = "Rekeningrijden | Cars API"
),
servers = {
        @Server(url = "http://localhost:9100")
})
public class Api extends Application {

}

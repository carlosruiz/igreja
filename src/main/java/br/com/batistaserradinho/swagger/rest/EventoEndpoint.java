package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.swagger.model.Evento;
import com.wordnik.swagger.annotations.Api;
import javax.ws.rs.Path;

/**
 * @author Carlos
 */

//@Api(value = "Evento", description = "Endpoint para gerenciamento de Eventos")
//@Path("/eventos")
public class EventoEndpoint extends GenericEndpoint {
   
    public EventoEndpoint() {
        super(Evento.class, "eventos");
    }  
}

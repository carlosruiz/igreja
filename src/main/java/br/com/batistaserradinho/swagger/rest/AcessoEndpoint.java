package br.com.batistaserradinho.swagger.rest;


import br.com.batistaserradinho.business.ControleDeAcessoBusiness;
import br.com.batistaserradinho.business.TokenBusiness;
import br.com.batistaserradinho.swagger.model.Acesso;
import br.com.batistaserradinho.swagger.model.MembroCargo;
import br.com.batistaserradinho.swagger.model.Usuario;
import br.com.batistaserradinho.swagger.service.CrudService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.codec.DecoderException;

/**
 *
 * @author cruiz
 */
@Api(value = "Acesso", description = "Endpoint para visualização de acessos de um usuario")
@Path("/acessos")
public class AcessoEndpoint {
    private final CrudService crudService = new CrudService();
    
     /**
     * Retorna lista completa
     * @param token
     */
    @GET
    @Path("/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna todos os acessos permitidos", notes = "Retorna a lista completa de acessos ", response = Acesso.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = Acesso.class)
        , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getUsuarios(@ApiParam(name = "token", value = "Token", required = true) 
                                 @PathParam("token") String token) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, DecoderException, UnsupportedEncodingException, Exception {

        Response.Status statusDoToken = new TokenBusiness().validarToken(token);       
        String mensagem = statusDoToken.equals(Response.Status.UNAUTHORIZED) ? "Usuario ou senha inválido" : statusDoToken.equals(Response.Status.BAD_REQUEST) 
                                                                    ? "Token Expirado!" : statusDoToken.equals(Response.Status.NOT_ACCEPTABLE) ? "Token inválido ou ocorreu um erro!" : null;
        if(mensagem != null)
           return Response.status(statusDoToken).entity(mensagem).type(MediaType.TEXT_PLAIN_TYPE).build();
                
        String login = new TokenBusiness().obterLoginDeUmToken(token);
        Usuario usuario = new Usuario();
        usuario.setId(login);
        usuario =(Usuario) crudService.obter(usuario);

        Collection<MembroCargo> membroCargos = usuario.getMembroId().getMembroCargoCollection();

        List<Acesso> acessosDoUsuario = new ArrayList();
        
        for(MembroCargo membroCargo : membroCargos){
            acessosDoUsuario.addAll(membroCargo.getCargoId().getAcessoCollection());
        }        
                      
       return Response.ok(acessosDoUsuario).build();
    }
}

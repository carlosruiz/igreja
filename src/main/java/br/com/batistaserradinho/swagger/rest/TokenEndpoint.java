package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.EnvelopeJson.TokenEnvelopeJson;
import br.com.batistaserradinho.Util.Criptografia;
import br.com.batistaserradinho.business.TokenBusiness;
import br.com.batistaserradinho.swagger.model.Usuario;
import br.com.batistaserradinho.swagger.service.CrudService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * @author Carlos
 */

@Api(value = "Token", description = "Endpoint obter o token")
@Path("/token")
public class TokenEndpoint {

        private final CrudService crudService = new CrudService();
        
     /**
     * Retorna o token atraves do login e senha
     * @param login
     * @param senha
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna o token valido", notes = "Retorna o token valido para o login informado. O Token expira em 15 minutos", response = TokenEnvelopeJson.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = TokenEnvelopeJson.class)
                          , @ApiResponse(code = 500, message = "Erro interno no servidor")})
        
    public Response getToken(@ApiParam(name = "login", value = "login do usuario", required = true) @QueryParam("login") String login,
                             @ApiParam(name = "senha", value = "senha do usuario", required = true) @QueryParam("senha") String senha) 
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchAlgorithmException{
      
        String token;
        TokenEnvelopeJson tokenEnvelope = new TokenEnvelopeJson();
        Usuario usuario = new Usuario();
        usuario.setId(login);
        usuario = (Usuario) crudService.obter(usuario);
        
        if(usuario != null && usuario.getSenha().equals(Criptografia.criptografar(senha))){
            token = new TokenBusiness().construirToken(usuario);           
            tokenEnvelope.setMensagem("Token Gerado com Sucesso!");
            tokenEnvelope.setToken(token);
        }
        else
            return Response.status(Status.UNAUTHORIZED).entity("Usuario ou senha inválido!").type(MediaType.TEXT_PLAIN_TYPE).build();   
        
        return Response.ok(tokenEnvelope).build();
    }  
    
     /**
     * Valida o token
     * @param token
     */
    @GET
    @Path("/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Valida o token gerado", notes = "Verifica se o token gerado é válido", response = TokenEnvelopeJson.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = TokenEnvelopeJson.class)
                          , @ApiResponse(code = 500, message = "Erro interno no servidor")})
        
    public Response getToken(@ApiParam(name = "token", value = "informe o token", required = true) @QueryParam("token") String token) 
            throws UnsupportedEncodingException, ParseException, Exception{
      
        Status statusDoToken = new TokenBusiness().validarToken(token);       
        String mensagem = statusDoToken.equals(Status.UNAUTHORIZED) ? "Usuario ou senha inválido" : statusDoToken.equals(Status.BAD_REQUEST) ? "Token Expirado!" : "Token é Valido";
        
        TokenEnvelopeJson tokenEnvelope = new TokenEnvelopeJson();
        tokenEnvelope.setMensagem(mensagem);
        tokenEnvelope.setToken(token);      
        
        return Response.status(statusDoToken).entity(tokenEnvelope).build();
    }  
}
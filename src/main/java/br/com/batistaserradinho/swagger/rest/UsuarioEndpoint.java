package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.EnvelopeJson.CadastroEnvelopeJson.Cadastro;
import br.com.batistaserradinho.business.ControleDeAcessoBusiness;
import br.com.batistaserradinho.swagger.model.Celula;
import br.com.batistaserradinho.swagger.model.CelulaMembro;
import br.com.batistaserradinho.swagger.model.Membro;
import br.com.batistaserradinho.swagger.model.Situacao;
import br.com.batistaserradinho.swagger.model.Usuario;
import br.com.batistaserradinho.swagger.service.CrudService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.apache.commons.codec.DecoderException;

/**
 *
 * @author cruiz
 */
@Api(value = "Usuario", description = "Endpoint para gerenciamento de cadstros de usuarios")
@Path("/usuarios")
public class UsuarioEndpoint {
    
    private final CrudService crudService = new CrudService();
    ControleDeAcessoBusiness controleDeAcesso = new ControleDeAcessoBusiness();
    
     /**
     * Retorna lista completa
     * @param token
     */
    @GET
    @Path("/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna todos os usuarios", notes = "Retorna a lista completa de usuarios ", response = Usuario.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = Usuario.class)
        , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getUsuarios(@ApiParam(name = "token", value = "Token", required = true) 
                                 @PathParam("token") String token) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, DecoderException, UnsupportedEncodingException, Exception {

        String descricaoDeAcesso = controleDeAcesso.obterNomeDaClasse();
        ResponseBuilder controle = controleDeAcesso.controlarAcesso(token, descricaoDeAcesso);
        if(controle != null)
            return controle.build();
        
        List<Usuario> usuarios = crudService.obterTodos(Usuario.class.getName());
        return Response.ok(usuarios).build();
    }
    
     /**
     * Retorna por id informado
     * @param token
     * @param usuarioId
     */
    @GET
    @Path("/{token}/{usuarioId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna o usuario pelo login", notes = "Retorna o usuario cadastrado pelo login", response = Usuario.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = Usuario.class)
                          , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getUsuario(@ApiParam(name = "usuarioId", value = "Login", required = true) @PathParam("usuarioId") String usuarioId, 
                               @ApiParam(name = "token", value = "Token", required = true) @PathParam("token") String token) 
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ParseException, Exception {
        
        String descricaoDeAcesso = controleDeAcesso.obterNomeDaClasse();
        ResponseBuilder controle = controleDeAcesso.controlarAcesso(token, descricaoDeAcesso);
        if(controle != null)
            return controle.build();
        
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario = (Usuario) crudService.obter(usuario);
        return Response.ok(usuario).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Novo Cadastro", notes = "Cria e registra novo cadastro", response = Cadastro.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Cadastro feito com sucesso!", response = Cadastro.class)
                         , @ApiResponse(code = 406, message = "Definição de cadstro mal formada") 
                         , @ApiResponse(code = 409, message = "já existe a cadastro informada")
                         , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response createCadastro(@ApiParam(name = "cadastro", required = true) Cadastro cadastro) throws Exception {
        try {
            Membro membro = CadastroMapper.retornarMembro(cadastro);
            Usuario usuario = (Usuario) crudService.obter(membro.getUsuarioCollection().iterator().next());
            
            Situacao situacao = new Situacao();
            situacao.setId(1); //Ativo
            
            if(usuario != null)
                 return Response.status(Status.CONFLICT).entity("Usuario "+cadastro.getLogin()+" já existe!").type(MediaType.TEXT_PLAIN_TYPE).build();
            
            if(cadastro.getCelulaId() > 0){
                Celula celula = new Celula();
                celula.setId(cadastro.getCelulaId());
                celula = (Celula) crudService.obter(celula);
                
                if(celula != null){
                    CelulaMembro celulaMembro = new CelulaMembro();
                    celulaMembro.setCelulaId(celula);
                    celulaMembro.setSituacaoId(situacao);
                    Collection celulaMembros = new HashSet();
                    celulaMembros.add(celulaMembro);
                    membro.setCelulaMembroCollection(celulaMembros);                    
                }          
            }              
            membro = (Membro) crudService.salvar(membro);
            
            return Response.status(Status.CREATED).entity(membro)
                    .location(getLocation(membro.getUsuarioCollection().iterator().next()))
                    .links(getUserLinks(membro.getUsuarioCollection().iterator().next())).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }
    
        private Link[] getUserLinks(Usuario usuario) throws NoSuchMethodException {
        final Link[] links = new Link[2];

        links[0] = Link.fromUri("usuarios/{usuarioId}").rel("update").type(MediaType.APPLICATION_XML).build(usuario.getId());
        links[1] = Link.fromUri("usuarios").rel("listAll").type(MediaType.APPLICATION_XML).build();

        return links;
    }

    private URI getLocation(final Usuario usuario) throws NoSuchMethodException {
        return UriBuilder.fromUri("usuarios/{usuarioId}").build(usuario.getId());
    }
}

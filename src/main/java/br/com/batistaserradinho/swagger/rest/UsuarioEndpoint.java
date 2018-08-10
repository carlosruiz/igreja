package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.EnvelopeJson.CadastroEnvelopeJson.Cadastro;
import br.com.batistaserradinho.swagger.model.Membro;
import br.com.batistaserradinho.swagger.model.Usuario;
import br.com.batistaserradinho.swagger.service.CrudService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author cruiz
 */
@Api(value = "Usuario", description = "Endpoint para gerenciamento de cadstros de usuarios")
@Path("/usuarios")
public class UsuarioEndpoint {
    
    private final CrudService crudService = new CrudService();
     /**
     * Retorna lista completa
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna todos os usuarios", notes = "Retorna a lista completa de usuarios ", response = Usuario.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = Usuario.class)
        , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getUsuarios() throws IOException {
        List<Usuario> usuarios = crudService.obterTodos(Usuario.class.getName());
        return Response.ok(usuarios).build();
    }
    
     /**
     * Retorna por id informado
     * @param usuarioId
     */
    @GET
    @Path("/{usuarioId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna o usuario pelo login", notes = "Retorna o usuario cadastrado pelo login", response = Usuario.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = Usuario.class)
                          , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getUsuario(@ApiParam(name = "usuarioId", value = "Login", required = true) 
                                 @PathParam("usuarioId") String usuarioId) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
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
            Membro membro = (Membro) crudService.salvar(CadastroMapper.retornarMembro(cadastro));
            
            return Response.status(Status.CREATED).entity(membro).location(getLocation(membro)).links(getUserLinks(membro)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }
    
        private Link[] getUserLinks(Membro membro) throws NoSuchMethodException {
        final Link[] links = new Link[2];

        links[0] = Link.fromUri("usuarios/{usuarioId}").rel("update").type(MediaType.APPLICATION_XML).build(membro.getId());
        links[1] = Link.fromUri("usuarios").rel("listAll").type(MediaType.APPLICATION_XML).build();

        return links;
    }

    private URI getLocation(final Membro membro) throws NoSuchMethodException {
        return UriBuilder.fromUri("usuarios/{usuarioId}").build(membro.getId());
    }
}

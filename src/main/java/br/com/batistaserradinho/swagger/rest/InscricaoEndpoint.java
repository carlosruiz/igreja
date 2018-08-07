package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.EnvelopeJson.InscricaoEnvelopeJson;
import br.com.batistaserradinho.swagger.model.Inscricao;
import br.com.batistaserradinho.swagger.service.EntityNotFoundException;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

@Api(value = "Inscricao", description = "Endpoint para gerenciamento de inscrição")
@Path("/inscricoes")
public class InscricaoEndpoint {

    private final CrudService crudService = new CrudService();

    /**
     * Retorna lista completa
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna todas as inscrições", notes = "Retorna a lista completa de inscrições inseridas na aplicação", response = InscricaoEnvelopeJson.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = InscricaoEnvelopeJson.class)
        , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getInscricoes() throws IOException {
        List<Inscricao> inscricoes = crudService.obterTodos(Inscricao.class.getName());
        return Response.ok(inscricoes).build();
    }

    /**
     * Retorna por id informado
     * @param inscricaoId
     */
    @GET
    @Path("/{inscricaoId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna a inscrição pelo Codigo", notes = "Retorna a inscrição inseridas na aplicação pelo codigo", response = InscricaoEnvelopeJson.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = InscricaoEnvelopeJson.class)
                          , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getInscricao(@ApiParam(name = "inscricaoId", value = "Codigo da Inscrição", required = true) 
                                 @PathParam("inscricaoId") int inscricaoId) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        Inscricao inscricao = new Inscricao();
        inscricao.setId(inscricaoId);
        inscricao = (Inscricao) crudService.obter(inscricao);
        return Response.ok(inscricao).build();
    }

    /**
     * Cria novo registro
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Cria nova inscrição", notes = "Cria e registra nova inscrição para o evento.", response = Inscricao.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Inscrição Feita com sucesso!", response = Inscricao.class)
                         , @ApiResponse(code = 406, message = "Definição de inscrição mal formada") 
                         , @ApiResponse(code = 409, message = "já existe a inscrição informada")
                         , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response createInscricao(@ApiParam(name = "inscricao", required = true) Inscricao inscricao) throws Exception {
        try {
            Object novaInscricao = crudService.salvar(inscricao);
            return Response.status(Status.CREATED).entity(novaInscricao).location(getLocation(novaInscricao)).links(getUserLinks(novaInscricao)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }

    /**
     * Atualiza Registro
     */
    @PUT
    @Path("/{inscricaoId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Atualiza a inscrição", notes = "Registra a atualizção da inscrição no banco de dados")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Inscrição Atualizada com sucesso!")
                          , @ApiResponse(code = 404, message = "Inscrição Nao Existe")
                          , @ApiResponse(code = 500, message = "Erro interno no Serividor")})
    public Response updateInscricao(@ApiParam(name = "inscricao", required = true) Inscricao inscricao) throws Exception {
        try {
            Object inscricaoAtualizada = crudService.editar(inscricao);
            return Response.status(Status.OK).entity(inscricaoAtualizada).location(getLocation(inscricaoAtualizada)).links(getUserLinks(inscricaoAtualizada)).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity("inscrição não existe").build();
        } catch (IOException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno no serividor").build();
        }
    }

    /**
     * Exclui o registro
     */
    @DELETE
    @Path("/{inscricaoId}")
    @Produces(MediaType.APPLICATION_XML)
    @ApiOperation(value = "Exclui a inscrição", notes = "Exclui definitivamene a inscrição.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Inscrição Excluida com sucesso")
                          , @ApiResponse(code = 404, message = "Inscrição não existe")
                          , @ApiResponse(code = 500, message = "Erro interno no Servicor")})
    public Response deleteInscricao(@ApiParam("codigo da inscrição") @PathParam("inscricaoId") int inscricaoId) throws Exception {
        Inscricao inscricao = new Inscricao();
        inscricao.setId(inscricaoId);
        crudService.removerPorId(inscricao);
        return Response.status(Status.OK).entity("Excluido com sucesso!").build();
    }

    private Link[] getUserLinks(final Object inscricao) throws NoSuchMethodException {
        final Link[] links = new Link[2];

        links[0] = Link.fromUri("inscricoes/{inscricaoId}").rel("update").type(MediaType.APPLICATION_XML).build(inscricao.getClass().getMethod("getId"));
        links[1] = Link.fromUri("inscricoes").rel("listAll").type(MediaType.APPLICATION_XML).build();

        return links;
    }

    private URI getLocation(final Object inscricao) throws NoSuchMethodException {
        return UriBuilder.fromUri("inscricoes/{inscricaoId}").build(inscricao.getClass().getMethod("getId"));
    }
}

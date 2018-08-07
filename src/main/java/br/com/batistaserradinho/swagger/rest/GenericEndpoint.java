package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.EnvelopeJson.GenericEnvelopeJson;
import br.com.batistaserradinho.swagger.service.EntityNotFoundException;
import br.com.batistaserradinho.swagger.service.CrudService;
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

public class GenericEndpoint {
    Object instancia;
    String rota;
    
    public GenericEndpoint(Object instancia, String rota){
        this.instancia = instancia;
        this.rota = rota;
    }

    private final CrudService crudService = new CrudService();

    /**
     * Retorna lista completa
     * @throws java.io.IOException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna todos os registros", notes = "Retorna a lista completa de registros inseridos na aplicação", response = GenericEnvelopeJson.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = GenericEnvelopeJson.class)
        , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response gets() throws IOException {
        List<Object> generic = crudService.obterTodos(instancia.getClass().getName());
        return Response.ok(generic).build();
    }

    /**
     * Retorna por id informado
     * @param id
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna o registro pelo Codigo", notes = "Retorna o registro inserido na aplicação pelo codigo", response = GenericEnvelopeJson.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = GenericEnvelopeJson.class)
                          , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response get(@ApiParam(name = "id", value = "Codigo", required = true) 
                                 @PathParam("id") int id) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        Object registro = new Object();
        //registro.getClass().getMethod("setId",id);
        Object resultado = crudService.obter(registro);
        return Response.ok(resultado).build();
    }

    /**
     * Cria novo registro
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Cria novo registro", notes = "Cria e salva novo registro.", response = Object.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Registrado com sucesso!", response = Object.class)
                         , @ApiResponse(code = 406, message = "Definição do registro mal formado") 
                         , @ApiResponse(code = 409, message = "já esxiste o registro informada")
                         , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response create(@ApiParam(name = "registro", required = true) Object registro) throws Exception {
        try {
            Object novoRegistro = crudService.salvar(registro);
            return Response.status(Status.CREATED).entity(novoRegistro).location(getLocation(novoRegistro, rota)).links(getUserLinks(novoRegistro, rota)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }

    /**
     * Atualiza Registro
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Atualiza o registro", notes = "Registra a atualizção do registro no banco de dados")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Registro Atualizado com sucesso!")
                          , @ApiResponse(code = 404, message = "Nao Existe")
                          , @ApiResponse(code = 500, message = "Erro interno no Servidor")})
    public Response update(@ApiParam(name = "registro", required = true) Object registro) throws Exception {
        try {
            Object registroAtualizado = crudService.editar(registro);
            return Response.status(Status.OK).entity(registroAtualizado).location(getLocation(registroAtualizado, rota)).links(getUserLinks(registroAtualizado, rota)).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity("Não existe").build();
        } catch (IOException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno no serividor").build();
        }
    }

    /**
     * Exclui o registro
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    @ApiOperation(value = "Exclui o registro", notes = "Exclui definitivamene o registro.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Registro Excluido com sucesso")
                          , @ApiResponse(code = 404, message = "Registro não existe")
                          , @ApiResponse(code = 500, message = "Erro interno no Servicor")})
    public Response deleteInscricao(@ApiParam("codigo") @PathParam("id") int id) throws Exception {
        crudService.removerPorId(id);
        return Response.status(Status.OK).entity("Excluido com sucesso!").build();
    }

    private Link[] getUserLinks(final Object registro, String rota) throws NoSuchMethodException {
        final Link[] links = new Link[2];

        links[0] = Link.fromUri(rota+"/{id}").rel("update").type(MediaType.APPLICATION_XML).build(registro.getClass().getMethod("getId"));
        links[1] = Link.fromUri(rota).rel("listAll").type(MediaType.APPLICATION_XML).build();

        return links;
    }

    private URI getLocation(final Object inscricao, String rota) throws NoSuchMethodException {
        return UriBuilder.fromUri(rota+"/{id}").build(inscricao.getClass().getMethod("getId"));
    }
}

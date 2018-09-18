/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.Util.Constantes;
import br.com.batistaserradinho.business.ControleDeAcessoBusiness;
import br.com.batistaserradinho.swagger.model.Entrada;
import br.com.batistaserradinho.swagger.model.Receita;
import br.com.batistaserradinho.swagger.model.Situacao;
import br.com.batistaserradinho.swagger.service.CrudService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.text.ParseException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author cruiz
 */
@Api(value = "Entrada", description = "Endpoint para controle da Entrada de receitas da Tesouraria")
@Path("/entradas")
public class EntradaEndpoint {

    private final CrudService crudService = new CrudService();
    ControleDeAcessoBusiness controleDeAcesso = new ControleDeAcessoBusiness();

    /**
     * Retorna por id informado
     *
     * @param entradaId
     * @param token
     */
    @GET
    @Path("/{entradaId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna a entrada pelo Codigo", notes = "Retorna a entrada das receitas pelo codigo", response = Entrada.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = Entrada.class)
        , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getEntrada(@ApiParam(name = "entradaId", value = "Codigo da Entrada", required = true) @PathParam("entradaId") int entradaId,
            @ApiParam(name = "token", value = "Token", required = true) @PathParam("token") String token) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ParseException, Exception {
        String descricaoDeAcesso = controleDeAcesso.obterNomeDaClasse();
        Response.ResponseBuilder controle = controleDeAcesso.controlarAcesso(token, descricaoDeAcesso);
        if (controle != null) {
            return controle.build();
        }

        Entrada entrada = new Entrada();
        entrada.setId(entradaId);
        entrada = (Entrada) crudService.obter(entrada);
        return Response.ok(entrada).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Nova Entrada", notes = "Cria e registra nova Entrada de receitas", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Cadastro feito com sucesso!", response = String.class)
        , @ApiResponse(code = 406, message = "Definição de entrada mal formada")
        , @ApiResponse(code = 409, message = "já existe a entrada informada")
        , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response createEntrada(@ApiParam(name = "entrada", required = true) Entrada entrada,
            @ApiParam(name = "token", value = "Token", required = true) @PathParam("token") String token) throws Exception {
        try {
            String descricaoDeAcesso = controleDeAcesso.obterNomeDaClasse();
            Response.ResponseBuilder controle = controleDeAcesso.controlarAcesso(token, descricaoDeAcesso);
            if (controle != null) {
                return controle.build();
            }

            if (entrada.getSituacaoId() == null) {
                Situacao situacao = new Situacao();
                situacao.setId(1); //Ativo
                entrada.setSituacaoId(situacao);
            }

            entrada = (Entrada) crudService.salvar(entrada);

            return Response.status(Response.Status.CREATED).entity("Entrada Criada com sucesso!")
                    .location(getLocation(entrada))
                    .links(getUserLinks(entrada)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }
    
    @POST
    @Path("/receita/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Nova Receita", notes = "Cria e registra nova receita de uma Entrada", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Cadastro feito com sucesso!", response = String.class)
        , @ApiResponse(code = 406, message = "Definição de entrada mal formada")
        , @ApiResponse(code = 409, message = "já existe a entrada informada")
        , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response createReceita(@ApiParam(name = "receita", required = true) Receita receita,
            @ApiParam(name = "token", value = "Token", required = true) @PathParam("token") String token) throws Exception {
        try {
            String descricaoDeAcesso = controleDeAcesso.obterNomeDaClasse();
            Response.ResponseBuilder controle = controleDeAcesso.controlarAcesso(token, descricaoDeAcesso);
            if (controle != null) {
                return controle.build();
            }

            Entrada entrada = (Entrada) crudService.obter(receita.getEntradaId());
            
            if(!entrada.getSituacaoId().equals(Constantes.SITUACAO_ATIVA))
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Não é permitido inclusão de novas receitas.").build();
            
            receita = (Receita) crudService.salvar(receita);

            return Response.status(Response.Status.CREATED).entity("Receita registrada com sucesso!")
                  //  .location(getLocation(receita))
                  //  .links(getUserLinks(receita))
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }

    private Link[] getUserLinks(Entrada entrada) throws NoSuchMethodException {
        final Link[] links = new Link[2];

        links[0] = Link.fromUri("entradas/{entradaId}").rel("update").type(MediaType.APPLICATION_XML).build(entrada.getId());
        links[1] = Link.fromUri("entradas").rel("listAll").type(MediaType.APPLICATION_XML).build();

        return links;
    }

    private URI getLocation(final Entrada entrada) throws NoSuchMethodException {
        return UriBuilder.fromUri("entradas/{entradaId}").build(entrada.getId());
    }

}

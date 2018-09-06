/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.EnvelopeJson.CelulaMembroEnvelopeJson;
import br.com.batistaserradinho.business.ControleDeAcessoBusiness;
import br.com.batistaserradinho.swagger.model.Celula;
import br.com.batistaserradinho.swagger.model.CelulaMembro;
import br.com.batistaserradinho.swagger.service.CrudService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author cruiz
 */
@Api(value = "Celula", description = "Endpoint para consulta de celulas")
@Path("/celulas")
public class CelulaEndpoint {
    private final CrudService crudService = new CrudService();
    ControleDeAcessoBusiness controleDeAcesso = new ControleDeAcessoBusiness();
    
     /**
     * Retorna lista completa
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna todas as celulas", notes = "Retorna a lista completa de celulas cadastradas", response = Celula.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = Celula.class)
        , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getCelulas() throws IOException {
        List<Celula> celulas = crudService.obterTodos(Celula.class.getName());
        return Response.ok(celulas).build();
    }
    
    /**
     * Retorna por id informado
     * @param celulaId
    */
    @GET
    @Path("/{celulaId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna a Celula pelo Codigo", notes = "Retorna a Celula inserida na aplicação pelo codigo", response = Celula.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = Celula.class)
                          , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getCelula(@ApiParam(name = "celulaId", value = "Numero da Celula", required = true) 
                                 @PathParam("celulaId") int celulaId) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        Celula celula = new Celula();
        celula.setId(celulaId);
        celula = (Celula) crudService.obter(celula);
        return Response.ok(celula).build();
    }
    
     /**
     * Retorna por id informado
     * @param celulaId
     * @param token
     * @return 
    */
    @GET
    @Path("/{celulaId}/membros")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna as pessoas da Celula pesquisada", notes = "Retorna todas as pessoas (membro, freuqnetador assiduo e visitantes) da Celula", response = CelulaMembroEnvelopeJson.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = CelulaMembroEnvelopeJson.class)
                          , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getMembrosCelula(@ApiParam(name = "celulaId", value = "Numero da Celula", required = true) @PathParam("celulaId") int celulaId, 
                                     @ApiParam(name = "token", value = "Token", required = true) @PathParam("token") String token) 
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ParseException, Exception {            
    
        String descricaoDeAcesso = controleDeAcesso.obterNomeDaClasse();
        Response.ResponseBuilder controle = controleDeAcesso.controlarAcesso(token, descricaoDeAcesso);
        if(controle != null)
            return controle.build();
                
        Celula celula = new Celula();
        celula.setId(celulaId);
        celula = (Celula) crudService.obter(celula);
        
        List<CelulaMembro> celulaMembros = new ArrayList();
        for(CelulaMembro celulamembro : celula.getCelulaMembroCollection()){
            CelulaMembroEnvelopeJson celulaMembroEnvelopeJson = new CelulaMembroEnvelopeJson();
            celulaMembroEnvelopeJson.setCelulaId(celula.getId());
            celulaMembroEnvelopeJson.setCelulaMembroId(celulamembro.getId());
            celulaMembroEnvelopeJson.setNome(celulamembro.getMembroId().getNome());
            if(celulamembro.getEhlider()!= null)
                celulaMembroEnvelopeJson.setEhLider(celulamembro.getEhlider());
            if(celulamembro.getEhLiderEmTreinamento()!= null)
                celulaMembroEnvelopeJson.setEhLiderEmTreinamento(celulamembro.getEhLiderEmTreinamento());
            celulaMembros.add(celulamembro);
        }     
        
        return Response.ok(celulaMembros).build();
    }
}

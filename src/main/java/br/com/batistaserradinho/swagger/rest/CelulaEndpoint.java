/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batistaserradinho.swagger.rest;

import br.com.batistaserradinho.EnvelopeJson.CelulaMembroEnvelopeJson;
import br.com.batistaserradinho.EnvelopeJson.ResumoRelatorioCelulaEnvelopeJson;
import br.com.batistaserradinho.EnvelopeJson.ResumoRelatorioCelulaEnvelopeJson.ResumoRelatorio;
import br.com.batistaserradinho.business.ControleDeAcessoBusiness;
import br.com.batistaserradinho.swagger.model.Celula;
import br.com.batistaserradinho.swagger.model.CelulaMembro;
import br.com.batistaserradinho.swagger.model.CelulaRelatorio;
import br.com.batistaserradinho.swagger.model.Membro;
import br.com.batistaserradinho.swagger.model.Situacao;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.core.UriBuilder;

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
    @Path("/{celulaId}/membros/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna as pessoas da Celula pesquisada", notes = "Retorna todas as pessoas (membro, frequentador assiduo e visitantes) da Celula", response = CelulaMembroEnvelopeJson.class)
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
    
    @GET
    @Path("/{celulaId}/Relatorio/{dataDeReuniao}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna o relatorio da celula por data", notes = "Retorna o relatorio da celula por data", response = CelulaRelatorio.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Consulta Realizada com sucesso", response = CelulaRelatorio.class)
                          , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response getCelulaRelatorio(@ApiParam(name = "celulaId", value = "Numero da Celula", required = true) @PathParam("celulaId") int celulaId, 
                                     @ApiParam(name = "dataDeReuniao", value = "Data da Reunião", required = true) @PathParam("dataDeReuniao") Date dataDeReuniao, 
                                     @ApiParam(name = "token", value = "Token", required = true) @PathParam("token") String token) 
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ParseException, Exception {            
    
        String descricaoDeAcesso = controleDeAcesso.obterNomeDaClasse();
        Response.ResponseBuilder controle = controleDeAcesso.controlarAcesso(token, descricaoDeAcesso);
        if(controle != null)
            return controle.build();
                
        Celula celula = new Celula();
        celula.setId(celulaId);
        celula = (Celula) crudService.obter(celula);
                
        for(CelulaRelatorio celulaRelatorio : celula.getCelulaRelatorioCollection()){
            if( celulaRelatorio.getDatadereuniao().equals(dataDeReuniao))
                return Response.ok(celulaRelatorio).build();
        }     
        return Response.noContent().build();
    }
        
    @POST
    @Path("/{celulaId}/Relatorio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Novo Relatorio", notes = "Cria e registra novo relatorio semanal da celula", response = CelulaRelatorio.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Relatorio inserido com sucesso!", response = ResumoRelatorio.class)
                         , @ApiResponse(code = 406, message = "Definição de cadstro mal formada") 
                         , @ApiResponse(code = 409, message = "já existe a cadastro informada")
                         , @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public Response createCelulaRelatorio(@ApiParam(name = "relatorio", required = true) CelulaRelatorio relatorio,
                                    @ApiParam(name = "token", value = "Token", required = true) @PathParam("token") String token) throws Exception {
        try {
                String descricaoDeAcesso = controleDeAcesso.obterNomeDaClasse();
                Response.ResponseBuilder controle = controleDeAcesso.controlarAcesso(token, descricaoDeAcesso);
                if(controle != null)
                    return controle.build();
            
            CelulaRelatorio celulaRelatorio = (CelulaRelatorio)crudService.salvar(relatorio);
            ResumoRelatorio resumo = new ResumoRelatorio();
            resumo.setId(celulaRelatorio.getId());
            resumo.setCelulaId(celulaRelatorio.getCelulaId().getId());
            resumo.setData(celulaRelatorio.getDatadereuniao());
            resumo.setOferta(celulaRelatorio.getValordaoferta());
            resumo.setTotalDeVisitantes(celulaRelatorio.getTotaldevisitantes());
            resumo.setTotalDeFrequentadoresAssiduos(celulaRelatorio.getTotaldefrequentadoresassiduos());
            resumo.setTotalDeMembros(celulaRelatorio.getTotaldemembrospresentes());
            resumo.setTotalDePresentes(celulaRelatorio.getTotadepresentes());
         
            return Response.status(Response.Status.CREATED).entity(resumo)
                    .location(getLocation(resumo))
                    .links(getUserLinks(resumo)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }
         private Link[] getUserLinks(ResumoRelatorio resumo) throws NoSuchMethodException {
        final Link[] links = new Link[2];

        links[0] = Link.fromUri("{celulaId}/relatorio/{relatorioId}").rel("update").type(MediaType.APPLICATION_XML).build(resumo.getId());
        links[1] = Link.fromUri("relatorio").rel("listAll").type(MediaType.APPLICATION_XML).build();

        return links;
    }

    private URI getLocation(final ResumoRelatorio resumo) throws NoSuchMethodException {
        return UriBuilder.fromUri("{celulaId}/relatorio/{relatorioId}").build(resumo.getId());
    }   
}

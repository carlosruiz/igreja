package br.com.batistaserradinho.business;

import br.com.batistaserradinho.Consultas.AcessoConsultas;
import br.com.batistaserradinho.swagger.model.Acesso;
import br.com.batistaserradinho.swagger.model.MembroCargo;
import br.com.batistaserradinho.swagger.model.Usuario;
import br.com.batistaserradinho.swagger.service.CrudService;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.codec.DecoderException;

/**
 *
 * @author cruiz
 */
public class ControleDeAcessoBusiness {
    
    private final CrudService crudService = new CrudService();
    
    private boolean verificarSeUsuarioPossuiAcesso(String login, String descricaoDoAcesso) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{  
        Usuario usuario = new Usuario();
        usuario.setId(login);
        usuario = (Usuario)crudService.obter(usuario);
        
        Collection membroCargos = usuario.getMembroId().getMembroCargoCollection();
        
        if(membroCargos.size() > 0){
            Acesso acesso = new Acesso();
            acesso.setDescricao(descricaoDoAcesso);
            
            Iterator mc = membroCargos.iterator();
            while (mc.hasNext())
            {
                MembroCargo membroCargo = (MembroCargo) mc.next();
                acesso.setCargoId(membroCargo.getCargoId());
                return (new AcessoConsultas().existeAcesso(acesso));
            }
        }
        return false;
    }
    
    private boolean tokenPossuiAcesso(String token, String descricaoDeAcesso) throws DecoderException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String login = new TokenBusiness().obterLoginDeUmToken(token);
        return verificarSeUsuarioPossuiAcesso(login, descricaoDeAcesso);
    }
        
    public String obterNomeDaClasse(){
        Throwable thr = new Throwable();
        thr.fillInStackTrace ();
        StackTraceElement[] ste = thr.getStackTrace();
        return ste[1].getMethodName();
    }
    
    public ResponseBuilder controlarAcesso(String token, String descricaoDeAcesso) throws UnsupportedEncodingException, ParseException, Exception{
        Status statusDoToken = new TokenBusiness().validarToken(token);       
        String mensagem = statusDoToken.equals(Status.UNAUTHORIZED) ? "Usuario ou senha inválido" : statusDoToken.equals(Status.BAD_REQUEST) 
                                                                    ? "Token Expirado!" : statusDoToken.equals(Status.NOT_ACCEPTABLE) ? "Token inválido ou ocorreu um erro!" : null;
        if(mensagem != null)
             return Response.status(statusDoToken).entity(mensagem).type(MediaType.TEXT_PLAIN_TYPE);
        
        if(!tokenPossuiAcesso(token, descricaoDeAcesso))
            return Response.status(Status.UNAUTHORIZED).entity("Desculpe, você não tem permissão. Solicite novo Token!").type(MediaType.TEXT_PLAIN_TYPE);
        
        return null;
    }
}

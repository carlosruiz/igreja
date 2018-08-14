package br.com.batistaserradinho.business;

import br.com.batistaserradinho.Consultas.AcessoConsultas;
import br.com.batistaserradinho.Consultas.MembroConsultas;
import br.com.batistaserradinho.swagger.model.Acesso;
import br.com.batistaserradinho.swagger.model.MembroCargo;
import br.com.batistaserradinho.swagger.model.Usuario;
import br.com.batistaserradinho.swagger.service.CrudService;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
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
        
        if(membroCargos != null){
            Acesso acesso = new Acesso();
            acesso.setDescricao(descricaoDoAcesso);
            
            Iterator mc = membroCargos.iterator();
            while (mc.hasNext())
            {
                MembroCargo membroCargo = (MembroCargo) mc.next();
                acesso.setCargoId(membroCargo.getCargoId());
                if(new AcessoConsultas().existeAcesso(acesso)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean verificarSeTokenPossuiAcesso(String token, String descricaoDeAcesso) throws DecoderException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String login = new TokenBusiness().obterLoginDeUmToken(token);
        return verificarSeUsuarioPossuiAcesso(login, descricaoDeAcesso);
    }
        
    public String obterNomeDaClasse(){
        Throwable thr = new Throwable();
        thr.fillInStackTrace ();
        StackTraceElement[] ste = thr.getStackTrace();
        return ste[2].getMethodName();
    }
}

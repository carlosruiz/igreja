package br.com.batistaserradinho.business;

import br.com.batistaserradinho.Consultas.MembroConsultas;
import br.com.batistaserradinho.swagger.model.Membro;
import br.com.batistaserradinho.swagger.model.Usuario;
import br.com.batistaserradinho.swagger.service.CrudService;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author cruiz
 */
public class ControleDeAcessoBusiness {
    
    private final CrudService crudService = new CrudService();
    /*
    public boolean verificarSeUsuarioPossuiAcesso(String login, String acesso) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{  
        Usuario usuario = new Usuario();
        usuario.setId(login);
        usuario = (Usuario)crudService.obter(usuario);
        Membro membro = new MembroConsultas().obterMembroPorUsuario(usuario);
        Object membroCargo = membro.getMembroCargoCollection();
    }
    */
}

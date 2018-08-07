package br.com.batistaserradinho.business;

import br.com.batistaserradinho.Util.Constantes;
import br.com.batistaserradinho.Util.Criptografia;
import br.com.batistaserradinho.swagger.model.Usuario;
import br.com.batistaserradinho.swagger.service.CrudService;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.codec.DecoderException;

/**
 *
 * @author cruiz
 */
public class TokenBusiness {
    
    public String construirToken(Usuario usuario) throws NoSuchAlgorithmException{
        Calendar date = Calendar.getInstance();
        long t= date.getTimeInMillis();
        Date dataDeExpiracao = new Date(t + (Constantes.PRAZO_DE_EXPIRACAO_DO_TOKEN_EM_MILISEGUNDOS));
        DateFormat dfData = new SimpleDateFormat("yyyyddMM");
        DateFormat dfHora = new SimpleDateFormat("ssmmHH");
        String prefixo = dfHora.format(dataDeExpiracao);
        String sufixo = dfData.format(dataDeExpiracao);

        String chave = prefixo + usuario.getId() +"&"+ usuario.getSenha() + sufixo;         

        return  Criptografia.stringToHex(chave);
    }
    
    public boolean validarToken(String token) throws DecoderException, UnsupportedEncodingException, ParseException, Exception{
        
        String tokenEmString = Criptografia.hexToString(token);
        
        String prefixo = tokenEmString.substring(0, 6);
        String sufixo = tokenEmString.substring(tokenEmString.length() - 8, tokenEmString.length());
        String login = obterLoginDeUmToken(token);
        String senha = tokenEmString.substring(tokenEmString.indexOf("&") + 1, tokenEmString.length() - 8);
        
        DateFormat dfData = new SimpleDateFormat("yyyyddMMssmmHH");
        Date dataDeExpiracao = dfData.parse(sufixo+ prefixo);
        Date dataAtual = new Date();
        
        if(dataAtual.getTime() > dataDeExpiracao.getTime())
            throw new Exception("Token expirado!");
        
        Usuario usuario = new Usuario();
        usuario.setId(login);
        Usuario usr = (Usuario) new CrudService().obter(usuario);
        
        if(usr == null || !usr.getSenha().equals(senha))
           throw new Exception("Usuario ou senha incorretos"); 
               
        return true;
    }
    
    public String obterLoginDeUmToken(String token) throws DecoderException, UnsupportedEncodingException{
       String tokenEmString = Criptografia.hexToString(token);     
       return tokenEmString.substring(6, tokenEmString.indexOf("&"));
    }    
}

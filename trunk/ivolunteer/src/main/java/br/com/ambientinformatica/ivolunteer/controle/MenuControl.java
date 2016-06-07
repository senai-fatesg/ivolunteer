package br.com.ambientinformatica.ivolunteer.controle;
import javax.faces.event.ActionEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.ambientinformatica.ambientjsf.controle.GerenciadorSessao;

/**
 * @hidden
 */
@Controller("MenuControl")
@Scope("session")
public class MenuControl {

    public void gerenciarSessao(ActionEvent evento){
        GerenciadorSessao.limparSessoes();
    }

}

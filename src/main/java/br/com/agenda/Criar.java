package br.com.agenda;

import br.com.agenda.contato.Contato;
import br.com.agenda.contato.ContatoDAO;
import br.com.agenda.contato.EstadoCivil;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import java.sql.Connection;
import java.util.Arrays;

public class Criar extends BasePage {

    public Criar() {
        add(new Label("titulo", "Criação de Contato"));

        Contato contato = new Contato();
        CompoundPropertyModel<Contato> compoundPropertyModelContrato = new CompoundPropertyModel<Contato>(contato);
        Form<Contato> form = new Form<Contato>("formularioContato", compoundPropertyModelContrato) {
            @Override
            public void onSubmit() {
                Contato contatoSubmetido = getModelObject();
                salvar(contatoSubmetido);
                Criar.this.setResponsePage(Inicio.class);
            }
        };
        add(form);

        TextField<String> inputNome = new TextField<String>("nome");
        TextField<String> inputEmail = new TextField<String>("email");
        TextField<String> inputTelefone = new TextField<String>("telefone");
        DropDownChoice<EstadoCivil> comboEstadoCivil = new DropDownChoice<EstadoCivil>("estadoCivil",
                Arrays.asList(EstadoCivil.values()), new IChoiceRenderer<EstadoCivil>() {
            @Override
            public Object getDisplayValue(EstadoCivil arg0) {
                return arg0.getLabel();
            }

            @Override
            public String getIdValue(EstadoCivil arg0, int arg1) {
                return arg0.name();
            }
        });
        form.add(inputNome, inputEmail, inputTelefone, comboEstadoCivil);

        inputNome.setLabel(Model.of("Nome de Contato")).setRequired(true).add(StringValidator.maximumLength(10));
        inputEmail.setLabel(Model.of("E-mail do contato")).add(EmailAddressValidator.getInstance());

        add(new FeedbackPanel("feedBackMessage", new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));

    }


    private void salvar(Contato contatoSubmetido) {
        Connection conexao = ((WicketApplication) getApplication()).getConexao();
        ContatoDAO dao = new ContatoDAO(conexao);
        dao.inserir(contatoSubmetido);
    }

}

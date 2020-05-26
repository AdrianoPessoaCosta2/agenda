package br.com.agenda;

import br.com.agenda.contato.Contato;
import br.com.agenda.contato.ContatoDAO;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import java.sql.Connection;

public class Pesquisar extends BasePage {

    public Pesquisar() {
        Form<String> formularioPesquisar = new Form<String>("formularioPesquisa");
        add(formularioPesquisar);

        final TextField<String> pesquisaNome = new TextField<String>("pesquisaNome", new Model<String>());
        formularioPesquisar.add(pesquisaNome);

        final WebMarkupContainer containerResultados = new WebMarkupContainer("divResultados");
        containerResultados.setVisible(false);
        containerResultados.setOutputMarkupPlaceholderTag(true);
        add(containerResultados);

        PropertyListView<Contato> listaResultados = new PropertyListView<Contato>("contatos", new ListModel<Contato>()) {
            @Override
            protected void populateItem(ListItem<Contato> listItem) {
                listItem.add(new Label("nome"));
                listItem.add(new Label("email"));
                listItem.add(new Label("telefone"));
                listItem.add(new Label("estadoCivil"));
            }
        };
        containerResultados.add(listaResultados);
        AjaxButton botaoPesquisar = new AjaxButton("botaoPesquisar", formularioPesquisar){
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form){
                containerResultados.setVisible(true);
                target.add(containerResultados);
            }
        };
        formularioPesquisar.add(botaoPesquisar);
    }
}

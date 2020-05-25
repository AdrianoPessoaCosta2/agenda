package br.com.agenda;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see //br.com.agenda.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

    private Connection conexao;

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return Inicio.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        conexao = criaConexao();

    }
    private Connection criaConexao() {
        String url = "jdbc:mysql://localhost:3306/contato?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        //estabelecendo a conexao com o banco
        try{
            Connection conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        }catch(SQLException e){
            return null;
        }
    }
    public Connection getConexao(){
        return conexao;
    }
}

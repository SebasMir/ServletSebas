
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuario
 */
public class SQLConnect {

    public String bd = "curso";
    public String login = "root";
    public String password = "";
    public String url = "jdbc:mysql://localhost/" + bd;
    Connection link = null;

    public SQLConnect() {
    }

    public Connection conectar() {

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.login, this.password);
            if (link != null) {
                System.out.println("Conectado a " + link.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return link;
    }

    public void closeConnection() {
        try {
            link.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fallo al cerrar la conexión");
        }
    }

    public ArrayList<Alumnos> getListaAlumn(Connection conn) throws SQLException {

        ArrayList<Alumnos> alumnos = new ArrayList<Alumnos>();
        Statement st;
        ResultSet rs;

        st = conn.createStatement();
        rs = st.executeQuery("select * from alumne;");

        while (rs.next()) {
            Alumnos a = new Alumnos(rs.getInt("codi"), rs.getString("nom"));
            alumnos.add(a);

        }

        return alumnos;
    }

    public Alumnos getAssigTutorias(Connection conn, int alumnCode) throws SQLException {

        Alumnos al = new Alumnos();
        Statement st;
        ResultSet rs;
        String nom = "", query;

        st = conn.createStatement();
        
        query = "select * from alumne where codi=" + alumnCode + ";";
        rs = st.executeQuery(query);

        while (rs.next()) {
            nom = rs.getString("nom");
        }
        al = new Alumnos(alumnCode, nom);
        query = "select nom from tutoria left join tutoriaalumne on " + "tutoria.codi=tutoriaalumne.codiTutoria where " + "tutoriaalumne.codiAlumne=" + alumnCode + ";";
        rs = st.executeQuery(query);

        while (rs.next()) {
            al.addTutoria(rs.getString("nom"));
        }

        query = "select assignatura.codi, assignatura.nom from assignatura " + "inner join tutoria on assignatura.codi=tutoria.codiAssignatura " + "inner join tutoriaalumne on tutoriaalumne.codiTutoria=tutoria.codi " + "where tutoriaalumne.codiAlumne=" + alumnCode + ";";
        rs = st.executeQuery(query);
        
        while (rs.next()) {
            al.addAssignatura(rs.getString("nom"));
        }

        return al;
    }
}

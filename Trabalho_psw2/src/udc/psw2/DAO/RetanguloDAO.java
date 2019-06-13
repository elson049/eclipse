package udc.psw2.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Retangulo;
import udc.psw2.aplicacao.DBConnection;
import udc.psw2.lista.ListaEncadeada;

public class RetanguloDAO {
	private int desenho;
	private Statement statement;
	private ResultSet resultSet;
	private int numberOfRows;
	private String query = "SELECT ID_Retangulo, X_a, Y_a, X_b ,Y_b FROM Retangulo WHERE ID_desenho = ";
	private String update = "UPDATE (X_a, Y_a, X_b, Y_b) FROM Retangulo with ";
	private String insert = "INSERT into Retangulo (X_a, Y_a, X_b, Y_b, ID_desenho) values ";

	public RetanguloDAO(int desenho) {
		this.desenho = desenho;
		query = query + "'" + desenho + "';";
		try {
			// cria Statement para consultar banco de dados
			statement = DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// configura consulta e a executa
			setQuery();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void moveToRow(int row) {
		try {
			resultSet.absolute(row);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Retangulo getRetangulo() {
		Retangulo retangulo;
		try {
			retangulo = new Retangulo(resultSet.getFloat(2), resultSet.getFloat(3), resultSet.getFloat(4), resultSet.getFloat(5));
			return retangulo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void save(Retangulo retangulo) throws SQLException, IllegalStateException {
		String newData = update + "('" + retangulo.getA().getX() + "', '" + retangulo.getA().getY() + "', '" 
				+ retangulo.getB().getX() + "', '" + retangulo.getB().getY() + "')"
				+ " WHERE id_retangulo = " + "', '" + resultSet.getInt(1) + "');";
		statement.executeUpdate(newData);
	}

	public void insert(Retangulo retangulo) throws SQLException, IllegalStateException {
		String newData = insert + "('" + retangulo.getA().getX() + "', '" + retangulo.getA().getY() + "', '"
				+ retangulo.getB().getX() + "', '" + retangulo.getB().getY() + "', '" + desenho + "');";
		statement.executeUpdate(newData);

		setQuery();
	}

	// configura nova string de consulta de banco de dados
	public void setQuery() throws SQLException, IllegalStateException {
		// especifica consulta e a executa
		resultSet = statement.executeQuery(query); 

		// determina o n�mero de linhas em ResultSet
		resultSet.last(); // move para a �ltima linha
		numberOfRows = resultSet.getRow(); // obt�m n�mero de linha
		resultSet.first();
	}

	public ListaEncadeada<FiguraGeometrica> getLista() {
		ListaEncadeada<FiguraGeometrica> lista = new ListaEncadeada<FiguraGeometrica>();
		
		try {
			if(!resultSet.first())
				return lista;
			while(!resultSet.isAfterLast()) {
				Retangulo ret = getRetangulo();
				if(ret != null)
					lista.inserirFim(ret);
				resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	// fecha Statement e Connection
	public void disconnectFromDatabase() {
		try {
			statement.close();
			DBConnection.getConnection().close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}

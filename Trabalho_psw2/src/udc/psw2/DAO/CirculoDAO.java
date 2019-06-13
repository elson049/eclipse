package udc.psw2.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import udc.psw2.FigurasGeometricas.Circulo;
import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.aplicacao.DBConnection;
import udc.psw2.lista.ListaEncadeada;

public class CirculoDAO {
	private int desenho;
	private Statement statement;
	private ResultSet resultSet;
	private int numberOfRows;
	private String query = "SELECT ID_Circulo, X_a, Y_a, X_b, Y_b FROM Circulo WHERE ID_desenho = ";
	private String update = "UPDATE (X_a, Y_a, X_b, Y_b) FROM Circulo with ";
	private String insert = "INSERT into Circulo (X_a, Y_a, X_b, Y_b, ID_desenho) values ";

	public CirculoDAO(int desenho) {
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

	public Circulo getCirculo() {
		Circulo circulo;
		try {
			circulo = new Circulo(resultSet.getFloat(2), resultSet.getFloat(3), resultSet.getFloat(4),resultSet.getFloat(5));
			return circulo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void save(Circulo circulo) throws SQLException, IllegalStateException {
		String newData = update + "('" + circulo.getA().getX() + "', '" + circulo.getA().getY() + "', '" 
				+ circulo.getB().getX() + "', '" + circulo.getB().getY() + "')"
				+ " WHERE id_circulo = " + "', '" + resultSet.getInt(1) + "');";
		statement.executeUpdate(newData);
	}

	public void insert(Circulo circulo) throws SQLException, IllegalStateException {
		String newData = insert + "('" + circulo.getA().getX() + "', '" + circulo.getA().getY() + "', '" 
				+ circulo.getB().getX() + "', '" + circulo.getB().getY() + "', '" + desenho + "');";
		statement.executeUpdate(newData);

		setQuery();
	}

	// configura nova string de consulta de banco de dados
	public void setQuery() throws SQLException, IllegalStateException {
		// especifica consulta e a executa
		resultSet = statement.executeQuery(query); 

		// determina o número de linhas em ResultSet
		resultSet.last(); // move para a última linha
		numberOfRows = resultSet.getRow(); // obtém número de linha
		resultSet.first();
	}
	
	public ListaEncadeada<FiguraGeometrica> getLista() {
		ListaEncadeada<FiguraGeometrica> lista = new ListaEncadeada<FiguraGeometrica>();
		
		try {
			if(!resultSet.first())
				return lista;			
			while(!resultSet.isAfterLast()) {
				Circulo circ = getCirculo();
				if(circ != null)
					lista.inserirFim(circ);
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

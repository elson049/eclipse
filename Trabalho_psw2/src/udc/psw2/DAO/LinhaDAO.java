package udc.psw2.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Linha;
import udc.psw2.aplicacao.DBConnection;
import udc.psw2.lista.ListaEncadeada;

public class LinhaDAO {
	private int desenho;
	private Statement statement;
	private ResultSet resultSet;
	private int numberOfRows;
	private String query = "SELECT ID_Linha, X_a, Y_a, X_b, Y_b FROM Linha WHERE ID_desenho = ";
	private String update = "UPDATE (X_a, Y_a, X_b, Y_b) FROM Linha ";
	private String insert = "INSERT into Linha (X_a, Y_a, X_b, Y_b, ID_desenho) values ";

	public LinhaDAO(int desenho) {
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

	public Linha getLinha() {
		Linha linha;
		try {
			linha = new Linha(resultSet.getFloat(2), resultSet.getFloat(3), resultSet.getFloat(4), resultSet.getFloat(5));
			return linha;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void save(Linha linha) throws SQLException, IllegalStateException {
		String newData = update + "('" + linha.getA().getX() + "', '" + linha.getA().getY() + "', '" 
				+ linha.getB().getX() + "', '" + linha.getB().getY() + "')"
				+ " WHERE id_linha = " + "', '" + resultSet.getInt(1) + "');";
		statement.executeUpdate(newData);
	}

	public void insert(Linha linha) throws SQLException, IllegalStateException {
		String newData = insert + "('" + linha.getA().getX() + "', '" + linha.getA().getY() + "', '"
				+ linha.getB().getX() + "', '" + linha.getB().getY() + "', '" + desenho + "');";
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
				Linha linha = getLinha();
				if(linha != null)
					lista.inserirFim(linha);
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

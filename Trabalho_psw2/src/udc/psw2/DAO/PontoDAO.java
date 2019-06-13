package udc.psw2.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Ponto;
import udc.psw2.aplicacao.DBConnection;
import udc.psw2.lista.ListaEncadeada;

public class PontoDAO {
	private int desenho;
	private Statement statement;
	private ResultSet resultSet;
	private int numberOfRows;
	private String query = "SELECT ID_Ponto, x, y FROM Ponto WHERE ID_desenho = ";
	private String update = "UPDATE (x, y) FROM Ponto with ";
	private String insert = "insert into Ponto (x,y,ID_desenho) values ";

	public PontoDAO(int desenho) {
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

	public Ponto getPonto() {
		Ponto ponto;
		try {
			ponto = new Ponto(resultSet.getFloat(2), resultSet.getFloat(3));
			return ponto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void save(Ponto ponto) throws SQLException, IllegalStateException {
		String newData = update + "('" + ponto.getX() + "', '" + ponto.getY() + "', '" + desenho + "')"
				+ " WHERE ID_Ponto = " + "', '" + resultSet.getInt(1) + "');";
		statement.executeUpdate(newData);
	}

	public void insert(Ponto ponto) throws SQLException, IllegalStateException {
		String newData = insert + "('" + ponto.getX() + "', '" + ponto.getY() + "', '" + desenho + "');";
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
				Ponto ponto = getPonto();
				ponto.setEstado(FiguraGeometrica.VERBOSE);
				if(ponto != null)
					lista.inserirFim(ponto);
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

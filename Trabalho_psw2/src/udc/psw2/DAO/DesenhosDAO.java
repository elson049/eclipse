package udc.psw2.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import udc.psw2.DAO.entidade.Desenhos;
import udc.psw2.aplicacao.DBConnection;

public class DesenhosDAO {
	private Statement statement;
	private ResultSet resultSet;
	private int numberOfRows;
	private String query = "SELECT ID_Desenho, nome FROM Desenhos ";
	private String update = "UPDATE (nome) from Desenhos with ";
	private String insert = "INSERT into Desenhos (nome) values ";

	public DesenhosDAO() {
		try {
			// cria Statement para consultar banco de dados
			statement = (Statement) DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
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

	public Desenhos getDesenho() {
		Desenhos desenhos;
		try {
			desenhos = new Desenhos(
					resultSet.getInt(1),
					resultSet.getString(2));
			return desenhos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void save(Desenhos desenho) throws SQLException, IllegalStateException {
		String newData = update + "('" + desenho.getNome() + "')" 
				+ " WHERE id_desenho = " + "', '" + desenho.getId() + "');";
		statement.executeUpdate(newData);
	}

	public void insert(Desenhos desenho) throws SQLException, IllegalStateException {
		String newData = insert + "('" + desenho.getNome() + "');";
		int affectedRows = statement.executeUpdate(newData, Statement.RETURN_GENERATED_KEYS);
		
        if (affectedRows == 0) {
            throw new SQLException("Creating Desenhos failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                desenho.setId(generatedKeys.getLong(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
		setQuery();
	}

	// configura nova string de consulta de banco de dados
	public Desenhos find(String nome) {
		String query = this.query + " WHERE nome = '" + nome + "';";

		Desenhos desenho;
		try {
			// especifica consulta e a executa
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
			desenho = new Desenhos(
					resultSet.getInt(1),
					resultSet.getString(2));
			return desenho;
			}
			else return null;
		} catch (IllegalStateException | SQLException e) {
			e.printStackTrace();
		}
		return null;
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
package data_access_object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import bean.UserBean;
import data_transfer_object.UserInformationDTO;

public class CreateUserDAO {
	private final String URL = "jdbc:mysql://localhost:3306/userinformation";
	private final String USER = "root";
	private final String PASS = "mysql";
	private Connection connection = null;

	public void connectDB() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(URL, USER, PASS);
	}

	public UserInformationDTO select() {
		Statement statement = null;
		ResultSet result_set = null;
		UserInformationDTO user_info_dto = new UserInformationDTO();
		String sql = "SELECT * FROM user";

		try {
			connectDB();
			statement = connection.createStatement();
			result_set = statement.executeQuery(sql);

			while (result_set.next()) {
				UserBean user_bean = new UserBean();
				user_bean.setNo(result_set.getInt("id"));
				user_bean.setName(result_set.getString("name"));
				user_bean.setPassword(result_set.getString("password"));

				user_info_dto.add(user_bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (result_set != null)
					result_set.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		disconnect();
		return user_info_dto;

	}

	public int createUser(String name, String password) {
		Statement statement = null;
		ResultSet serch_id = null;
		String searchNewMinId_aql = "SELECT id From user ORDER BY id";
		String createFiles_sql = "CREATE TABLE FILEOF_" + name + "("
				+ "fileId INT PRIMARY KEY AUTO_INCREMENT,"
				+ "fileName varchar(20)"
				+ ");";

		int newId = 1;

		try {
			if (!isNameAvailable(name)) {
				return 0;
			}
			connectDB();

			statement = connection.createStatement();
			serch_id = statement.executeQuery(searchNewMinId_aql);

			Set<Integer> userIds = new HashSet<>();
			//userIdsにすべてのIDを入れる
			while (serch_id.next()) {
				userIds.add(serch_id.getInt("id"));
			}
			//新しい最小のIDを探す
			while (userIds.contains(newId)) {
				newId++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//新しいIDをセットしたSQL文
		String insert_sql = "INSERT INTO user VALUES (" + newId + ", '" + name + "', '" + password + "')";

		int createFile_int = executeSql(createFiles_sql);

		return executeSql(insert_sql);
	}

	public boolean isLogin(String name, String pass) {
		Statement statement = null;
		ResultSet result_set = null;
		String isLogin_sql = "SELECT COUNT(*) FROM user WHERE name = '" + name + "' AND password = '" + pass + "'";
		Boolean isLogin = false;

		try {
			connectDB();
			statement = connection.createStatement();
			result_set = statement.executeQuery(isLogin_sql);

			int count = 0;
			result_set.next();
			count = result_set.getInt(1);

			if (count == 1) {
				isLogin = true;
			} else {
				isLogin = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (result_set != null)
					result_set.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		disconnect();
		return isLogin;
	}

	public boolean isNameAvailable(String name) {
		Statement statement = null;
		ResultSet result_set = null;
		String isNameAvailable_sql = "SELECT COUNT(*) FROM user WHERE name = '" + name + "'";
		Boolean isNameAvailable = false;
		try {
			connectDB();
			statement = connection.createStatement();
			result_set = statement.executeQuery(isNameAvailable_sql);
			result_set.next();

			if (result_set.getInt(1) == 0) {
				isNameAvailable = true;
			} else {
				isNameAvailable = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (result_set != null)
					result_set.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		disconnect();
		return isNameAvailable;
	}

	public void addFileName(String name, String fileName) {
		String addFileName_sql = "INSERT INTO FILEOF_" + name + "(fileName)VALUES('" + fileName + "')";
		String addFileData_sql = "CREATE TABLE DATAOF_" + fileName + "_" + name + "("
				+ "question varchar(100),"
				+ "answer varchar(100)"
				+ ");";

		int addFileName_int = executeSql(addFileName_sql);
		int addFileData_int = executeSql(addFileData_sql);
	}

	public void addData(String name, String fileName, String question, String answer) {
		String addData_sql = "INSERT INTO DATAOF_" + fileName + "_" + name + " (question, answer) VALUES('"
				+ question + "', '" + answer + "');";
		int addData_int = executeSql(addData_sql);
	}

	public int executeSql(String sql) {
		Statement statement = null;
		int result = 1;

		try {
			connectDB();
			statement = connection.createStatement();
			result = statement.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		disconnect();
		return result;

	}

	public void disconnect() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

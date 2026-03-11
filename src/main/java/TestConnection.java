import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

public class TestConnection {
	public static void main(String[] args) {
		Properties props = new Properties();

		try (InputStream input = TestConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
			if (input == null) {
				System.err.println("Ошибка: Файл db.properties не найден!");
				return;
			}
			props.load(input);

			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String pass = props.getProperty("password");

			try (Connection conn = DriverManager.getConnection(url, user, pass)) {
				System.out.println("Ура! Мы успешно подключились к базе данных.");

				try (Statement stmt = conn.createStatement()) {
					String createTableSQL = "CREATE TABLE IF NOT EXISTS PersonJDBC (" +
							"id INT PRIMARY KEY AUTO_INCREMENT, " +
							"age INT, " +
							"salary DECIMAL(10, 2), " +
							"passport CHAR(10), " +
							"address VARCHAR(200), " +
							"dateOfBirthday DATE, " +
							"dateTimeCreate DATETIME, " +
							"timeToLunch TIME, " +
							"letter TEXT" +
							")";

					stmt.execute(createTableSQL);
					System.out.println("Таблица PersonJDBC успешно создана (или уже существовала).");
				}

				String insertSQL = "INSERT INTO PersonJDBC (age, salary, passport, address, dateOfBirthday, dateTimeCreate, timeToLunch, letter) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

				try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

					try (Statement cleanStmt = conn.createStatement()) {
						cleanStmt.execute("TRUNCATE TABLE PersonJDBC");
						System.out.println("Таблица очищена для новых данных.");
					}

					addPerson(pstmt, 18, 1200.50, "AB12345678", "Минск, ул. Ленина 5", "2006-03-10", "2024-01-01 10:00:00", "12:00:00", "Письмо новичка");

					addPerson(pstmt, 25, 2500.00, "MP99988877", "Гродно, пр. Мира 12", "1999-05-20", "2024-02-15 09:30:00", "13:00:00", "Заявление на отпуск");

					addPerson(pstmt, 34, 4200.75, "KH55544433", "Брест, ул. Советская 1", "1990-12-01", "2024-03-01 08:00:00", "12:30:00", "Отчет за квартал");

					addPerson(pstmt, 21, 1800.00, "AC11122233", "Гомель, ул. Барыкина 10", "2003-01-15", "2024-01-10 14:20:00", "14:00:00", "Запрос на обучение");

					addPerson(pstmt, 45, 6000.00, "BM44455566", "Витебск, ул. Правды 8", "1979-08-25", "2023-12-20 11:15:00", "13:30:00", "Текст на несколько страниц...");

					System.out.println("5 уникальных Person успешно добавлены!");
				}

				System.out.println("\nЛюди старше 21 года (отсортированы по dateTimeCreate):");

				String selectSQL = """
						SELECT * 
						FROM PersonJDBC
						WHERE age > 21
						ORDER BY dateTimeCreate
						""";

				try (Statement selectStmt = conn.createStatement();
					 ResultSet rs = selectStmt.executeQuery(selectSQL)) {

					while (rs.next()) {

						int id = rs.getInt("id");
						int age = rs.getInt("age");
						double salary = rs.getDouble("salary");
						String passport = rs.getString("passport");
						String address = rs.getString("address");

						System.out.println(
								"id=" + id +
										", age=" + age +
										", salary=" + salary +
										", passport=" + passport +
										", address=" + address
						);
					}
				}

				System.out.println("\nМетаданные базы данных:");

				DatabaseMetaData metaData = conn.getMetaData();

				System.out.println("Название БД: " + metaData.getDatabaseProductName());
				System.out.println("Версия БД: " + metaData.getDatabaseProductVersion());
				System.out.println("Драйвер: " + metaData.getDriverName());

				System.out.println("\nКолонки таблицы PersonJDBC:");

				ResultSet columns = metaData.getColumns(null, null, "PersonJDBC", null);

				while (columns.next()) {

					String columnName = columns.getString("COLUMN_NAME");
					String columnType = columns.getString("TYPE_NAME");

					System.out.println(columnName + " : " + columnType);
				}

			} catch (Exception e) {
				System.err.println("Не удалось подключиться к самой БД.");
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.err.println("Ошибка при чтении файла свойств.");
			e.printStackTrace();
		}
	}

	private static void addPerson(PreparedStatement pstmt, int age, double salary, String passport, String address,
								  String birth, String create, String lunch, String letter) throws SQLException {
		pstmt.setInt(1, age);
		pstmt.setDouble(2, salary);
		pstmt.setString(3, passport);
		pstmt.setString(4, address);
		pstmt.setDate(5, java.sql.Date.valueOf(birth));
		pstmt.setTimestamp(6, java.sql.Timestamp.valueOf(create));
		pstmt.setTime(7, java.sql.Time.valueOf(lunch));
		pstmt.setString(8, letter);
		pstmt.executeUpdate();
	}
}
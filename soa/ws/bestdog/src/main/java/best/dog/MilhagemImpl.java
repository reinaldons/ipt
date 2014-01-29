package best.dog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.jws.WebService;

@WebService(endpointInterface = "best.dog.Milhagem")
public class MilhagemImpl implements Milhagem {

	private static final String DEFAULT_DB = "jdbc:h2:tcp://localhost/~/test;USER=sa";

	static {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String calculaMilhagem(String cpf) {
		int total = salvaMilhas(cpf);
		String result = cpf + " tem " + total + " milhas";
		System.out.println(result);
		return result;
	}

	private int salvaMilhas(String cpf) {
		int total = 0;
		try (Connection conn = DriverManager.getConnection(DEFAULT_DB)) {
			PreparedStatement statSelect = conn
					.prepareStatement("select total from IPT_MILHAGEM where cpf = ?");
			statSelect.setString(1, cpf);
			ResultSet rs = statSelect.executeQuery();
			while (rs.next()) {
				total += rs.getInt("total");
			}
			PreparedStatement statInsert = conn
					.prepareStatement("merge into IPT_MILHAGEM key(cpf) values(?, ?)");
			statInsert.setString(1, cpf);
			statInsert.setInt(2, total);
			statInsert.executeUpdate();
			// fecha conexoes
			statInsert.close();
			statSelect.close();
			rs.close();
		} catch (Exception e) {
			// nao calcula as milhas
		}
		return total;
	}

}

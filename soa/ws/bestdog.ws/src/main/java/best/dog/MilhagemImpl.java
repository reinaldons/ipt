package best.dog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.jws.WebService;
@WebService(endpointInterface = "best.dog.Milhagem")
public class MilhagemImpl implements Milhagem {

    public String calculaMilhagem(String cpf) {
        System.out.println(cpf);

        int total = salvaMilhas(cpf);

        return cpf + " tem " + total + " milhas";
    }

    private int salvaMilhas(String cpf) {
        int total = 1;
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/activiti;USER=sa");
            Statement stat = conn.createStatement();

            stat.execute("insert into MILHAGEM values('" + cpf + "', " + total + ")");

            stat.close();
            conn.close();
            return total;
        } catch (Exception e) {
        }
        return 10;
    }
    

}

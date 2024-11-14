import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ServicoAdicionalCRUD {

    //create
    public static ServicoAdicional servicoAdicional (ServicoAdicional servicoAdicional) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_servico_adicional ( id , descricao , custo_mensal ) VALUES ( ? , ? , ?) " , Statement.RETURN_GENERATED_KEYS) ;

            ps.setInt(1, servicoAdicional.getId());
            ps.setString(2, servicoAdicional.getDescricao());
            ps.setDouble(3, servicoAdicional.getCusto_mensal());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                servicoAdicional.setId(rs.getInt(1));

            }

        }
        catch (Exception e) {

            e.printStackTrace();

        }
        return servicoAdicional ;
    }

    //delete
    
}

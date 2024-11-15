import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicoAdicionalCRUD {

    //create
    public static ServicoAdicional AdicionarServicoAdicional (ServicoAdicional servicoAdicional) {

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
    public static void removerServicoAdicional (int id) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_servico_adicional WHERE id = ?") ;

            ps.setInt(1, id);
            ps.execute() ;

        }
        catch (Exception e ) {

            e.printStackTrace();

        }

    }

    //read
    public static ServicoAdicional buscarServicoAdicionalPorId ( int id ) {


        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_servico_adicional WHERE id = ? ") ;

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int idServico = rs.getInt("id") ;
                String descricao = rs.getString("descricao") ;
                Double custo_mensal = rs.getDouble("custo_mensal") ;

                return new ServicoAdicional (idServico , descricao , custo_mensal) ;

            }
        }
        catch (Exception e) {

            e.printStackTrace();

        }

        throw new RuntimeException("Nenhum serviço adicional cadastrado com o id " + id ) ;
    }

    //read
    public static List <ServicoAdicional> buscarTodosOsServicosAdicionais () {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_servico_adicional") ;

            ResultSet rs = ps.executeQuery();
            List<ServicoAdicional> servicoAdicionais = new ArrayList<>() ;

            while (rs.next()) {

                servicoAdicionais.add(new ServicoAdicional(
                        rs.getInt("id") ,
                        rs.getString("descricao") ,
                        rs.getDouble("custo_mensal")
                        )) ;

            }

            return servicoAdicionais ;
        }
        catch (Exception e) {

            e.printStackTrace();

        }
        throw new RuntimeException("Nenhum serviço adicional cadastrado") ;
    }

    //update
    public static void atualizarServicoAdicional (ServicoAdicional servicoAdicional , int id ) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("UPDATE tb_servico_adicional SET descricao = ? , custo_mensal = ? WHERE id = ? ") ;

            ps.setString(1, servicoAdicional.getDescricao());
            ps.setDouble(2, servicoAdicional.getCusto_mensal());
            ps.setInt(3, id);

            ps.execute() ;


        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }

}

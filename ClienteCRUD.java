import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteCRUD {

    //create
    public static Cliente adicionarCliente (Cliente cliente) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_cliente ( id,nome,email,telefone,id_plano) VALUES ( ? , ? , ? , ? , ? ) " , Statement.RETURN_GENERATED_KEYS) ;

            ps.setInt(1 , cliente.getId());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefone());
            ps.setInt(5, cliente.getPlano().getId());

            ps.execute() ;
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                cliente.setId(rs.getInt(1));

            }

        }
        catch (Exception e) {

            e.printStackTrace();

        }
        return cliente ;
    }

    //delete
    public static void removerCliente (int id ) throws SQLException {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_cliente WHERE id = ? ") ;

            ps.setInt(1, id);

            ps.execute();

        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }

    //read
    public static Cliente buscarClientePorId (int id) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_cliente WHERE id = ? ") ;

            ps.setInt(1, id );

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int idCliente = rs.getInt("id") ;
                String nome = rs.getString("nome") ;
                String email = rs.getString("email") ;
                String telefone = rs.getString("telefone") ;
                Plano plano = PlanoCRUD.buscarPlanoPorId(rs.getInt("id_plano")) ;
                return new Cliente(idCliente , nome , email , telefone , plano) ;

            }
        }
        catch (Exception e ) {

            e.printStackTrace();

        }
        throw new RuntimeException("Nenhum cliente cadastrado com o id " + id) ;
    }

    //read
    public static List<Cliente> buscarTodosOsClientes () {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_cliente") ;

            ResultSet rs = ps.executeQuery() ;
            List<Cliente> clientes = new ArrayList<>() ;

            while (rs.next()) {

                clientes.add(new Cliente(
                        rs.getInt("id") ,
                        rs.getString("nome") ,
                        rs.getString("email"),
                        rs.getString("telefone") ,
                        PlanoCRUD.buscarPlanoPorId(rs.getInt("id_plano"))

                )) ;
            }
            return clientes ;
        }
        catch (Exception e) {

            e.printStackTrace();

        }

        throw new RuntimeException("Nenhum cliente cadastrado") ;
    }

    //update
    public static void atualizarCliente (Cliente cliente , int id ) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("UPDATE tb_cliente SET nome = ? , email = ? , telefone = ? , id_plano = ? WHERE id = ? ") ;

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone());
            ps.setInt(4, cliente.getPlano().getId());
            ps.setInt(5, id);

            ps.execute() ;

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }
}

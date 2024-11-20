import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContratoCRUD {

    //create
    public static Contrato adicionarContrato (Contrato contrato) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_contrato (id, id_plano, termos, data_inicio, data_fim) VALUES ( ? , ? , ? , ? , ?" , Statement.RETURN_GENERATED_KEYS) ;

            ps.setInt(1, contrato.getId());
            ps.setInt(2, contrato.getPlano().getId());
            ps.setString(3, contrato.getTermos());
            ps.setString(4, contrato.getData_inicio());
            ps.setString(5, contrato.getData_fim());

            ps.execute() ;
            ResultSet rs = ps.getGeneratedKeys() ;

            if(rs.next()) {
                contrato.setId(rs.getInt(1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return contrato;
    }

    //read
    public static Contrato buscarContratoPorId (int id ) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_contrato WHERE id = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery() ;

            if (rs.next()) {

                int idContrato = rs.getInt("id") ;
                Plano plano = PlanoCRUD.buscarPlanoPorId(rs.getInt("id_plano"));
                String termos = rs.getString("termos");
                String data_inicio = rs.getString("data_inicio");
                String data_fim = rs.getString("data_fim");
                return new Contrato(idContrato, plano , termos , data_inicio, data_fim) ;

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Nenhum contrato encontrado com o id " + id) ;
    }

    public static Contrato buscarContratoPorPlano (int idPlano) {

        try (Connection connection = ConexaoBanco.getConnections()){

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_contrato WHERE id_plano = ? ");

        ps.setInt(1, idPlano);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            int id = rs.getInt("id");
            Plano plano = PlanoCRUD.buscarPlanoPorId(rs.getInt("id_plano"));
            String termos = rs.getString("termos");
            String data_inicio = rs.getString("data_inicio");
            String data_fim = rs.getString("data_fim");
            return new Contrato(id, plano, termos, data_inicio, data_fim) ;
        }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Nenhum contrato vinculado ao Plano com Id " + idPlano);
    }

    //read
    public static List<Contrato> buscarTodosOsContratos () {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_contrato ") ;

            ResultSet rs = ps.executeQuery();
            List<Contrato> contratos = new ArrayList<>() ;

            while (rs.next()) {
                    contratos.add(new Contrato(
                            rs.getInt("id"),
                            PlanoCRUD.buscarPlanoPorId(rs.getInt("id_plano")) ,
                            rs.getString("termos") ,
                            rs.getString("data_inicio") ,
                            rs.getString("data_fim")
                    )) ;
            }
            return contratos ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Nenhum contrato encontrado") ;
    }

    //update
    public static void atualizarContrato ( Contrato contrato, int id) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("UPDATE tb_contrato SET id_plano = ? , termos = ? , data_inicio = ? , data_fim = ? WHERE id = ?") ;

            ps.setInt(1, contrato.getPlano().getId());
            ps.setString(2, contrato.getTermos());
            ps.setString(3, contrato.getData_inicio());
            ps.setString(4, contrato.getData_fim());
            ps.setInt(5, id);

            ps.execute() ;

        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    //delete
    public static void removerContrato (int id) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_contrato WHERE id = ?");

            ps.setInt(1, id);
            ps.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

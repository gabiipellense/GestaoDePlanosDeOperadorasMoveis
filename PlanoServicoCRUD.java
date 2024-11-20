import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoServicoCRUD {

    //create
    public static PlanoServico adicionarServicoAPlano (PlanoServico planoServico) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_plano_servico (id_plano , id_servico) VALUES ( ? , ? )") ;

            ps.setInt(1, planoServico.getPlano().getId());
            ps.setInt(2, planoServico.getServico().getId());

            ps.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return planoServico;

    }

    //read
    public static List<PlanoServico> buscarPlanoServicoPorIdPlano (int id) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_plano_servico WHERE id = ?") ;

            ps.setInt(1 , id);

            ResultSet rs = ps.executeQuery();
            List<PlanoServico> planoServicos = new ArrayList<>() ;

            while (rs.next()) {

                planoServicos.add(new PlanoServico(
                   PlanoCRUD.buscarPlanoPorId(rs.getInt("id_plano")),
                   ServicoAdicionalCRUD.buscarServicoAdicionalPorId(rs.getInt("id_servico"))
                ));
            }
            return planoServicos ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Nenhum Serviço vinculado a um Plano de id " + id);

    }

    //delete
    public static void removerPlanoServico (Plano plano, ServicoAdicional servicoAdicional) throws SQLException {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_plano_servico WHERE id_plano = ? AND id_servico = ?");

            ps.setInt(1, plano.getId());
            ps.setInt(2, servicoAdicional.getId() );

            ps.execute() ;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlanoCRUD {

    //create
    public static Plano adicionarPlano (Plano plano) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_plano (id, operadora, nome, quantidade_dados , quantidade_dados_bonus , beneficios , valor) VALUES  (? , ? , ? ,? , ? , ? , ?)" , Statement.RETURN_GENERATED_KEYS ) ;

            ps.setInt(1, plano.getId());
            ps.setString(2, plano.getOperadora());
            ps.setString(3, plano.getNome());
            ps.setDouble(4, plano.getQuantidade_dados());
            ps.setDouble(5,plano.getQuantidade_dados_bonus());
            ps.setString(6, plano.getBeneficios());
            ps.setDouble(7, plano.getValor());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                plano.setId(rs.getInt(1));

            }
        }
        catch (Exception e ) {

            e.printStackTrace();
        }
        return plano;
    }

    //delete
    public static void removerPlano (int id) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_plano WHERE id = ? ") ;

            ps.setInt(1, id);
            ps.execute() ;

        }
        catch (Exception e ) {

            e.printStackTrace();

        }

    }

    //read
    public static List<Plano> buscarTodosOsPlanosDaOperadora (String operadora) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_plano WHERE operadora = ? ") ;

            ps.setString(1,operadora);
            ResultSet rs = ps.executeQuery();
            List <Plano> planosOperadoras = new ArrayList<>() ;


            while (rs.next()) {
                planosOperadoras.add(new Plano(
                        rs.getInt("id") ,
                        rs.getString("operadora" ) ,
                        rs.getString("nome") ,
                        rs.getDouble("quantidade_dados") ,
                        rs.getDouble("quantidade_dados_bonus"),
                        rs.getString("beneficios") ,
                        rs.getDouble("valor")
                        )) ;

            }
            return planosOperadoras ;
        }
        catch (Exception e) {

            e.printStackTrace();

        }
        throw new RuntimeException("Nenhum plano da Operadora " + operadora + " registrado") ;
    }

    //read
    public static Plano buscarPlanoPorId (int id ) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_plano WHERE id = ? ") ;

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery() ;

            if (rs.next()) {
                int idPlano = rs.getInt("id") ;
                String operadora = rs.getString("operadora");
                String nome = rs.getString("nome");
                Double quantidade_dados = rs.getDouble("quantidade_dados") ;
                Double quantidade_dados_bonus = rs.getDouble("quantidade_dados");
                String beneficios = rs.getString("beneficios")  ;
                Double valor = rs.getDouble("valor") ;
                return new Plano(idPlano , operadora, nome , quantidade_dados , quantidade_dados_bonus , beneficios , valor) ;
            }
        }
        catch (Exception e ) {

            e.printStackTrace();

        }

        throw new RuntimeException( "Nenhum plano registrado com o id " + id ) ;
    }

    //update
    public static void atualizarPlano (Plano plano , int id) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("UPDATE tb_plano SET operadora = ?, nome = ?, quantidade_dados = ?, quantidade_dados_bonus = ?, beneficios = ?, valor= ? WHERE id = ? ") ;

            ps.setString(1,plano.getOperadora());
            ps.setString(2, plano.getNome());
            ps.setDouble(3, plano.getQuantidade_dados());
            ps.setDouble(4, plano.getQuantidade_dados_bonus());
            ps.setString(5, plano.getBeneficios());
            ps.setDouble(6, plano.getValor());
            ps.setInt(7, id);

            ps.execute() ;
        }
        catch (Exception e ) {

            e.printStackTrace();

        }
    }
}

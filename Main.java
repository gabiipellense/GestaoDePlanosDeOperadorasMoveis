import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final Scanner sc = new Scanner(System.in) ;
    public static void main(String[] args) throws SQLException {

        int resposta  ;

        do {

            System.out.println("Digite o número correspondente a ação que você deseja executar: ");

            System.out.println("--------------------MENU--------------------");
            System.out.println(" 1 - Adicionar um Plano  ");
            System.out.println(" 2 - Buscar um Plano por Id ");
            System.out.println(" 3 - Buscar todos os Planos de uma Operadora ");
            System.out.println(" 4 - Atualizar um Plano pelo Id ");
            System.out.println(" 5 - Remover Plano ");
            System.out.println(" 6 - Adicionar um Cliente ");
            System.out.println(" 7 - Buscar um Cliente por Id ");
            System.out.println(" 8 - Buscar Todos os Clientes ");
            System.out.println(" 9 - Atualizar um Cliente por Id ");
            System.out.println("10 - Remover Cliente ");
            System.out.println("11 - Adicionar um Serviço Adicional ");
            System.out.println("12 - Buscar um Serviço Adicional por Id ");
            System.out.println("13 - Buscar todos os Serviços Adicionais ");
            System.out.println("14 - Atualizar um Serviço Adicional por Id ");
            System.out.println("15 - Remover Servico Adicional ");
            System.out.println("16 - Vincular um Servico Adicional a um Plano ");
            System.out.println("17 - Buscar Serviço Por Plano ");
            System.out.println("18 - Remover Serviço de Plano ");
            System.out.println("19 - Vincular um Plano a um Contrato ");
            System.out.println("20 - Buscar Cliente Por Plano ");
            System.out.println("21 - Buscar Contrato Por Plano ");
            System.out.println("22 - Atualizar Contrato ");
            System.out.println("23 - Remover Contrato ");
            System.out.println("24 - Buscar Todos os Contratos ");
            System.out.println("25 - Buscar Contrato Por Id ");
            System.out.println(" 0 - Sair ");
            System.out.println("--------------------------------------------");
            resposta = sc.nextInt() ;

            switch (resposta) {

                case 1 :
                    adicionarPlano();
                    break;

                case 2 :
                    buscarPlanoPorId();
                    break;

                case 3 :
                    buscarPlanoPorOperadora();
                    break;

                case 4:
                    atualizarPlano();
                    break;

                case 5:
                    removerPlano();
                    break;

                case 6 :
                    adicionarCliente();
                    break;

                case 7 :
                    buscarClientePorId();
                    break;

                case 8 :
                    buscarTodosOsClientes();
                    break;

                case 9 :
                    atualizarCliente();
                    break;

                case 10 :
                    removerCliente();
                    break;

                case 11 :
                    adicionarServicoAdicional();
                    break;

                case 12 :
                    buscarServicoPorId();
                    break;

                case 13 :
                    buscarTodosOsServicosAdicionais();
                    break;

                case 14 :
                    atualizarServicoAdicional();
                    break;

                case 15 :
                    removerServicoAdicional();
                    break;

                case 16:
                    vincularUmServicoComUmPlano();
                    break;

                case 17:
                    buscarServicoPorPlano();
                    break;

                case 18:
                    removerPlanoServico();
                    break;

                case 19:
                    adicionarContrato();
                    break;

                case 20:
                    buscarClientePorPlano();
                    break;

                case 21:
                    buscarContratoPorPlano();
                    break;

                case 22:
                    atualizarContrato();
                    break;

                case 23:
                    removerContrato();
                    break;

                case 24:
                    buscarTodosOsContratos();
                    break;

                case 25:
                    buscarContratoPorId();
                    break;

                case 0 :
                    System.exit(0);
                    break;

                default:

                    System.out.println("Esse número não faz parte do MENU de opções ");

            }

        }while (resposta != 0 ) ;

        ConexaoBanco.getConnections();

    }
    public static Plano adicionarPlano () {

        System.out.println("Digite a Operadora do Plano: ");
        String operadora = sc.next() ;

        System.out.println("Digite o nome do Plano: ");
        String nome = sc.next() ;

        System.out.println("Digite a quantidade de dados que você deseja: ");
        Double quantidade_dados = sc.nextDouble() ;

        System.out.println("Digite a quantidade de dados bonus que você deseja: ");
        Double quantidade_dados_bonus = sc.nextDouble() ;

        System.out.println("Digite os beneficios do Plano: ");
        String beneficios = sc.next() ;

        System.out.println("Digite o valor do Plano: ");
        Double valor = sc.nextDouble();

        return PlanoCRUD.adicionarPlano(new Plano(operadora, nome , quantidade_dados , quantidade_dados_bonus, beneficios , valor)) ;

    }
    public static void buscarPlanoPorId () {

        System.out.println("Digite o id do plano que você deseja buscar: ");
        int id = sc.nextInt() ;

        System.out.println(PlanoCRUD.buscarPlanoPorId(id));

    }
    public static List<Plano> buscarPlanoPorOperadora () {

        System.out.println("Digite a operadora que você deseja pesquisar os planos ");
        String operadora = sc.next() ;

        System.out.println(PlanoCRUD.buscarTodosOsPlanosDaOperadora(operadora));

        return PlanoCRUD.buscarTodosOsPlanosDaOperadora(operadora) ;

    }
    public static void atualizarPlano () {

        System.out.println("Digite o id do plano que você deseja atualizar: ");
        int id = sc.nextInt() ;

        System.out.println("Digite a nova Operadora:  ");
        String operadora = sc.next() ;

        System.out.println("Digite o novo nome do Plano:  ");
        String nome = sc.next() ;

        System.out.println("Digite a nova quantidade de dados:  ");
        Double quantidade_dados = sc.nextDouble() ;

        System.out.println("Digite a nova quantidade de dados bonus:  ");
        Double quantidade_dados_bonus = sc.nextDouble() ;

        System.out.println("Digite os novos benefícios:  ");
        String beneficios = sc.next() ;

        System.out.println("Digite o novo valor do Plano:  ");
        Double valor = sc.nextDouble() ;

        PlanoCRUD.atualizarPlano(new Plano(operadora , nome , quantidade_dados , quantidade_dados_bonus , beneficios , valor), id);

        System.out.println("Plano " + id + " atualizado com sucesso ");
    }
    public static void removerPlano () {

        System.out.println("Digite o id do plano que você deseja remover: ");
        int id = sc.nextInt() ;

        PlanoCRUD.removerPlano(id);

        System.out.println("Plano " + id + " removido com sucesso ");
    }
    public static ServicoAdicional adicionarServicoAdicional () {

        System.out.println("Digite a descrição do Serviço Adicional que você deseja adicionar: ");
        String descricao = sc.next() ;

        System.out.println("Digite o custo mensal do Serviço Adicional:  ");
        Double custo_mensal = sc.nextDouble() ;

        return ServicoAdicionalCRUD.AdicionarServicoAdicional(new ServicoAdicional(descricao , custo_mensal)) ;

    }
    public static void buscarServicoPorId () {

        System.out.println("Digite o Id do Serviço Adicional que você deseja buscar: ");
        int id = sc.nextInt() ;

        System.out.println(ServicoAdicionalCRUD.buscarServicoAdicionalPorId(id));

    }
    public static List<ServicoAdicional> buscarTodosOsServicosAdicionais () {

        System.out.println(ServicoAdicionalCRUD.buscarTodosOsServicosAdicionais());
        return ServicoAdicionalCRUD.buscarTodosOsServicosAdicionais() ;

    }
    public static void atualizarServicoAdicional () {

        System.out.println("Digite o Id do Serviço Adicional que você deseja atualizar: ");
        int id = sc.nextInt();

        System.out.println("Digite a nova descrição: ");
        String descricao = sc.next() ;

        System.out.println("Digite o novo custo mensal do Serviço: ");
        Double custo_mensal = sc.nextDouble();

        ServicoAdicionalCRUD.atualizarServicoAdicional(new ServicoAdicional(descricao , custo_mensal ) , id);

        System.out.println("Serviço Adicional " + id + " atualizado com sucesso ");

    }
    public static void removerServicoAdicional () {

        System.out.println("Digite o Id do Serviço Adicional que você deseja remover: ");
        int id = sc.nextInt() ;

        ServicoAdicionalCRUD.removerServicoAdicional(id);

        System.out.println("Serviço Adicional " + id + " removido com sucesso ");

    }
    public static Cliente adicionarCliente () {

        System.out.println("Digite o nome do que Cliente que você deseja adicionar: ");
        String nome = sc.next() ;

        System.out.println("Digite o email do Cliente: ");
        String email = sc.next() ;

        System.out.println("Digite o telefone do Cliente: ");
        String telefone = sc.next() ;

        System.out.println("Digite o Id do Plano ao qual você deseja associar o cliente: ");
        int id_plano = sc.nextInt() ;

        Plano plano = PlanoCRUD.buscarPlanoPorId(id_plano) ;

        return ClienteCRUD.adicionarCliente(new Cliente(nome , email , telefone , plano) ) ;

    }
    public static void buscarClientePorId () {

        System.out.println("Digite o Id do Cliente que você deseja buscar: ");
        int id = sc.nextInt();

        System.out.println(ClienteCRUD.buscarClientePorId(id) );

    }
    public static List<Cliente> buscarTodosOsClientes () {

        System.out.println(ClienteCRUD.buscarTodosOsClientes());
        return ClienteCRUD.buscarTodosOsClientes() ;

    }
    public static void atualizarCliente () {

        System.out.println("Digite o Id do Cliente que você deseja atualizar: ");
        int id = sc.nextInt();;

        System.out.println("Digite o novo nome do Cliente: ");
        String nome = sc.next() ;

        System.out.println("Digite o novo email do CLiente: ");
        String email = sc.next() ;

        System.out.println("Digite o novo telefone do Cliente: ");
        String telefone = sc.next();

        System.out.println("Digite o novo id do Plano ao qual você deseja vincular o Cliente: ");
        int id_plano = sc.nextInt();

        Plano plano = PlanoCRUD.buscarPlanoPorId(id_plano) ;

        ClienteCRUD.atualizarCliente(new Cliente(nome, email, telefone, plano) , id);

        System.out.println("--- Cliente Atualizado Com Sucesso ---");
    }
    public static void removerCliente () {

        try {
            System.out.println("Digite o Id do Cliente que você deseja remover: ");
            int id = sc.nextInt();

            ClienteCRUD.removerCliente(id);
            System.out.println("--- Cliente Removido com Sucesso ---");
        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }
    public static PlanoServico vincularUmServicoComUmPlano () {

        System.out.println("Digite o Id do Serviço Adicional que você deseja vincular:");
        int idServico = sc.nextInt() ;

        System.out.println("Digite o Id do Plano que você deseja vincular o Serviço Adicional:");
        int idPlano = sc.nextInt() ;

        Plano plano = PlanoCRUD.buscarPlanoPorId(idPlano) ;

        ServicoAdicional servico = ServicoAdicionalCRUD.buscarServicoAdicionalPorId(idServico);

        return PlanoServicoCRUD.adicionarServicoAPlano(new PlanoServico(plano,servico));

    }
    public static List<PlanoServico> buscarServicoPorPlano () {

        System.out.println("Digite o Id do Plano que você deseja buscar os serviços: ");
        int idPlano = sc.nextInt();

        System.out.println(PlanoServicoCRUD.buscarPlanoServicoPorIdPlano(idPlano));
        return PlanoServicoCRUD.buscarPlanoServicoPorIdPlano(idPlano);

    }
    public static void removerPlanoServico () throws SQLException{

        System.out.println("Digite o Id do Plano vinculado ao Serviço Adicional: ");
        int idPlano = sc.nextInt() ;

        System.out.println("Digite o Id do Serviço Adicional vinculado ao Plano que você deseja apagar: ");
        int idServico = sc.nextInt() ;

        Plano plano = PlanoCRUD.buscarPlanoPorId(idPlano) ;
        ServicoAdicional servicoAdicional = ServicoAdicionalCRUD.buscarServicoAdicionalPorId(idServico) ;

        PlanoServicoCRUD.removerPlanoServico(plano,servicoAdicional);

        System.out.println("--- Desvinculado com Sucesso ---");
    }
    public static List<Cliente> buscarClientePorPlano () {

        System.out.println("Digite o Id do Plano que você deseja saber os Clientes: ");
        int idPlano = sc.nextInt() ;

        Plano plano = PlanoCRUD.buscarPlanoPorId(idPlano);

        System.out.println(ClienteCRUD.buscarClientePorPlano(plano));
        return ClienteCRUD.buscarClientePorPlano(plano) ;

    }
    public static Contrato adicionarContrato () {

        System.out.println("Digite o Id do Plano que você deseja contratar: ");
        int idPlano = sc.nextInt() ;

        System.out.println("Digite os Termos do Contrato: ");
        String termos = sc.next() ;

        System.out.println("Digite a Data de Início do Contrato");
        String data_inicio = sc.next() ;

        System.out.println("Digite a Data de Fim do Contrato");
        String data_fim = sc.next() ;

        Plano plano = PlanoCRUD.buscarPlanoPorId(idPlano) ;

        return ContratoCRUD.adicionarContrato(new Contrato(plano, termos, data_inicio, data_fim)) ;

    }
    public static Contrato buscarContratoPorPlano () {

        System.out.println("Digite o Id do Plano que você deseja pesquisar o Contrato: ");
        int idPlano = sc.nextInt() ;

        System.out.println(ContratoCRUD.buscarContratoPorPlano(idPlano));
        return ContratoCRUD.buscarContratoPorPlano(idPlano) ;

    }
    public static void atualizarContrato () {

        System.out.println("Digite qual o Id do Contrato que você deseja atualizar: ");
        int id = sc.nextInt() ;

        System.out.println("Digite o Id do Plano atualizado: ");
        int idPlano = sc.nextInt();

        Plano plano = PlanoCRUD.buscarPlanoPorId(idPlano) ;

        System.out.println("Digite os novos Termos: ");
        String termos = sc.next();

        System.out.println("Digite a nova Data previsa para o Início do Contrato: ");
        String data_inicio = sc.next() ;

        System.out.println("Digite a nova Data previsa para o Fim do Contrato: ");
        String data_fim = sc.next() ;

        ContratoCRUD.atualizarContrato(new Contrato(plano, termos, data_inicio, data_fim), id );

        System.out.println("--- Contrato Atualizado Com Sucesso ---");
    }
    public static void removerContrato () {

        System.out.println("Digite o Id do Contrato que você deseja apagar: ");
        int id = sc.nextInt() ;

        ContratoCRUD.removerContrato(id);

        System.out.println("--- Contrato Removido com Sucesso ---");

    }
    public static List<Contrato> buscarTodosOsContratos () {

        System.out.println(ContratoCRUD.buscarTodosOsContratos());
        return ContratoCRUD.buscarTodosOsContratos();

    }
    public static void buscarContratoPorId () {

        System.out.println("Digite o Id do Contrato que você deseja pesquisar: ");
        int idContrato = sc.nextInt() ;

        System.out.println(ContratoCRUD.buscarContratoPorId(idContrato));

    }
}


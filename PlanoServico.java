public class PlanoServico {

    private Plano plano;
    private ServicoAdicional servico ;

    public PlanoServico(Plano plano, ServicoAdicional servico) {
        this.plano = plano;
        this.servico = servico;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public ServicoAdicional getServico() {
        return servico;
    }

    public void setServico(ServicoAdicional servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return  "\n-------------------------------"+
                "\n             PLANO             "+
                "\n-------------------------------" +
                "\nPlano = " + plano.getId() +
                "\nServico = " + servico.getId() +
                "\n-------------------------------";
    }
}

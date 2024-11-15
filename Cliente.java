public class Cliente {
    private int id ;
    private String nome ;
    private String email ;
    private String telefone ;
    private Plano plano;

    public Cliente(int id, String nome, String email, String telefone, Plano plano) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.plano = plano;
    }

    public Cliente(String nome, String email, String telefone, Plano plano) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.plano = plano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    @Override
    public String toString() {
        return  "\n-------------------------------"+
                "\n           CLIENTE" +
                "\n-------------------------------" +
                "\nId = " + id +
                "\nNome = " + nome +
                "\nEmail = " + email +
                "\nTelefone = " + telefone +
                "\nPlano = " + plano +
                "\n-------------------------------";
    }
}

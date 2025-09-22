import java.util.*;

class Usuario implements Identificavel {
    private int id; private String nome; private String email;
    public Usuario(int id,String nome,String email){ this.id=id; this.nome=nome; this.email=email; }
    public void exibirInfo(){ System.out.println("Usuário: "+nome+" ("+email+")"); }
    public int getId(){ return id; }
    public String getNome(){ return nome; } public void setNome(String n){ this.nome=n; }
    public String getEmail(){ return email; } public void setEmail(String e){ this.email=e; }
}

interface IUsuarioService extends IServico<Usuario>{ List<Usuario> buscarPorNome(String nome); }

class UsuarioService extends ServicoGenerico<Usuario> implements IUsuarioService {
    public UsuarioService(IRepositorio<Usuario> repo){ super(repo); }
    public List<Usuario> buscarPorNome(String nome){
        List<Usuario> res = new ArrayList<>();
        for(Usuario u: repositorio.listar()){ if(u.getNome().toLowerCase().contains(nome.toLowerCase())) res.add(u); }
        return res;
    }
}

class UsuarioController {
    private IUsuarioService service;
    public UsuarioController(IUsuarioService s){ this.service=s; }
    public void cadastrarUsuario(int id,String nome,String email){ service.cadastrar(new Usuario(id,nome,email)); }
    public void listarUsuarios(){ service.listar(); }
}

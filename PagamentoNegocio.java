import java.util.*;

class Pagamento implements Identificavel {
    private int id, usuarioId; private double valor;
    public Pagamento(int id,int u,double v){ this.id=id; this.usuarioId=u; this.valor=v; }
    public void exibirInfo(){ System.out.println("Pagamento ID "+id+" - Usuário "+usuarioId+" - R$"+valor); }
    public int getId(){ return id; }
    public int getUsuarioId(){ return usuarioId; } public void setUsuarioId(int u){ usuarioId=u; }
    public double getValor(){ return valor; } public void setValor(double v){ valor=v; }
}

interface IPagamentoService extends IServico<Pagamento>{ List<Pagamento> buscarPorUsuario(int usuarioId); }

class PagamentoService extends ServicoGenerico<Pagamento> implements IPagamentoService {
    public PagamentoService(IRepositorio<Pagamento> repo){ super(repo); }
    public List<Pagamento> buscarPorUsuario(int usuarioId){
        List<Pagamento> res = new ArrayList<>();
        for(Pagamento p: repositorio.listar()){ if(p.getUsuarioId()==usuarioId) res.add(p); }
        return res;
    }
}

class PagamentoController {
    private IPagamentoService service;
    public PagamentoController(IPagamentoService s){ service=s; }
    public void cadastrarPagamento(int id,int u,double v){ service.cadastrar(new Pagamento(id,u,v)); }
    public void listarPagamentos(){ service.listar(); }
}

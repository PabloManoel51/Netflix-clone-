import java.util.*;

class Plano implements Identificavel {
    private int id; private String tipo; private double preco;
    public Plano(int id,String tipo,double preco){ this.id=id; this.tipo=tipo; this.preco=preco; }
    public void exibirInfo(){ System.out.println("Plano: "+tipo+" - R$"+preco); }
    public int getId(){ return id; }
    public String getTipo(){ return tipo; } public void setTipo(String t){ tipo=t; }
    public double getPreco(){ return preco; } public void setPreco(double p){ preco=p; }
}

interface IPlanoService extends IServico<Plano>{ List<Plano> buscarPorTipo(String tipo); }

class PlanoService extends ServicoGenerico<Plano> implements IPlanoService {
    public PlanoService(IRepositorio<Plano> repo){ super(repo); }
    public List<Plano> buscarPorTipo(String tipo){
        List<Plano> res = new ArrayList<>();
        for(Plano p: repositorio.listar()){ if(p.getTipo().equalsIgnoreCase(tipo)) res.add(p); }
        return res;
    }
}

class PlanoController {
    private IPlanoService service;
    public PlanoController(IPlanoService s){ service=s; }
    public void cadastrarPlano(int id,String tipo,double preco){ service.cadastrar(new Plano(id,tipo,preco)); }
    public void listarPlanos(){ service.listar(); }
}

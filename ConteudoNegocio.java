import java.util.*;

interface Identificavel {
    int getId();
}

abstract class Conteudo implements Identificavel {
    protected int id;
    protected String titulo;
    protected String genero;

    public Conteudo(int id, String titulo, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
    }

    public abstract void exibirInfo();
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}

class Filme extends Conteudo {
    private int duracao;
    public Filme(int id, String titulo, String genero, int duracao) {
        super(id, titulo, genero);
        this.duracao = duracao;
    }
    public void exibirInfo() {
        System.out.println("Filme: " + titulo + " (" + genero + ") - " + duracao + " min");
    }
    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }
}

class Serie extends Conteudo {
    private int temporadas;
    public Serie(int id, String titulo, String genero, int temporadas) {
        super(id, titulo, genero);
        this.temporadas = temporadas;
    }
    public void exibirInfo() {
        System.out.println("Série: " + titulo + " (" + genero + ") - " + temporadas + " temporadas");
    }
    public int getTemporadas() { return temporadas; }
    public void setTemporadas(int temporadas) { this.temporadas = temporadas; }
}

interface IRepositorio<T extends Identificavel> {
    void adicionar(T obj);
    void remover(int id);
    void atualizar(T obj);
    List<T> listar();
    T buscar(int id);
}

class RepositorioGenerico<T extends Identificavel> implements IRepositorio<T> {
    private List<T> lista = new ArrayList<>();
    public void adicionar(T obj) { lista.add(obj); }
    public void remover(int id) { lista.removeIf(o -> o.getId() == id); }
    public void atualizar(T obj) {
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getId()==obj.getId()){ lista.set(i,obj); return; }
        }
    }
    public List<T> listar(){ return new ArrayList<>(lista); }
    public T buscar(int id){
        for(T obj: lista){ if(obj.getId()==id) return obj; }
        return null;
    }
}

interface IServico<T extends Identificavel> {
    void cadastrar(T obj);
    void remover(int id);
    void atualizar(T obj);
    void listar();
    T buscar(int id);
}

interface IFilmeService extends IServico<Filme> { List<Filme> buscarPorGenero(String genero); }
interface ISerieService extends IServico<Serie> { List<Serie> buscarPorTemporadas(int temporadas); }

class ServicoGenerico<T extends Identificavel> implements IServico<T> {
    protected IRepositorio<T> repositorio;
    public ServicoGenerico(IRepositorio<T> repositorio){ this.repositorio = repositorio; }
    public void cadastrar(T obj){ repositorio.adicionar(obj); }
    public void remover(int id){ repositorio.remover(id); }
    public void atualizar(T obj){ repositorio.atualizar(obj); }
    public void listar(){
        for(T obj: repositorio.listar()){
            try{ obj.getClass().getMethod("exibirInfo").invoke(obj); }
            catch(Exception e){ System.out.println("Erro ao exibir: "+e.getMessage()); }
        }
    }
    public T buscar(int id){ return repositorio.buscar(id); }
}

class FilmeService extends ServicoGenerico<Filme> implements IFilmeService {
    public FilmeService(IRepositorio<Filme> repo){ super(repo); }
    public List<Filme> buscarPorGenero(String genero){
        List<Filme> res = new ArrayList<>();
        for(Filme f: repositorio.listar()){ if(f.getGenero().equalsIgnoreCase(genero)) res.add(f); }
        return res;
    }
}

class SerieService extends ServicoGenerico<Serie> implements ISerieService {
    public SerieService(IRepositorio<Serie> repo){ super(repo); }
    public List<Serie> buscarPorTemporadas(int temporadas){
        List<Serie> res = new ArrayList<>();
        for(Serie s: repositorio.listar()){ if(s.getTemporadas()==temporadas) res.add(s); }
        return res;
    }
}

class FilmeController {
    private IFilmeService service;
    public FilmeController(IFilmeService service){ this.service=service; }
    public void cadastrarFilme(int id,String titulo,String genero,int duracao){ service.cadastrar(new Filme(id,titulo,genero,duracao)); }
    public void listarFilmes(){ service.listar(); }
    public void removerFilme(int id){ service.remover(id); }
    public List<Filme> buscarFilmesPorGenero(String genero){ return service.buscarPorGenero(genero); }
}

class SerieController {
    private ISerieService service;
    public SerieController(ISerieService service){ this.service=service; }
    public void cadastrarSerie(int id,String titulo,String genero,int temp){ service.cadastrar(new Serie(id,titulo,genero,temp)); }
    public void listarSeries(){ service.listar(); }
  }

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MenuView menu = new MenuView();
        menu.exibirMenu();
    }
}

class MenuView {
    private Scanner scanner;
    private FilmeController filmeController;
    private SerieController serieController;
    private UsuarioController usuarioController;
    private PlanoController planoController;
    private PagamentoController pagamentoController;
    private FilaController filaController;

    public MenuView() {
        this.scanner = new Scanner(System.in);

        IRepositorio<Filme> repoFilme = new RepositorioGenerico<>();
        IRepositorio<Serie> repoSerie = new RepositorioGenerico<>();
        IRepositorio<Usuario> repoUsuario = new RepositorioGenerico<>();
        IRepositorio<Plano> repoPlano = new RepositorioGenerico<>();
        IRepositorio<Pagamento> repoPagamento = new RepositorioGenerico<>();

        this.filmeController = new FilmeController(new FilmeService(repoFilme));
        this.serieController = new SerieController(new SerieService(repoSerie));
        this.usuarioController = new UsuarioController(new UsuarioService(repoUsuario));
        this.planoController = new PlanoController(new PlanoService(repoPlano));
        this.pagamentoController = new PagamentoController(new PagamentoService(repoPagamento));
        this.filaController = new FilaController();
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Gerenciar Filmes");
            System.out.println("2 - Gerenciar Séries");
            System.out.println("3 - Gerenciar Usuários");
            System.out.println("4 - Gerenciar Planos");
            System.out.println("5 - Gerenciar Pagamentos");
            System.out.println("6 - Gerenciar Fila de Pedidos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt(); scanner.nextLine();
            switch(opcao) {
                case 1: menuFilmes(); break;
                case 2: menuSeries(); break;
                case 3: menuUsuarios(); break;
                case 4: menuPlanos(); break;
                case 5: menuPagamentos(); break;
                case 6: menuFila(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while(opcao != 0);
    }

    private void menuFilmes() {
        int opcao;
        do {
            System.out.println("\n===== GERENCIAR FILMES =====");
            System.out.println("1 - Cadastrar Filme");
            System.out.println("2 - Listar Filmes");
            System.out.println("3 - Remover Filme");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt(); scanner.nextLine();
            switch(opcao) {
                case 1:
                    System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Título: "); String titulo = scanner.nextLine();
                    System.out.print("Gênero: "); String genero = scanner.nextLine();
                    System.out.print("Duração (min): "); int duracao = scanner.nextInt();
                    filmeController.cadastrarFilme(id, titulo, genero, duracao); break;
                case 2: filmeController.listarFilmes(); break;
                case 3:
                    System.out.print("ID do filme a remover: "); int idRemover = scanner.nextInt();
                    filmeController.removerFilme(idRemover); break;
            }
        } while(opcao != 0);
    }

    private void menuSeries() {
        int opcao;
        do {
            System.out.println("\n===== GERENCIAR SÉRIES =====");
            System.out.println("1 - Cadastrar Série");
            System.out.println("2 - Listar Séries");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt(); scanner.nextLine();
            if(opcao==1){
                System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                System.out.print("Título: "); String titulo = scanner.nextLine();
                System.out.print("Gênero: "); String genero = scanner.nextLine();
                System.out.print("Temporadas: "); int temporadas = scanner.nextInt();
                serieController.cadastrarSerie(id, titulo, genero, temporadas);
            } else if(opcao==2){ serieController.listarSeries(); }
        } while(opcao != 0);
    }

    private void menuUsuarios() {
        int opcao;
        do {
            System.out.println("\n===== GERENCIAR USUÁRIOS =====");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt(); scanner.nextLine();
            if(opcao==1){
                System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                System.out.print("Nome: "); String nome = scanner.nextLine();
                System.out.print("Email: "); String email = scanner.nextLine();
                usuarioController.cadastrarUsuario(id, nome, email);
            } else if(opcao==2){ usuarioController.listarUsuarios(); }
        } while(opcao != 0);
    }

    private void menuPlanos() {
        int opcao;
        do {
            System.out.println("\n===== GERENCIAR PLANOS =====");
            System.out.println("1 - Cadastrar Plano");
            System.out.println("2 - Listar Planos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt(); scanner.nextLine();
            if(opcao==1){
                System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                System.out.print("Tipo: "); String tipo = scanner.nextLine();
                System.out.print("Preço: "); double preco = scanner.nextDouble();
                planoController.cadastrarPlano(id, tipo, preco);
            } else if(opcao==2){ planoController.listarPlanos(); }
        } while(opcao != 0);
    }

    private void menuPagamentos() {
        int opcao;
        do {
            System.out.println("\n===== GERENCIAR PAGAMENTOS =====");
            System.out.println("1 - Cadastrar Pagamento");
            System.out.println("2 - Listar Pagamentos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt(); scanner.nextLine();
            if(opcao==1){
                System.out.print("ID: "); int id = scanner.nextInt();
                System.out.print("ID Usuário: "); int usuarioId = scanner.nextInt();
                System.out.print("Valor: "); double valor = scanner.nextDouble();
                pagamentoController.cadastrarPagamento(id, usuarioId, valor);
            } else if(opcao==2){ pagamentoController.listarPagamentos(); }
        } while(opcao != 0);
    }

    private void menuFila() {
        int opcao;
        do {
            System.out.println("\n===== FILA DE PEDIDOS =====");
            System.out.println("1 - Adicionar Pedido");
            System.out.println("2 - Atender Pedido");
            System.out.println("3 - Listar Fila");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt(); scanner.nextLine();
            switch(opcao) {
                case 1: System.out.print("Digite o pedido: "); String pedido = scanner.nextLine();
                        filaController.adicionarPedido(pedido); break;
                case 2: String atendido = filaController.atenderPedido();
                        System.out.println(atendido!=null?"Atendido: "+atendido:"Fila vazia!"); break;
                case 3: filaController.listarFila(); break;
            }
        } while(opcao != 0);
    }
  }
